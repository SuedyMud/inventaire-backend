package be.eafcuccle.projint.inventairebackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zuprojet")
public class ZUProjet {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Refunite",referencedColumnName = "Idunite")
    private ZUnite zunite;

    @JsonIgnore // Ajoutez cette annotation pour éviter la récursion
    @ManyToOne
    @JoinColumn(name = "Refprojet", referencedColumnName = "Idprojet")
    private ZProjet zprojet;


    public ZUProjet(){
    }


    public ZProjet getZprojet() {
        return zprojet;
    }

    // Getters et setters avec data*/


}
