package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

//import javax.persistence.Lob;

@Data
@Entity
@Table(name = "zfrascati")
public class ZFrascati {

    @Id
    @Column(name = "Idfrascati", nullable = false, length = 4)
    private String idfrascati;

    @Column(name = "Frascati", nullable = false, length = 255)
    private String frascati;

    @Column(name = "FrascatiUK", nullable = false, length = 255)
    private String frascatiUK;

    //@Lob
    @Column(name = "Description", nullable = false)
    private String description;

    //@Lob
    @Column(name = "DescriptionUK", nullable = false)
    private String descriptionUK;

    @Column(name = "Refgrdiscip", nullable = false)
    private int refgrdiscip;

    @Column(name = "Ordre", nullable = false)
    private int ordre;

    @OneToMany(mappedBy = "zfrascati")
    Set<ZUFrascati> zufrascati;

    public ZFrascati() {
    }

    // Getters and setters using Lombok's @Data
}
