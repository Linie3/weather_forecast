package de.hdm_stuttgart.mi.sd1.weather;


import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Providing terminal based weather forecast
 */
public class Forecast {

  /**
   * <p>Entry starting the application.</p>
   * @param args Yet unused.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a city: ");
    String cityName = scanner.next();

    ArrayList<City> matchingCities = new ArrayList<>();
    for (City city : Cities.cities) {     //Going through every city element// Out of array
      if(city.getName().contains(cityName)){
        matchingCities.add(city);
      }
    }
    System.out.println("Please choose one of the following cities: ");
    for(int i = 0; i < matchingCities.size(); i++){
      System.out.println((i + 1) + ": " + matchingCities.get(i).getName() + " " + matchingCities.get(i).getCountry());
    }
  }
}