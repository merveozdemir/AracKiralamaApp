package com.vaadin.domain;

import com.vaadin.common.BaseDomain;
import com.vaadin.domain.enums.EnumVitesTuru;
import com.vaadin.domain.enums.EnumYakitTuru;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Audited
@Entity
@Table(name = "ARAC")
public class Arac extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    @Size(max = 50)
    private String aracMarka;

    @Column(length = 50)
    @Size(max = 50)
    private  String aracModel;

    @Column
    private int koltukSayisi;

    @Column
    private int kapiSayisi;

    @Column
    private Boolean klimali;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private EnumVitesTuru enumVitesTuru;

    @Enumerated(EnumType.STRING)
    @Column
    private EnumYakitTuru enumYakitTuru;

    @Column
    private Float gunlukKiralamaUreti;

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

    public int getKapiSayisi() {
        return kapiSayisi;
    }

    public void setKapiSayisi(int kapiSayisi) {
        this.kapiSayisi = kapiSayisi;
    }

    public Boolean isKlimali() {
        return klimali;
    }

    public void setKlimali(Boolean klimali) {
        this.klimali = klimali;
    }

    public EnumVitesTuru getEnumVitesTuru() {
        return enumVitesTuru;
    }

    public void setEnumVitesTuru(EnumVitesTuru enumVitesTuru) {
        this.enumVitesTuru = enumVitesTuru;
    }

    public EnumYakitTuru getEnumYakitTuru() {
        return enumYakitTuru;
    }

    public void setEnumYakitTuru(EnumYakitTuru enumYakitTuru) {
        this.enumYakitTuru = enumYakitTuru;
    }

    public Float getGunlukKiralamaUreti() {
        return gunlukKiralamaUreti;
    }

    public void setGunlukKiralamaUreti(Float gunlukKiralamaUreti) {
        this.gunlukKiralamaUreti = gunlukKiralamaUreti;
    }

    @Override
    public String toString() {
        return aracMarka +" "+ aracModel;
    }
}

