// Package declaration for the weather application
package de.hdm_stuttgart.mi.sd1.weather;

// Import statements for necessary classes etc.
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Class declaration for the InputPanel
public class InputPanel extends JPanel implements KeyListener {

    // Instance variables for the InputPanel class
    JTextField cityInput;
    JLabel inputLabel;

    // Constructor for the InputPanel class
    public InputPanel(){
        // Creating a text field and label for city input
        cityInput = new JTextField(10);
        inputLabel = new JLabel("Please enter your city: ");

        // Creating a titled border for the panel
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3, true),"Input");
        this.setBorder(border);

        // Adding the label and text field to the panel
        this.add(inputLabel);
        this.add(cityInput);

        // Adding a key listener to the text field
        cityInput.addKeyListener(this);

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // Checking if the Enter key is pressed
        if(e.getKeyCode()==10){
            // Calling the static method cityNameEntered in the Forecast class with the entered city name
            Forecast.cityNameEntered(cityInput.getText());
        }

    }
}
