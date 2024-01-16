package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import java.io.IOException;

import java.util.Date;

import static de.hdm_stuttgart.mi.sd1.weather.Forecast.saveWeatherData;

public class WeatherDataPanel extends JPanel{

    JTable weatherDataTable;
    JScrollPane sp;
    JLabel cityName;

    static WeatherDataPanel currWeatherDataPanel;

    public WeatherDataPanel (){
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3, true),"");
        this.setBorder(border);
        this.setLayout(new BorderLayout());
        WeatherDataPanel.currWeatherDataPanel = this;

    }

    public void displayData(String cityNameStr){
        int cityId = getCityId(cityNameStr);
        if (!(sp == null)) {
            this.remove(sp);
            this.remove(cityName);
        }

        System.out.println();
        saveWeatherData(cityId);

        Weather weather;

        try {
            weather = WeatherDataParser.parse("./src/main/resources/weather_data.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[][] weatherData = new String[31][8];
        int row = 0;
        for(List listElement : weather.getList()){
            weatherData[row][0] = new Date(listElement.getDt()* 1000L).toString();
            weatherData[row][1] = listElement.getMain().getTemp() + " C°";
            weatherData[row][2] = String.valueOf(listElement.getMain().getHumidity() + " %");
            weatherData[row][3] = String.valueOf(listElement.getMain().getPressure() + " hPa");
            weatherData[row][4] = listElement.getMain().getTempMin()+ " C°";
            weatherData[row][5] = listElement.getMain().getTempMax()+ " C°";
            row++;

            if (row > 30) {
                break;
            }
        }

        String[] columnNames = {"Time", "Temperature", "Humidity", "Pressure", "Minimum Temperature", "Maximum Temperature"};
        weatherDataTable = new JTable(weatherData,columnNames);
        weatherDataTable.setEnabled(false);
        weatherDataTable.setPreferredSize(new Dimension(2000,500));
        sp = new JScrollPane();
        sp.getViewport().add(weatherDataTable, null);
        cityName=new JLabel(cityNameStr,SwingConstants.CENTER);
        this.add(cityName, BorderLayout.NORTH);
        this.add(sp,BorderLayout.CENTER);
        this.updateUI();

    }

    static int getCityId(String buttonText){
        return Integer.parseInt(buttonText.substring(buttonText.indexOf("(")+1, buttonText.indexOf(")")));
    }
}
