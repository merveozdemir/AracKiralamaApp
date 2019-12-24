package com.vaadin.ui.views;

import com.vaadin.dao.AracDao;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;

import com.vaadin.domain.Arac;
import com.vaadin.domain.enums.EnumVitesTuru;
import com.vaadin.domain.enums.EnumYakitTuru;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.DeleteButton;
import com.vaadin.ui.components.SaveButton;
import com.vaadin.ui.components.StTextField;

import java.util.List;

public class ArabaEkleContentView extends HorizontalLayout {
    FormLayout formLayout;

    @PropertyId("id")
    StTextField idField;

    @PropertyId("aracMarka")
    StTextField markaField;

    @PropertyId("aracModel")
    StTextField modelField;

    @PropertyId("koltukSayisi")
    StTextField koltukSayisiField;

    @PropertyId("kapiSayisi")
    StTextField kapiSayisiField;

    @PropertyId("klimali")
    CheckBox klimali;

    @PropertyId("enumYakitTuru")
    ComboBox yakitTuruComboBox;

    @PropertyId("enumVitesTuru")
    ComboBox vitesTuruComboBox;

    @PropertyId("gunlukKiralamaUreti")
    StTextField gunlukKiraUcreti;

    SaveButton saveButton;
    DeleteButton deleteButton;
    Table table;
    IndexedContainer indexedContainer;
    Arac arac;
    BeanItem<Arac> item;
    FieldGroup binder;

    public ArabaEkleContentView() {
        setSpacing(true);

        buildFormLayout();
       // addComponent(formLayout);

        fillViewByArac(new Arac());

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

            if(arac.isKlimali() == true){
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
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
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
        item = new BeanItem<Arac>(arac);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
    }

    private void updateTable() {
        indexedContainer.removeAllItems();
        fillTable();
    }

    private void buildFormLayout() {
        formLayout = new FormLayout();
        addComponent(formLayout);

        idField = new StTextField("ID");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        markaField = new StTextField("Marka");
        formLayout.addComponent(markaField);

        modelField = new StTextField("Model");
        formLayout.addComponent(modelField);

        koltukSayisiField = new StTextField("Koltuk Sayısı");
        formLayout.addComponent(koltukSayisiField);

        kapiSayisiField = new StTextField("Kapı Sayısı");
        formLayout.addComponent(kapiSayisiField);

        klimali = new CheckBox("Klima");
        formLayout.addComponent(klimali);

        yakitTuruComboBox = new ComboBox("Yakıt Türü");
        fillComboBoxwithEnumYakitTuru();
        formLayout.addComponent(yakitTuruComboBox);

        vitesTuruComboBox = new ComboBox("Vites Türü");
        fillComboBoxwithEnumVitesTuru();
        formLayout.addComponent(vitesTuruComboBox);

        gunlukKiraUcreti = new StTextField("Günlük Kira Ücreti");
        formLayout.addComponent(gunlukKiraUcreti);

        saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                saveView();
                updateTable();
                fillViewByArac(new Arac());
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
                    fillViewByArac(new Arac());
            }
        });
        formLayout.addComponent(deleteButton);


    }

    private void fillComboBoxwithEnumYakitTuru() {
        for (EnumYakitTuru value : EnumYakitTuru.values()) {
            yakitTuruComboBox.addItem(value);
        }
    }

    private void fillComboBoxwithEnumVitesTuru() {
        for (EnumVitesTuru value : EnumVitesTuru.values()) {
            vitesTuruComboBox.addItem(value);
        }
    }

    private void saveView() {
        try {
            binder.commit();
            Arac arac = item.getBean();
            AracDao aracDao = new AracDao();
            arac = aracDao.araciKaydet(arac);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }

    }

    private void resetFormLayoutComponents() {
        idField.setValue("");
        markaField.setValue("");
        modelField.setValue("");
        yakitTuruComboBox.select(null);
        vitesTuruComboBox.select(null);
        kapiSayisiField.setValue("");
        koltukSayisiField.setValue("");
        gunlukKiraUcreti.setValue("");
        klimali.setValue(false);
    }
}
