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


    @ManyToOne
    @JoinColumn(name = "Refprojet", referencedColumnName = "Idprojet")
    private ZProjet zprojet;


    public ZUProjet(){
    }

   /* public ZProjet getProjet() {
        return zprojet;
    }*/

    // Getters et setters avec data*/

    /*public ZChercheur getChercheur() {
        return zchercheur;
    }*/
}
