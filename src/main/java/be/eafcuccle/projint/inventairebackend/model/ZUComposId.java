package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ZUComposId implements Serializable {
    private int zChercheurId;
    private int zUniteId;

    // Getters, setters, equals, hashCode
}