package com.vaadin.ui.components;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.views.GirisSidebarView;


public class SideBar extends VerticalLayout {
    Container container;

    public SideBar() {

//        setWidth(100, Unit.PERCENTAGE);
//        setHeight(100, Unit.PERCENTAGE);
        this.addStyleName(ValoTheme.LAYOUT_CARD);

        GirisSidebarView girisSidebarView = new GirisSidebarView();
        addComponent(girisSidebarView);

    }

    public void setContent(Component component) {
        removeAllComponents();
        addComponent(component);
    }
}
