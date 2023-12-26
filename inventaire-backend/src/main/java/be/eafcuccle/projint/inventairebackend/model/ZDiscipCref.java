package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ZDiscipCref")
public class ZDiscipCref {

    @Id
    @Column(name = "Idcodecref", nullable = false, length = 4)
    private String idcodecref;

    @Column(name = "Discipline", nullable = false, length = 75)
    private String discipline;

    @Column(name = "DisciplineUK", nullable = false, length = 75)
    private String disciplineUK;

    // Getters et setters avec data
}
