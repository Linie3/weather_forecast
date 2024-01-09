package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import static de.hdm_stuttgart.mi.sd1.weather.Forecast.saveWeatherData;

public class WeatherDataPanel extends JPanel{

    JLabel weatherDataLabel;
    JTable weatherDataTable;

    static WeatherDataPanel currWeatherDataPanel;

    public WeatherDataPanel (){
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3, true),"");
        weatherDataLabel = new JLabel("Weather Data");
        weatherDataTable = new JTable(8,8);
        this.setBorder(border);
        this.add(weatherDataLabel);
        WeatherDataPanel.currWeatherDataPanel = this;

    }

    public void displayData(int cityId ){
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
//           weatherData[row][2] = new Date(listElement.getDt()* 1000L).toString();
//           weatherData[row][3] = new Date(listElement.getDt()* 1000L).toString();
//           weatherData[row][4] = new Date(listElement.getDt()* 1000L).toString();

             row++;

             if (row > 7){
                 break;
             }

         }
        String[] columnNames = {"Time","Temperature"};
        weatherDataTable = new JTable(weatherData,columnNames);

        this.add(weatherDataLabel);
    }
}
