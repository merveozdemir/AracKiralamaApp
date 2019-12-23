package com.vaadin.ui.views;

import com.sun.javafx.binding.StringFormatter;
import com.vaadin.MyUI;
import com.vaadin.dao.AracDao;
import com.vaadin.dao.KiralamaDao;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.domain.Arac;
import com.vaadin.domain.Enums.EnumAlisOfisi;
import com.vaadin.domain.Enums.EnumVitesTuru;
import com.vaadin.domain.Enums.EnumYakitTuru;
import com.vaadin.domain.Kiralama;
import com.vaadin.domain.Kullanici;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.components.SaveButton;

import java.util.List;

public class ArabaKiralaContentView extends HorizontalLayout {
    FormLayout formLayout;
    TextField idField;
    DateField kiralamaBaslangicField;
    DateField kiralamaBitisField;
    Table table;
    IndexedContainer indexedContainer;
    Arac arac;
    VerticalLayout formAndTextLayout;
    ComboBox alisOfisiCombobox;


    public ArabaKiralaContentView() {
        setSpacing(true);

        buildFormAndTextLayout();
        addComponent(formAndTextLayout);

        buildTableContainer();
        buildTable();
        fillTable();
        addComponent(table);
    }

    private void buildTableContainer() {
        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("marka", String.class, null);
        indexedContainer.addContainerProperty("model", String.class, null);
        indexedContainer.addContainerProperty("kapiSayisi", Integer.class, null);
        indexedContainer.addContainerProperty("koltukSayisi", Integer.class, null);
        indexedContainer.addContainerProperty("yakitTuru", EnumYakitTuru.class, null);
        indexedContainer.addContainerProperty("vitesTuru", EnumVitesTuru.class, null);
        indexedContainer.addContainerProperty("klimali", String.class, null);
        indexedContainer.addContainerProperty("gunlukKiraUcreti", Float.class, null);
    }

    private void fillTable() {
        AracDao aracDao = new AracDao();
        List<Arac> aracList = aracDao.tumAraclariGetir();
        for (Arac arac : aracList) {
            Item item = indexedContainer.addItem(arac);
            item.getItemProperty("id").setValue(arac.getId());
            item.getItemProperty("marka").setValue(arac.getAracMarka());
            item.getItemProperty("model").setValue(arac.getAracModel());
            item.getItemProperty("kapiSayisi").setValue(arac.getKapiSayisi());
            item.getItemProperty("koltukSayisi").setValue(arac.getKoltukSayisi());
            item.getItemProperty("yakitTuru").setValue(arac.getEnumYakitTuru());
            item.getItemProperty("vitesTuru").setValue(arac.getEnumVitesTuru());

            if (arac.isKlimali().equals(true)) {
                item.getItemProperty("klimali").setValue("Var");
            } else {
                item.getItemProperty("klimali").setValue("Yok");
            }

            item.getItemProperty("gunlukKiraUcreti").setValue(arac.getGunlukKiralamaUreti());
        }
    }

    private void buildTable() {
        table = new Table();
        table.setHeight("400px");
        table.setWidth("650px");
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("NO", "MARKA", "MODEL", "KAPI SAYISI", "KOLTUK SAYISI", "YAKIT TÜRÜ", "VİTES TÜRÜ", "KLİMA DURUMU", "GÜNLÜK KİRA ÜCRETİ");
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                arac = (Arac) itemClickEvent.getItemId();
                idField.setValue(arac.getId().toString());
            }
        });
    }

    private void buildFormAndTextLayout() {
        formAndTextLayout = new VerticalLayout();
        formLayout = new FormLayout();
        formAndTextLayout.addComponent(formLayout);


        idField = new TextField("Araba No");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        kiralamaBaslangicField = new DateField("Kiralama Başlangıç Tarihi");
        formLayout.addComponent(kiralamaBaslangicField);

        kiralamaBitisField = new DateField("Kiralama Bitiş Tarihi");
        formLayout.addComponent(kiralamaBitisField);

        alisOfisiCombobox = new ComboBox("Alış Ofisi");
        for (EnumAlisOfisi value : EnumAlisOfisi.values()) {
            alisOfisiCombobox.addItem(value);
        }
        formLayout.addComponent(alisOfisiCombobox);

        SaveButton saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Kiralama kiralama = saveView();

                TextArea bilgilendirmeArea = new TextArea();
                bilgilendirmeArea.setWidth("350px");
                bilgilendirmeArea.setEnabled(false);
                bilgilendirmeArea.setValue("Kiralama Numaranız: " + kiralama.getId() + " \n" +
                        "Araç numarası: " + kiralama.getArac().getId() +
                        "\nBaşlangıç Tarihi: " + kiralama.getKiralamaBaslangicTarihi() +
                        "\nBitiş Tarihi: " + kiralama.getKiralamaBitisTarihi().toString() +
                        "\nAlış Ofisi: " + kiralama.getEnumAlisOfisi());
                formAndTextLayout.addComponent(bilgilendirmeArea);
            }
        });
        formLayout.addComponent(saveButton);

    }

    private Kiralama saveView() {
        MyUI myUI = (MyUI) UI.getCurrent();
        Kullanici kullanici = myUI.getMyKullanici();

        Kiralama kiralama = new Kiralama();
        kiralama.setArac(arac);
        kiralama.setKullanici(kullanici);
        kiralama.setKiralamaBaslangicTarihi(kiralamaBaslangicField.getValue());
        kiralama.setKiralamaBitisTarihi(kiralamaBitisField.getValue());
        kiralama.setEnumAlisOfisi((EnumAlisOfisi) alisOfisiCombobox.getValue());

        KiralamaDao kiralamaDao = new KiralamaDao();
        kiralama = kiralamaDao.kiralamaKaydet(kiralama);
        return kiralama;
    }
}
