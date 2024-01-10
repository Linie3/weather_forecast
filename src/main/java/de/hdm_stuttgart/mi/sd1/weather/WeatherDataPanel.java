package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static de.hdm_stuttgart.mi.sd1.weather.Forecast.saveWeatherData;

public class WeatherDataPanel extends JPanel{

    JTable weatherDataTable;
    JScrollPane sp;

    static WeatherDataPanel currWeatherDataPanel;

    public WeatherDataPanel (){
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3, true),"");
        this.setBorder(border);
        WeatherDataPanel.currWeatherDataPanel = this;

    }

    public void displayData(int cityId ){
        if (!(weatherDataTable == null)) {

        }

        System.out.println();
        saveWeatherData(cityId);

        Weather weather;

        try {
            weather = WeatherDataParser.parse("./src/main/resources/weather_data.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[][] weatherData = new String[8][8];
        int row = 0;
        for(List listElement : weather.getList()){
            weatherData[row][0] = new Date(listElement.getDt()* 1000L).toString();
            weatherData[row][1] = String.valueOf(listElement.getMain().getTemp());
            weatherData[row][2] = String.valueOf(listElement.getMain().getHumidity());
            weatherData[row][3] = String.valueOf(listElement.getMain().getPressure());
            row++;

            if (row > 7){
                break;
            }
        }

        String[] columnNames = {"Time", "Temperature", "Humidity", "Pressure"};
        weatherDataTable = new JTable(weatherData,columnNames);
        weatherDataTable.setEnabled(false);
        weatherDataTable.setPreferredSize(new Dimension(800,500));
        sp = new JScrollPane();
        sp.getViewport().add(weatherDataTable, null);
        this.add(sp);



        Forecast.currPanel.repaint();
    }
}
