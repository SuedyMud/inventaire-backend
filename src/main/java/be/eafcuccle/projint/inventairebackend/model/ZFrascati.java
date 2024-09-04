package be.eafcuccle.projint.inventairebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
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


    @Column(name = "Description", nullable = false)
    private String description;


    @Column(name = "DescriptionUK", nullable = false)
    private String descriptionUK;

    @Column(name = "Refgrdiscip", nullable = false)
    private int refgrdiscip;

    @Column(name = "Ordre", nullable = false)
    private int ordre;

    //@JsonIgnore
    @OneToMany(mappedBy = "zfrascati", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<ZUFrascati> zufrascati = new HashSet<>();

    public ZFrascati() {
    }

    // Getters and setters using Lombok's @Data
}
