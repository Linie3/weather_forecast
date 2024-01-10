package de.hdm_stuttgart.mi.sd1.weather;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputPanel extends JPanel implements KeyListener {
    JTextField cityInput;
    JLabel inputLabel;
    public InputPanel(){
        cityInput = new JTextField(10);
        inputLabel = new JLabel("Please enter your city: ");
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3, true),"Input");
        this.setBorder(border);
        this.add(inputLabel);
        this.add(cityInput);
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
        if(e.getKeyCode()==10){
            Forecast.cityNameEntered(cityInput.getText());
        }

    }
}
