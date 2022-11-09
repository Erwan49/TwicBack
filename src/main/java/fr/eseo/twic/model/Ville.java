package fr.eseo.twic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ville_france")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ville {
    @Id
    @Column(name = "Code_commune_INSEE", nullable = false)
    private String id;

    @Column(name = "Nom_commune", nullable = false)
    private String nomCommune;

    @Column(name = "Code_postal", nullable = true)
    private String codePostal;

    @Column(name = "Libelle_acheminement", nullable = true)
    private String libelleAcheminement;

    @Column(name = "Ligne_5", nullable = true)
    private String ligne5;

    @Column(name = "Latitude", nullable = true)
    private String latitude;

    @Column(name = "Longitude", nullable = true)
    private String longitude;
}