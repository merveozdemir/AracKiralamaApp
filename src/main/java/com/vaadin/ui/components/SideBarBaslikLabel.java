package com.vaadin.ui.components;

import com.vaadin.domain.Kullanici;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class SideBarBaslikLabel extends Label {

    public SideBarBaslikLabel(String value) {
        setValue("HOŞGELDİNİZ "+ value);
        addStyleName(ValoTheme.LABEL_H2);
        addStyleName(ValoTheme.LABEL_COLORED);
    }
}
