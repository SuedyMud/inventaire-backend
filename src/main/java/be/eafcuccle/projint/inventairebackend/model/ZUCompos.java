package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ZUCompos")
public class ZUCompos {

    @EmbeddedId
    private ZUComposId id;


    @ManyToOne
    @MapsId("zUniteId")
    @JoinColumn(name = "refunite", referencedColumnName = "idunite", nullable = false)
    private ZUnite zunite;


    @ManyToOne
    @MapsId("zChercheurId")
    @JoinColumn(name = "refche", referencedColumnName = "idche", nullable = false)
    private ZChercheur zchercheur;

    @Column(name = "Responsable", length = 3, nullable = false, columnDefinition = "varchar(3) COMMENT 'Dec : décédé Dci : délégué interne Dce : délégué externe'")
    private String responsable;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    @Column(name = "Ordre", nullable = false, columnDefinition = "int default 0")
    private int ordre;

    // Getters et setters avec data
}