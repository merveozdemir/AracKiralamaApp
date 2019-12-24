package com.vaadin.ui.views;

import com.vaadin.MyUI;
import com.vaadin.dao.AracDao;
import com.vaadin.dao.KiralamaDao;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.domain.Arac;
import com.vaadin.domain.enums.EnumAlisOfisi;
import com.vaadin.domain.enums.EnumVitesTuru;
import com.vaadin.domain.enums.EnumYakitTuru;
import com.vaadin.domain.Kiralama;
import com.vaadin.domain.Kullanici;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.SaveButton;

import java.util.List;

public class ArabaKiralaContentView extends HorizontalLayout {
    FormLayout formLayout;
    TextField idField;

    @PropertyId("kiralamaBaslangicTarihi")
    DateField kiralamaBaslangicField;

    @PropertyId("kiralamaBitisTarihi")
    DateField kiralamaBitisField;

    @PropertyId("enumAlisOfisi")
    ComboBox alisOfisiCombobox;

    Table table;
    IndexedContainer indexedContainer;
    Arac arac;
    VerticalLayout formAndTextLayout;
    BeanItem<Kiralama> item;
    FieldGroup binder;

    public ArabaKiralaContentView() {
        setSpacing(true);

        buildFormAndTextLayout();
        addComponent(formAndTextLayout);

        fillViewByKiralama(new Kiralama());

        buildTableContainer();
        buildTable();
        fillTable();
        addComponent(table);
    }

    private void fillViewByKiralama(Kiralama kiralama) {
        item = new BeanItem<Kiralama>(kiralama);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);
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

            if (arac.isKlimali() == (true)) {
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
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.setColumnHeaders("NO", "MARKA", "MODEL", "KAPI SAYISI", "KOLTUK SAYISI", "YAKIT TÜRÜ", "VİTES TÜRÜ", "KLİMA DURUMU", "GÜNLÜK KİRA ÜCRETİ");
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                arac = (Arac) itemClickEvent.getItemId();
                idField.setValue(arac.toString());
            }
        });
    }

    private void buildFormAndTextLayout() {
        formAndTextLayout = new VerticalLayout();

        formLayout = new FormLayout();
        formAndTextLayout.addComponent(formLayout);

        idField = new TextField("Araba ");
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
               saveView();
//                TextArea bilgilendirmeArea = new TextArea();
//                bilgilendirmeArea.setWidth("350px");
//                bilgilendirmeArea.setEnabled(false);
//                bilgilendirmeArea.setValue("Kiralama Numaranız: " + kiralama.getId() + " \n" +
//                        "Araç numarası: " + kiralama.getArac().getId() +
//                        "\nBaşlangıç Tarihi: " + kiralama.getKiralamaBaslangicTarihi() +
//                        "\nBitiş Tarihi: " + kiralama.getKiralamaBitisTarihi().toString() +
//                        "\nAlış Ofisi: " + kiralama.getEnumAlisOfisi());
//                formAndTextLayout.addComponent(bilgilendirmeArea);
            }
        });
        formLayout.addComponent(saveButton);

    }

    private void saveView() {

        try {
            binder.commit();
            MyUI myUI = (MyUI) UI.getCurrent();
            Kullanici kullanici = myUI.getMyKullanici();

            Kiralama kiralama = item.getBean();
            kiralama.setArac(arac);
            kiralama.setKullanici(kullanici);

            KiralamaDao kiralamaDao = new KiralamaDao();
            kiralama = kiralamaDao.kiralamaKaydet(kiralama);
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }

    }
}
