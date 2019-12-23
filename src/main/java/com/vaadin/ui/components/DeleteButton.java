package com.vaadin.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

public class DeleteButton extends Button {
    public DeleteButton() {
        setIcon(FontAwesome.TRASH);
        setCaption("Sil");
    }
}
