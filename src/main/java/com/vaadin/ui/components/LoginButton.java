package com.vaadin.ui.components;


import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

public class LoginButton extends Button {

    public LoginButton() {
        setIcon(FontAwesome.SIGN_IN);
        setCaption("Giriş");
    }
}
