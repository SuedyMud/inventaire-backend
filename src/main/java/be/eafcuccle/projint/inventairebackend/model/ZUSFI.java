package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "zusfi")
public class ZUSFI {

    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Refunite", referencedColumnName = "idunite")
    private ZUnite zunite;
    @ManyToOne
    @JoinColumn(name = "Refdepart", referencedColumnName = "iddepart")
    private ZSFI zsfi;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    // Getters et setters avec data
}