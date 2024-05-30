package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ZUCompos")
public class ZUCompos {

    @Id
    private String refunite;

    private Integer refche;

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