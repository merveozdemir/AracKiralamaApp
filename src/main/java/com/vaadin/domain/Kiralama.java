package com.vaadin.domain;

import com.vaadin.common.BaseDomain;
import com.vaadin.domain.enums.EnumAlisOfisi;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "KIRALAMA")
public class Kiralama extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ARAC", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "KIRALAMA_ARAC_ID"))
    private Arac arac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "KIRALAMA_KULLANICI_ID"))
    private Kullanici kullanici;

    @Temporal(TemporalType.DATE)
    private Date kiralamaBaslangicTarihi;

    @Temporal(TemporalType.DATE)
    private Date kiralamaBitisTarihi;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumAlisOfisi enumAlisOfisi;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public Date getKiralamaBaslangicTarihi() {
        return kiralamaBaslangicTarihi;
    }

    public void setKiralamaBaslangicTarihi(Date kiralamaBaslangicTarihi) {
        this.kiralamaBaslangicTarihi = kiralamaBaslangicTarihi;
    }

    public Date getKiralamaBitisTarihi() {
        return kiralamaBitisTarihi;
    }

    public void setKiralamaBitisTarihi(Date kiralamaBitisTarihi) {
        this.kiralamaBitisTarihi = kiralamaBitisTarihi;
    }

    public EnumAlisOfisi getEnumAlisOfisi() {
        return enumAlisOfisi;
    }

    public void setEnumAlisOfisi(EnumAlisOfisi enumAlisOfisi) {
        this.enumAlisOfisi = enumAlisOfisi;
    }
}
