package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//import javax.persistence.Lob;

@Data
@Entity
@Table(name = "ZFrascati")
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

    // Getters and setters using Lombok's @Data
}
