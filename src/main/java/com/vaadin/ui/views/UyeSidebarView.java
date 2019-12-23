package com.vaadin.ui.views;

import com.vaadin.MyUI;
import com.vaadin.domain.Kullanici;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.MenuButton;
import com.vaadin.ui.components.SideBarBaslikLabel;
import com.vaadin.ui.themes.ValoTheme;

public class UyeSidebarView extends VerticalLayout {
    public UyeSidebarView(Kullanici kullanici) {
        MyUI myUI = (MyUI) UI.getCurrent();

        SideBarBaslikLabel label = new SideBarBaslikLabel(kullanici.toString());
        addComponent(label);

        setMargin(true);
        setSpacing(true);

        MenuButton arabaKiralaButton = new MenuButton(FontAwesome.PLUS_SQUARE, "Araba Kirala");
        arabaKiralaButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ArabaKiralaContentView arabaKiralaContentView = new ArabaKiralaContentView();
                myUI.getMyContent().setContent(arabaKiralaContentView);
            }
        });
        addComponent(arabaKiralaButton);

        MenuButton kiraladigimAraclarButton = new MenuButton(FontAwesome.CAR, "Kiraladığım Araçlar");
        kiraladigimAraclarButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
               UyeKiralananAraclarContentView kiralananAraclarContentView = new UyeKiralananAraclarContentView();
               myUI.getMyContent().setContent(kiralananAraclarContentView);
            }
        });
        addComponent(kiraladigimAraclarButton);

//        MenuButton profilGoruntuleButton = new MenuButton(FontAwesome.USER, "Profil Görüntüle");
//        profilGoruntuleButton.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//
//            }
//        });
//        addComponent(profilGoruntuleButton);

        MenuButton cikisButton = new MenuButton(FontAwesome.SIGN_OUT, "Çıkış");
        cikisButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                GirisSidebarView girisSidebarView = new GirisSidebarView();
                myUI.getMySideBar().setContent(girisSidebarView);
                myUI.getMyContent().removeAllComponents();
            }
        });
        addComponent(cikisButton);
    }

}
