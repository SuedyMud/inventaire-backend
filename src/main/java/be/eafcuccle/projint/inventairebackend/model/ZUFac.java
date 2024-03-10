package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ZUFac")
public class ZUFac {

    @Id
    @Column(name = "Refunite", nullable = false)
    private String refunite;

    @Column(name = "Reffac", nullable = false)
    private String reffac;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    // Getters et setters avec data
}