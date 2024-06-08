package be.eafcuccle.projint.inventairebackend.model;


import jakarta.persistence.*;

@Entity
@Table(name = "zuprojet")
public class ZUProjet {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Refunite",referencedColumnName = "Idunite")
    private ZUnite zunite;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Refprojet", referencedColumnName = "Idprojet")
    private ZProjet zprojet;


    // Getters et setters avec data*/
}
