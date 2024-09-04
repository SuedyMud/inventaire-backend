package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zudiscipcref")
public class ZUDiscipCref {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Refunite", referencedColumnName = "idunite")
    private ZUnite zunite;

    @ManyToOne
    @JoinColumn(name = "Refcref", referencedColumnName = "idcodecref")
    private ZDiscipCref zdiscipCrefs;

    public ZDiscipCref getdiscipCref() {
        return zdiscipCrefs;
    }

    // Getters and setters using Lombok's @Data
}
