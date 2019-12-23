package com.vaadin.ui.components;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Header extends HorizontalLayout {
    Label label;

    public Header() {
        setWidth(100, Unit.PERCENTAGE);
        this.addStyleName(ValoTheme.LAYOUT_CARD);

        label = new Label("ARAÇ KİRALAMA UYGULAMASI");
        label.addStyleName(ValoTheme.LABEL_HUGE);
        label.addStyleName(ValoTheme.LABEL_BOLD);
        label.addStyleName(ValoTheme.LABEL_COLORED);
        label.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
        addComponent(label);

    }

    void setHeaderTitle(String headerTitle) {
        label.setValue(headerTitle);
    }

}
