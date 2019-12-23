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

public class AdminSidebarView extends VerticalLayout {

    public AdminSidebarView(Kullanici kullanici) {
        MyUI myUI = (MyUI) UI.getCurrent();

        SideBarBaslikLabel label = new SideBarBaslikLabel(kullanici.toString());
        addComponent(label);

        setMargin(true);
        setSpacing(true);

        MenuButton arabaEkleButton = new MenuButton(FontAwesome.PLUS_SQUARE, "Araba Ekle");
        arabaEkleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                    ArabaEkleContentView arabaEkleContentView = new ArabaEkleContentView();
                    myUI.getMyContent().setContent(arabaEkleContentView);
            }
        });
        addComponent(arabaEkleButton);

        MenuButton kiralananAraclar = new MenuButton(FontAwesome.USER_PLUS, "Kiralanan Araçlar");
        kiralananAraclar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
              KiralananAraclarContentView kiralananAraclarContentView = new KiralananAraclarContentView();
              myUI.getMyContent().setContent(kiralananAraclarContentView);
            }
        });
        addComponent(kiralananAraclar);

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
