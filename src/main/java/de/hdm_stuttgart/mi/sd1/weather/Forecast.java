package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Providing terminal based weather forecast
 */
public class Forecast extends JPanel  {


InputPanel inputPanel;
public WeatherDataPanel weatherDataPanel;
final static String KEY = "35e9fbabc3c58c940ce6f94c3b7dd10e";

static Forecast currPanel;
static JFrame currFrame;
  /**
   * <p>Entry starting the application.</p>
   *
   * @param args Yet unused.
   */
  public static void main(String[] args) {

    Forecast panel = new Forecast();
    JFrame frame = new JFrame("weather_forecast");
    frame.setSize(1100,800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }
  public static void saveWeatherData(int id){
    String url = "https://api.openweathermap.org/data/2.5/forecast?lang=de&APPID="+ KEY +"&units=metric&id="+id;
    try {
      FileUtils.copyURLToFile(
              new URL(url),
              new File("./src/main/resources/weather_data.json"));
    } catch (IOException e) {
      System.out.println("Url couldn't be found");
    }
  }

  public Forecast(){
    inputPanel = new InputPanel();
    setLayout(new BorderLayout());
    add(inputPanel,BorderLayout.SOUTH);


    weatherDataPanel = new WeatherDataPanel();
    weatherDataPanel.setSize(2000, 100);
    add(weatherDataPanel,BorderLayout.CENTER);

    Forecast.currPanel=this;
  }
  static void cityNameEntered(String cityName){
    ArrayList<City> matchingCities = new ArrayList<>();

    for (City city : Cities.cities) {       //Going through every city element// Out of array
      if (city.getName().toUpperCase().contains(cityName.toUpperCase())) {
        matchingCities.add(city);
      }
    }
    if(matchingCities.isEmpty()){
      ImageIcon  icon = new ImageIcon("./src/main/resources/taliyahWarning.png");
      JOptionPane.showMessageDialog(null, "City not found.","Error", JOptionPane.WARNING_MESSAGE,icon);
      return;
    }

    CitySelectorWindow panel = new CitySelectorWindow(matchingCities.toArray(new City[0]));
    JFrame frame = new JFrame("City Selector 3000");
    Forecast.currFrame = frame;
    frame.setSize(400,600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

  static void closeSelectorWindow(){
    currFrame.dispose();
  }
}

