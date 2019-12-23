package com.vaadin.domain;

import com.google.web.bindery.autobean.shared.impl.EnumMap;
import com.vaadin.MyUI;
import com.vaadin.common.BaseDomain;
import com.vaadin.domain.Enums.EnumKullaniciTuru;
import com.vaadin.ui.UI;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.jsoup.Connection;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "KULLANICI")
public class Kullanici extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    @Size(max = 50)
    private String adi;

    @Column(length = 50)
    @Size(max = 50)
    private String soyadi;

    @NotNull
    @Column(length = 50, unique = true)
    @Size(max = 50)
    private String email;

    @Column(length = 11)
    @Size(max = 11)
    private String telNo;

    @NotNull
    @Column(length = 50)
    @Size(max = 50)
    private String sifre;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private EnumKullaniciTuru enumKullaniciTuru;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public EnumKullaniciTuru getEnumKullaniciTuru() {
        return enumKullaniciTuru;
    }

    public void setEnumKullaniciTuru(EnumKullaniciTuru enumKullaniciTuru) {
        this.enumKullaniciTuru = enumKullaniciTuru;
    }

    @Override
    public String toString() {
        return adi + " "+ soyadi;
    }
}
