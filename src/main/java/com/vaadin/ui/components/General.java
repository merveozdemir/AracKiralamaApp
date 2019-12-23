package com.vaadin.ui.components;

import com.vaadin.MyUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class General extends HorizontalLayout {

    public General() {
        MyUI myUI = (MyUI) UI.getCurrent();

        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);

        SideBar sideBar = new SideBar();
        addComponent(sideBar);
        myUI.setMySideBar(sideBar);

        Container container = new Container();
        addComponent(container);

        setExpandRatio(sideBar,2f);
        setExpandRatio(container, 8f);

    }
}
