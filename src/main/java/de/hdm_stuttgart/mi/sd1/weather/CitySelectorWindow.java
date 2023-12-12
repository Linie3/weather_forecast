package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.City;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CitySelectorWindow extends JPanel implements ActionListener {
    private City[] cities;
    private JRadioButton[] radioButtons;
    public CitySelectorWindow(City[] cities){

        this.cities = cities;
        radioButtons = new JRadioButton[cities.length];

        for (int i = 0; i < cities.length; i++){
            radioButtons[i] = new JRadioButton(cities[i].getName());
        }
        this.setLayout(new GridLayout(radioButtons.length, 1,3,3));
        ButtonGroup buttonGroup = new ButtonGroup();

        for (JRadioButton jb : radioButtons){
            buttonGroup.add(jb);
            this.add(jb);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
