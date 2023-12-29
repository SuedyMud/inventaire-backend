package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ZDiscipcref") //attention ZDiscipcref n'est pas identique a ZDiscipCref (la DB comprend zdiscip_cref dans ce cas)
public class ZDiscipCref {

    @Id
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
