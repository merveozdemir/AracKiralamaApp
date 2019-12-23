package com.vaadin.ui.views;

import com.vaadin.dao.AracDao;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.domain.Arac;
import com.vaadin.domain.enums.EnumVitesTuru;
import com.vaadin.domain.enums.EnumYakitTuru;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.components.DeleteButton;
import com.vaadin.ui.components.SaveButton;

import java.util.List;

public class ArabaEkleContentView extends HorizontalLayout {
    FormLayout formLayout;
    TextField idField;
    TextField markaField;
    TextField modelField;
    TextField koltukSayisiField;
    TextField kapiSayisiField;
    CheckBox klimali;
    ComboBox yakitTuruComboBox;
    ComboBox vitesTuruComboBox;
    TextField gunlulKiraUcreti;
    SaveButton saveButton;
    DeleteButton deleteButton;
    Table table;
    IndexedContainer indexedContainer;
    Arac arac;

    public ArabaEkleContentView() {
        setSpacing(true);

        buildFormLayout();
        addComponent(formLayout);

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

            if(arac.isKlimali().equals(true)){
                item.getItemProperty("klimali").setValue("Var");
            }else{
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
                deleteButton.setEnabled(true);
                arac = (Arac) itemClickEvent.getItemId();
                fillViewByArac(arac);

            }
        });
    }

    private void fillViewByArac(Arac arac) {
        idField.setValue(arac.getId().toString());
        markaField.setValue(arac.getAracMarka());
        modelField.setValue(arac.getAracModel());
        koltukSayisiField.setValue(String.valueOf(arac.getKoltukSayisi()));
        kapiSayisiField.setValue(String.valueOf(arac.getKapiSayisi()));
        klimali.setValue(arac.isKlimali());
        yakitTuruComboBox.select(arac.getEnumYakitTuru());
        vitesTuruComboBox.select(arac.getEnumVitesTuru());
        gunlulKiraUcreti.setValue(String.valueOf(arac.getGunlukKiralamaUreti()));

    }

    private void updateTable() {
        indexedContainer.removeAllItems();
        fillTable();
    }

    private void buildFormLayout() {
        formLayout = new FormLayout();

        idField = new TextField("ID");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        markaField = new TextField("Marka");
        formLayout.addComponent(markaField);

        modelField = new TextField("Model");
        formLayout.addComponent(modelField);

        koltukSayisiField = new TextField("Koltuk Sayısı");
        formLayout.addComponent(koltukSayisiField);

        kapiSayisiField = new TextField("Kapı Sayısı");
        formLayout.addComponent(kapiSayisiField);

        klimali = new CheckBox("Klima");
        formLayout.addComponent(klimali);

        yakitTuruComboBox = new ComboBox("Yakıt Türü");
        for (EnumYakitTuru value : EnumYakitTuru.values()) {
            yakitTuruComboBox.addItem(value);
        }
        formLayout.addComponent(yakitTuruComboBox);

        vitesTuruComboBox = new ComboBox("Vites Türü");
        for (EnumVitesTuru value : EnumVitesTuru.values()) {
            vitesTuruComboBox.addItem(value);
        }
        formLayout.addComponent(vitesTuruComboBox);

        gunlulKiraUcreti = new TextField("Günlük Kira Ücreti");
        formLayout.addComponent(gunlulKiraUcreti);

        saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                saveView();
                updateTable();
                resetFormLayoutComponents();
            }
        });
        formLayout.addComponent(saveButton);

        deleteButton = new DeleteButton();
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                    AracDao aracDao = new AracDao();
                    aracDao.deleteArac(arac);
                    updateTable();
                    resetFormLayoutComponents();
            }
        });
        formLayout.addComponent(deleteButton);


    }

    private void saveView() {
        Long idFieldValue = null;
        if (idField.getValue() != "") {
            idFieldValue = Long.parseLong(idField.getValue());
        }

        Arac arac = new Arac();
        arac.setId(idFieldValue);
        arac.setAracMarka(markaField.getValue());
        arac.setAracModel(modelField.getValue());
        arac.setEnumYakitTuru((EnumYakitTuru) yakitTuruComboBox.getValue());
        arac.setEnumVitesTuru((EnumVitesTuru) vitesTuruComboBox.getValue());
        arac.setKapiSayisi(Integer.valueOf(kapiSayisiField.getValue()));
        arac.setKoltukSayisi(Integer.valueOf(koltukSayisiField.getValue()));
        arac.setKlimali(klimali.getValue());
        arac.setGunlukKiralamaUreti(Float.parseFloat(gunlulKiraUcreti.getValue()));

        AracDao aracDao = new AracDao();
        arac = aracDao.araciKaydet(arac);
    }

    private void resetFormLayoutComponents() {
        idField.setValue("");
        markaField.setValue("");
        modelField.setValue("");
        yakitTuruComboBox.select(null);
        vitesTuruComboBox.select(null);
        kapiSayisiField.setValue("");
        koltukSayisiField.setValue("");
        gunlulKiraUcreti.setValue("");
        klimali.setValue(false);
    }
}
