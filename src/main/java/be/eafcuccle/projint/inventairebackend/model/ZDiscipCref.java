package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zdiscipcref")
public class ZDiscipCref {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idcodecref", nullable = false, length = 4)
    private String idcodecref;

    @Column(name = "Discipline", nullable = false, length = 75)
    private String discipline;

    @Column(name = "DisciplineUK", nullable = false, length = 75)
    private String disciplineUK;

    // Getters et setters avec data

    public ZDiscipCref() {
    }

    public ZDiscipCref(String discipline, String disciplineUK) {
        this.discipline = discipline;
        this.disciplineUK = disciplineUK;
    }

    public ZDiscipCref(String idcodecref, String discipline, String disciplineUK) {
        this.idcodecref = idcodecref;
        this.discipline = discipline;
        this.disciplineUK = disciplineUK;
    }
}
