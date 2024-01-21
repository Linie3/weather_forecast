// Package declaration for the weather application
package de.hdm_stuttgart.mi.sd1.weather;

// Import statements for necessary classes etc.
import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import static de.hdm_stuttgart.mi.sd1.weather.Forecast.saveWeatherData;

// Class declaration for the WeatherDataPanel
public class WeatherDataPanel extends JPanel{

    // Instance variables for the WeatherDataPanel class
    JTable weatherDataTable;
    JScrollPane sp;
    JLabel cityName;

    // Static variable to keep track of the current WeatherDataPanel instance
    static WeatherDataPanel currWeatherDataPanel;

    // Constructor for the WeatherDataPanel class
    public WeatherDataPanel (){
        // Creating a titled border for the panel
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3, true),"");
        this.setBorder(border);
        this.setLayout(new BorderLayout());
        WeatherDataPanel.currWeatherDataPanel = this;
    }

    // Method to display weather data for a specific city
    public void displayData(String cityNameStr){
        // Extracting city ID from the button text
        int cityId = getCityId(cityNameStr);
        // Removing existing components
        if (!(sp == null)) {
            this.remove(sp);
            this.remove(cityName);
        }


        // Saving weather data for the specified city
        saveWeatherData(cityId);

        // Parsing the weather data from the local file
        Weather weather;

        try {
            weather = WeatherDataParser.parse("./src/main/resources/weather_data.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Creating a 2D array to store weather data
        String[][] weatherData = new String[31][8];
        int row = 0;
        // Populating the weather data array with information from the parsed data
        for(List listElement : weather.getList()){
            weatherData[row][0] = new Date(listElement.getDt()* 1000L).toString();
            weatherData[row][1] = listElement.getMain().getTemp() + " C°";
            weatherData[row][2] = String.valueOf(listElement.getMain().getHumidity() + " %");
            weatherData[row][3] = String.valueOf(listElement.getMain().getPressure() + " hPa");
            weatherData[row][4] = listElement.getMain().getTempMin()+ " C°";
            weatherData[row][5] = listElement.getMain().getTempMax()+ " C°";
            row++;

            // Breaking the loop if 31 rows have been processed
            if (row > 30) {
                break;
            }
        }

        // Names for each Column for the JTable
        String[] columnNames = {"Time", "Temperature", "Humidity", "Pressure", "Minimum Temperature", "Maximum Temperature"};

        // Creating a JTable with the weather data
        weatherDataTable = new JTable(weatherData,columnNames);
        weatherDataTable.setEnabled(false);
        weatherDataTable.setPreferredSize(new Dimension(2000,500));

        // Creating a JScrollPane to display the JTable with a scroll bar
        sp = new JScrollPane();
        sp.getViewport().add(weatherDataTable, null);

        // Creating a JLabel to display the city name
        cityName=new JLabel(cityNameStr,SwingConstants.CENTER);

        // Adding components to the WeatherDataPanel
        this.add(cityName, BorderLayout.NORTH);
        this.add(sp,BorderLayout.CENTER);
        this.updateUI();

    }

    // Method to extract city ID from the button text
    static int getCityId(String buttonText){
        return Integer.parseInt(buttonText.substring(buttonText.indexOf("(")+1, buttonText.indexOf(")")));
    }
}
