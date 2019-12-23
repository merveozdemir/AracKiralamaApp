package com.vaadin.ui.views;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.domain.Kiralama;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseKiralananAracListeleme extends VerticalLayout {
    Table table;
    IndexedContainer indexedContainer;
    Kiralama kiralama;

    public BaseKiralananAracListeleme() {
        setSpacing(true);

        buildTableContainer();
        buildTable();
        fillTable();
        addComponent(table);

    }


    protected abstract void fillTable();
    protected abstract void buildTable();
    protected abstract void buildTableContainer();
}
