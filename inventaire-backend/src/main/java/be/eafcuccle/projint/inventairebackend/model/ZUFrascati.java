package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ZUFrascati")
public class ZUFrascati {

    @Id
    @Column(name = "Refunite", nullable = false, length = 6)
    private String refunite;

    @Column(name = "Reffrascati", nullable = false, length = 4)
    private String reffrascati;

    // Getters and setters using Lombok's @Data
}
