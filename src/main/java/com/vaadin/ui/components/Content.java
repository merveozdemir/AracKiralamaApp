package com.vaadin.ui.components;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class Content extends VerticalLayout {

    public Content() {
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);

    }

    public void setContent(Component component){
        removeAllComponents();
        addComponent(component);
    }
}
