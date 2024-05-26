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

/*
   @Id*//*
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private int id;
*//*
    @ManyToOne
    @JoinColumn(name = "Refunite", nullable = false)
    private ZUnite refunite;

    @JoinColumn(name = "Refprojet", nullable = false)
    private ZProjet refprojet;

    // Getters et setters avec data*/
}
