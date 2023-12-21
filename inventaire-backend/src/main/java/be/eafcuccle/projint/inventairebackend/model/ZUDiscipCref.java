package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ZUDiscipCref")
public class ZUDiscipCref {

    @Id
    @Column(name = "Refunite", nullable = false, length = 6)
    private String refunite;

    @Column(name = "Refcref", nullable = false, length = 4)
    private String refcref;

    // Getters and setters using Lombok's @Data
}
