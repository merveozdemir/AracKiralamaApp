package com.vaadin.ui.views;

import com.vaadin.MyUI;
import com.vaadin.dao.KiralamaDao;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.domain.Arac;
import com.vaadin.domain.enums.EnumAlisOfisi;
import com.vaadin.domain.Kiralama;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

import java.util.Date;
import java.util.List;

public class UyeKiralananAraclarContentView extends BaseKiralananAracListeleme {
    
    protected void buildTableContainer() {
        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("arac", Arac.class, null);
        indexedContainer.addContainerProperty("baslangicTarihi", Date.class, null);
        indexedContainer.addContainerProperty("bitisTarihi", Date.class, null);
        indexedContainer.addContainerProperty("alisOfisi", EnumAlisOfisi.class, null);

    }

    protected void fillTable() {
        MyUI myUI = (MyUI) UI.getCurrent();
        KiralamaDao kiralamaDao = new KiralamaDao();
        List<Kiralama> kiralamaList = kiralamaDao.uyeyeGoreKiralamalariGetir(myUI.getMyKullanici().getId());
        for (Kiralama kiralama : kiralamaList) {
            Item item = indexedContainer.addItem(kiralama);
            item.getItemProperty("id").setValue(kiralama.getId());
            item.getItemProperty("arac").setValue(kiralama.getArac());
            item.getItemProperty("baslangicTarihi").setValue(kiralama.getKiralamaBaslangicTarihi());
            item.getItemProperty("bitisTarihi").setValue(kiralama.getKiralamaBitisTarihi());
            item.getItemProperty("alisOfisi").setValue(kiralama.getEnumAlisOfisi());

        }
    }

    protected void buildTable() {
        table = new Table();
        table.setHeight("400px");
       // table.setWidth("650px");
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("KİRALAMA NO", "ARAÇ", "BAŞLANGIÇ TARİHİ", "BİTİŞ TARİHİ", "ALIŞ OFİSİ");
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                kiralama = (Kiralama) itemClickEvent.getItemId();
            }
        });
    }

}
