package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.domain.Kullanici;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.Content;
import com.vaadin.ui.components.General;
import com.vaadin.ui.components.Header;
import com.vaadin.ui.components.SideBar;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.vaadin.MyAppWidgetset")
public class MyUI extends UI {
    private Kullanici myKullanici;
    private SideBar mySideBar;

    private Content myContent;
    private Header myHeader;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        General general = new General();
        setContent(general);
    }

    public SideBar getMySideBar() {
        return mySideBar;
    }

    public void setMySideBar(SideBar mySideBar) {
        this.mySideBar = mySideBar;
    }

    public Content getMyContent() {
        return myContent;
    }

    public void setMyContent(Content myContent) {
        this.myContent = myContent;
    }

    public Header getMyHeader() {
        return myHeader;
    }

    public void setMyHeader(Header myHeader) {
        this.myHeader = myHeader;
    }


    public Kullanici getMyKullanici() {
        return myKullanici;
    }

    public void setMyKullanici(Kullanici myKullanici) {
        this.myKullanici = myKullanici;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
