package com.vaadin.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by AKARTAL on 18.12.2019.
 */
public class MenuButton extends Button {

    public MenuButton(FontAwesome fontAwesome, String captionValue) {
        setIcon(fontAwesome);
        setCaption(captionValue);
        setWidth(100, Unit.PERCENTAGE);
        addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
    }

}
