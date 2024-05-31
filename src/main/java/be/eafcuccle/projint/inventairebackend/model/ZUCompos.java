package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "zucompos")
public class ZUCompos {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "refunite", referencedColumnName = "idunite")
    private ZUnite zunite;

    @ManyToOne
    @JoinColumn(name = "refche", referencedColumnName = "idche")
    private ZChercheur zchercheur;

    @Column(name = "Responsable", length = 3, nullable = false, columnDefinition = "varchar(3) COMMENT 'Dec : décédé Dci : délégué interne Dce : délégué externe'")
    private String responsable;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    @Column(name = "Ordre", nullable = false, columnDefinition = "int default 0")
    private int ordre;

    public ZChercheur getChercheur() {
        return this.zchercheur;
    }

    public ZUnite getZUnite() {
        return this.zunite;
    }

    public ZUCompos() {
    }

    // Getters et setters avec data
}