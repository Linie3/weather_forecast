// Package declaration for the weather application
package de.hdm_stuttgart.mi.sd1.weather;

// Import statements for classes libaries etc.
import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import org.apache.commons.io.FileUtils;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Providing terminal based weather forecast
 */

// Class decleration for the Forecast application
public class Forecast extends JPanel  {

// Decleration of instance variables
InputPanel inputPanel;
public WeatherDataPanel weatherDataPanel;
final static String KEY = "35e9fbabc3c58c940ce6f94c3b7dd10e";

// Static variables for the current panel and current frame
static Forecast currPanel;
static JFrame currFrame;
  /**
   * <p>Entry starting the application.</p>
   *
   * @param args Yet unused.
   */

  // maine method to start the Application
  public static void main(String[] args) {

    //Creating an instance of Forecast panel and JFrame
    Forecast panel = new Forecast();
    JFrame frame = new JFrame("weather_forecast");
    frame.setSize(1100,800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

 // MEthod to save weather data for a specific Cityid
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

  // Constructor for the Forecast class
  public Forecast(){
    inputPanel = new InputPanel();
    setLayout(new BorderLayout());
    add(inputPanel,BorderLayout.SOUTH);

    weatherDataPanel = new WeatherDataPanel();
    weatherDataPanel.setSize(2000, 100);
    add(weatherDataPanel,BorderLayout.CENTER);

    Forecast.currPanel=this;
  }

  // Method to handle when a city name is entered
  static void cityNameEntered(String cityName){
    ArrayList<City> matchingCities = new ArrayList<>();

    // Searching for matching cities based on the users input
    for (City city : Cities.cities) {       //Going through every city element// Out of array
      if (city.getName().toUpperCase().contains(cityName.toUpperCase())) {
        matchingCities.add(city);
      }
    }

    // Handle and Display an error when city name was not found
    if(matchingCities.isEmpty()){
      ImageIcon  icon = new ImageIcon("./src/main/resources/taliyahWarning.png");
      JOptionPane.showMessageDialog(null, "City not found.","Error", JOptionPane.WARNING_MESSAGE,icon);
      return;
    }

    // Creating and display a city selector window with all of the matching cities
    CitySelectorWindow panel = new CitySelectorWindow(matchingCities.toArray(new City[0]));
    JFrame frame = new JFrame("City Selector 3000, nur für 150,00$");
    Forecast.currFrame = frame;
    frame.setSize(400,600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

  // Method to close City Selector Window
  static void closeSelectorWindow(){
    currFrame.dispose();
  }
}

