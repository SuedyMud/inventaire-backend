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
    @JoinColumn(name = "Refunite", referencedColumnName = "Idunite")
    private ZUnite zunite;

    @ManyToOne
    @JoinColumn(name = "Refche", referencedColumnName = "Idche")
    private ZChercheur zchercheur;

    @Column(name = "Responsable", length = 3, nullable = false, columnDefinition = "varchar(3) COMMENT 'Dec : décédé Dci : délégué interne Dce : délégué externe'")
    private String responsable;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    @Column(name = "Ordre", nullable = false, columnDefinition = "int default 0")
    private Integer ordre;

    // Getter et setter pour 'chercheur'
    public ZChercheur getChercheur() {
        return zchercheur;
    }

    public void setChercheur(ZChercheur chercheur) {
        this.zchercheur = zchercheur;
    }
    public ZUCompos() {
    }

    // Getters et setters avec data
}