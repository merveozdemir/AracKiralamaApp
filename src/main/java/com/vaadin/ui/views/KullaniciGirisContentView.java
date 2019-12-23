package com.vaadin.ui.views;

import com.vaadin.MyUI;
import com.vaadin.dao.KullaniciDao;
import com.vaadin.domain.Enums.EnumKullaniciTuru;
import com.vaadin.domain.Kullanici;
import com.vaadin.ui.*;
import com.vaadin.ui.components.LoginButton;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

import static com.vaadin.domain.Enums.EnumKullaniciTuru.Admin;

public class KullaniciGirisContentView extends VerticalLayout {
    FormLayout formLayout;

    public KullaniciGirisContentView() {
        buildFormLayout();
    }

    private void buildFormLayout() {
        MyUI myUI = (MyUI) UI.getCurrent();

        formLayout = new FormLayout();
        addComponent(formLayout);

        TextField emailField = new TextField("E-posta");
        formLayout.addComponent(emailField);

        PasswordField passwordField = new PasswordField("Şifre");
        formLayout.addComponent(passwordField);

        LoginButton loginButton = new LoginButton();
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                KullaniciDao kullaniciDao = new KullaniciDao();
                Kullanici kullanici = kullaniciDao.kullaniciyiGetir(emailField.getValue(), passwordField.getValue());
                EnumKullaniciTuru enumKullaniciTuru = kullanici.getEnumKullaniciTuru();
                myUI.setMyKullanici(kullanici);
                myUI.getMyContent().removeAllComponents();

                switch (enumKullaniciTuru) {
                    case Admin:
                        AdminSidebarView adminSidebarView = new AdminSidebarView(kullanici);
                        myUI.getMySideBar().setContent(adminSidebarView);
                        break;
                    case Uye:
                        UyeSidebarView uyeSidebarView = new UyeSidebarView(kullanici);
                        myUI.getMySideBar().setContent(uyeSidebarView);
                        break;
                }

                //myUI.getMySideBar().setContent();
            }
      });
        formLayout.addComponent(loginButton);

    }
}
