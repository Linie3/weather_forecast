package de.hdm_stuttgart.mi.sd1.weather;


import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Providing terminal based weather forecast
 */
public class Forecast {

final static String KEY = "35e9fbabc3c58c940ce6f94c3b7dd10e";
  /**
   * <p>Entry starting the application.</p>
   *
   * @param args Yet unused.
   */
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a city: ");
    String cityName = scanner.next();

    ArrayList<City> matchingCities = new ArrayList<>();


    for (City city : Cities.cities) {     //Going through every city element// Out of array
      if (city.getName().contains(cityName)) {
        matchingCities.add(city);
      }
    }


    System.out.println("Please choose one of the following cities: ");
    for(int i = 0; i < matchingCities.size(); i++){

      System.out.println((i + 1) + ": " + matchingCities.get(i).getName() + " " + matchingCities.get(i).getCountry());
    }

    int cityIndex = scanner.nextInt() - 1;
    City city = matchingCities.get(cityIndex);

    saveWeatherData(city.getId());

    Weather weather;

    try {
      weather = WeatherDataParser.parse("./src/main/resources/weather_data.json");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (List listElement: weather.getList() ) {
      System.out.println(new Date(listElement.getDt() * 1000L));

    }






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

}