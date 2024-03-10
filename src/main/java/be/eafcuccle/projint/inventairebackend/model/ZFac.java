package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ZFac")
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

    // Getters et setters avec data


    public ZFac() {
    }

    public ZFac(String faculte, String faculteUK, String sigle, Date dMaj, String cc, int infofin, int idFac, int actif, String groupe, int invent20) {
        this.faculte = faculte;
        this.faculteUK = faculteUK;
        this.sigle = sigle;
        this.dMaj = dMaj;
        this.cc = cc;
        this.infofin = infofin;
        this.idFac = idFac;
        this.actif = actif;
        this.groupe = groupe;
        this.invent20 = invent20;
    }

    public ZFac(String fac, String faculte, String faculteUK, String sigle, Date dMaj, String cc, int infofin, int idFac, int actif, String groupe, int invent20) {
        this.fac = fac;
        this.faculte = faculte;
        this.faculteUK = faculteUK;
        this.sigle = sigle;
        this.dMaj = dMaj;
        this.cc = cc;
        this.infofin = infofin;
        this.idFac = idFac;
        this.actif = actif;
        this.groupe = groupe;
        this.invent20 = invent20;
    }
}