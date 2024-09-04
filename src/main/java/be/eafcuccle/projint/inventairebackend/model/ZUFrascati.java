package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zufrascati")
public class ZUFrascati {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "Refunite", referencedColumnName = "idunite")
    private ZUnite zunite;

    @ManyToOne
    @JoinColumn(name = "Reffrascati", referencedColumnName = "idfrascati")
    private ZFrascati zfrascati;

    public ZUFrascati() {
    }

    // Getter et setter pour 'zfrascati'
    public ZFrascati getFrascati() {
        return zfrascati;
    }



    // Getters and setters using Lombok's @Data

}
