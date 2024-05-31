package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "zsfi")
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

    @OneToMany(mappedBy = "zsfi")
    Set<ZUSFI> zusfi;


    public ZSFI() {
    }



    // Getters et setters avec data
}