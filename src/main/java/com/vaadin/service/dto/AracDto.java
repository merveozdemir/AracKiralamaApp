package com.vaadin.service.dto;

public class AracDto {
    private Long id;
    private String aracMarka;
    private String aracModel;
    private int koltukSayisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAracMarka() {
        return aracMarka;
    }

    public void setAracMarka(String aracMarka) {
        this.aracMarka = aracMarka;
    }

    public String getAracModel() {
        return aracModel;
    }

    public void setAracModel(String aracModel) {
        this.aracModel = aracModel;
    }

    public int getKoltukSayisi() {
        return koltukSayisi;
    }

    public void setKoltukSayisi(int koltukSayisi) {
        this.koltukSayisi = koltukSayisi;
    }
}
