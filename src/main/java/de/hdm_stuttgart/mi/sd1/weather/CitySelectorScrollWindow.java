package de.hdm_stuttgart.mi.sd1.weather;

import javax.swing.*;

public class CitySelectorScrollWindow extends JScrollPane {

    public CitySelectorScrollWindow(CitySelectorWindow citySelectorWindow) {
        this.add(citySelectorWindow);
        citySelectorWindow.setVisible(true);
        citySelectorWindow.setSize(1000, 1000);
    }

}
