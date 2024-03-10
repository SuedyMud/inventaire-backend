package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ZUSFI")
public class ZUSFI {

    @Id
    @Column(name = "Refunite", length = 6, nullable = false)
    private String refunite;

    @Column(name = "Refdepart", nullable = false)
    private int refdepart;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    // Getters et setters avec data
}