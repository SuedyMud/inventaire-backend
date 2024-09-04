package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "zufac")
public class ZUFac {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Refunite",referencedColumnName = "idunite")
    private ZUnite zunite;

    @ManyToOne
    @JoinColumn(name = "Reffac", referencedColumnName = "Fac")
    private ZFac zfac;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;


    public ZUFac() {
    }

    public ZFac getFac() {
        return zfac;
    }

    // Getters et setters avec data

}