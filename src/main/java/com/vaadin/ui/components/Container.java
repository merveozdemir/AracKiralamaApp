package com.vaadin.ui.components;

import com.vaadin.MyUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Container extends VerticalLayout {

    public Container() {
        MyUI myUI = (MyUI) UI.getCurrent();

        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);
        setMargin(true);

        Header header = new Header();
        addComponent(header);
        myUI.setMyHeader(header);

        Content content = new Content();
        addComponent(content);
        myUI.setMyContent(content);

        setExpandRatio(header, 1f);
        setExpandRatio(content, 9f);

    }
}
