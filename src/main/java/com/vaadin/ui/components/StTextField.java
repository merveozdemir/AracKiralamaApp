package com.vaadin.ui.components;

import com.vaadin.ui.TextField;

public class StTextField extends TextField {

    public StTextField() {
        addStyleName("st-text-field");
        setNullRepresentation("");

    }

    public StTextField(String caption) {
        this();
        setCaption(caption);
    }
}
