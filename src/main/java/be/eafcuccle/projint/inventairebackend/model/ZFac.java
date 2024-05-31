package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "zfac")
public class ZFac {

    @Id
    @Column(name = "Fac", length = 11, nullable = false)
    private String fac;

    @Column(name = "Faculte", length = 70)
    private String faculte;

    @Column(name = "FaculteUK", length = 70, nullable = false)
    private String faculteUK;

    @Column(name = "Sigle", length = 16, nullable = false)
    private String sigle;

    @Column(name = "DMaj", nullable = false)
    private Date dMaj;

    @Column(name = "CC", length = 10, nullable = false)
    private String cc;

    @Column(name = "Infofin", nullable = false)
    private int infofin;

    @Column(name = "IdFac", nullable = false)
    private int idFac;

    @Column(name = "Actif", nullable = false)
    private int actif;

    @Column(name = "Groupe", length = 20, nullable = false)
    private String groupe;

    @Column(name = "Invent20", nullable = false)
    private int invent20;


    @OneToMany(mappedBy = "zfac")
    Set<ZUFac> zufac;



    public ZFac() {
    }

    // Getters et setters avec data


}