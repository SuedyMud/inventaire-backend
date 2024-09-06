package be.eafcuccle.projint.inventairebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "zufac")
public class ZUFac {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Refunite",referencedColumnName = "idunite")
    private ZUnite zunite;

    @JsonIgnore // Ajoutez cette annotation pour éviter la récursion

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