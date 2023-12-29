package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ZSFI")
public class ZSFI {

    @Id
    @Column(name = "Iddepart", nullable = false)
    private int iddepart;

    @Column(name = "Depart", length = 100, nullable = false, columnDefinition = "varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci")
    private String depart;

    @Column(name = "DepartUK", length = 100, nullable = false, columnDefinition = "varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci")
    private String departUK;

    @Column(name = "Reffac", length = 11, nullable = false, columnDefinition = "varchar(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci")
    private String reffac;

    @Column(name = "Ordre", nullable = false)
    private int ordre;

    // Getters et setters avec data


    public ZSFI() {
    }

    public ZSFI(String depart, String departUK, String reffac, int ordre) {
        this.depart = depart;
        this.departUK = departUK;
        this.reffac = reffac;
        this.ordre = ordre;
    }

    public ZSFI(int iddepart, String depart, String departUK, String reffac, int ordre) {
        this.iddepart = iddepart;
        this.depart = depart;
        this.departUK = departUK;
        this.reffac = reffac;
        this.ordre = ordre;
    }
}