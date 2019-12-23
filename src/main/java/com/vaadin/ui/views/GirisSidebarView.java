package com.vaadin.ui.views;

import com.vaadin.MyUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.MenuButton;
import com.vaadin.ui.components.SideBarBaslikLabel;
import com.vaadin.ui.themes.ValoTheme;

public class GirisSidebarView extends VerticalLayout {

    public GirisSidebarView() {
        MyUI myUI = (MyUI)UI.getCurrent();

        SideBarBaslikLabel label = new SideBarBaslikLabel("");
        addComponent(label);

        setMargin(true);
        setSpacing(true);

        MenuButton uyeGirisButton = new MenuButton(FontAwesome.SIGN_IN, "Üye Girişi");
        uyeGirisButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                KullaniciGirisContentView kullaniciGirisContentView = new KullaniciGirisContentView();
                myUI.getMyContent().setContent(kullaniciGirisContentView);
            }
        });
        addComponent(uyeGirisButton);


//        MenuButton uyeOlButton = new MenuButton(FontAwesome.USER_PLUS, "Üye Ol");
//        uyeOlButton.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//                KullaniciEkleContentView kullaniciEkleContentView = new KullaniciEkleContentView();
//                myUI.getMyContent().setContent(kullaniciEkleContentView);
//            }
//        });
//        addComponent(uyeOlButton);

    }
}
