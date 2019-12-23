package com.vaadin.ui.views;

import com.vaadin.MyUI;
import com.vaadin.dao.KiralamaDao;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.domain.Arac;
import com.vaadin.domain.Enums.EnumAlisOfisi;
import com.vaadin.domain.Kiralama;
import com.vaadin.domain.Kullanici;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.DeleteButton;

import java.util.Date;
import java.util.List;

public class KiralananAraclarContentView extends BaseKiralananAracListeleme {
    DeleteButton deleteButton;

    public KiralananAraclarContentView() {
        super();

        buildDeleteButton();
        addComponent(deleteButton);

    }

    void buildDeleteButton(){
        deleteButton = new DeleteButton();
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                KiralamaDao kiralamaDao = new KiralamaDao();
                kiralamaDao.deleteKiralama(kiralama);
                updateTable();
            }
        });

    }

    protected void buildTableContainer() {
        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("arac", Arac.class, null);
        indexedContainer.addContainerProperty("kullanici", Kullanici.class, null);
        indexedContainer.addContainerProperty("baslangicTarihi", Date.class, null);
        indexedContainer.addContainerProperty("bitisTarihi", Date.class, null);
        indexedContainer.addContainerProperty("alisOfisi", EnumAlisOfisi.class, null);

    }

    protected void fillTable() {
        KiralamaDao kiralamaDao = new KiralamaDao();
        List<Kiralama> kiralamaList = kiralamaDao.tumKiralamalariGetir();
        for (Kiralama kiralama : kiralamaList) {
            Item item = indexedContainer.addItem(kiralama);
            item.getItemProperty("id").setValue(kiralama.getId());
            item.getItemProperty("arac").setValue(kiralama.getArac());
            item.getItemProperty("kullanici").setValue(kiralama.getKullanici());
            item.getItemProperty("baslangicTarihi").setValue(kiralama.getKiralamaBaslangicTarihi());
            item.getItemProperty("bitisTarihi").setValue(kiralama.getKiralamaBitisTarihi());
            item.getItemProperty("alisOfisi").setValue(kiralama.getEnumAlisOfisi());

        }
    }

    protected void buildTable() {
        table = new Table();
        table.setHeight("400px");
        table.setContainerDataSource(indexedContainer);
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.setColumnHeaders("KİRALAMA NO", "ARAÇ", "KULLANICI", "BAŞLANGIÇ TARİHİ", "BİTİŞ TARİHİ", "ALIŞ OFİSİ");
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                kiralama = (Kiralama) itemClickEvent.getItemId();

            }
        });
    }

    private void updateTable() {
        indexedContainer.removeAllItems();
        fillTable();
    }

}
