package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ZUProjet")
public class ZUProjet {

    @Id
    @Column(name = "Refunite", nullable = false)
    private String refunite;

    @Column(name = "Refprojet", nullable = false)
    private int refprojet;

    // Getters et setters avec data
}
