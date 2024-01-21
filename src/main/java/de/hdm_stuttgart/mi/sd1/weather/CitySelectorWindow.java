// Package declaration for the weather application
package de.hdm_stuttgart.mi.sd1.weather;

// Import statements for necessary classes etc.
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class declaration for the InputPanel
public class CitySelectorWindow extends JPanel implements ActionListener {

    // Instance variables for the CitySelectorWindow class
    private City[] cities;
    private JRadioButton[] radioButtons;

    // Constructor for the CitySelectorWindow class
    public CitySelectorWindow(City[] cities){

        // Initializing instance variables with the provided array of cities
        this.cities = cities;
        radioButtons = new JRadioButton[cities.length];

        // Creating radio buttons for each city and adding ActionListener
        for (int i = 0; i < cities.length; i++){
            radioButtons[i] = new JRadioButton(cities[i].getName() + ", " + cities[i].getCountry() + " (" + cities[i].getId() + ")");
            radioButtons[i].addActionListener(this);
        }

            // Setting layout and adding radio buttons to the panel
        this.setLayout(new GridLayout(radioButtons.length, 1,3,3));
        ButtonGroup buttonGroup = new ButtonGroup();

        for (JRadioButton jb : radioButtons){
            buttonGroup.add(jb);
            this.add(jb);
        }
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {

        // Handling the action event when a radio button is clicked
        JRadioButton clickedButton = (JRadioButton) e.getSource();
        String buttonText = clickedButton.getText();

        // Displaying weather data for the selected city and closing the selector window
        WeatherDataPanel.currWeatherDataPanel.displayData(buttonText);
        Forecast.closeSelectorWindow();

    }
}
