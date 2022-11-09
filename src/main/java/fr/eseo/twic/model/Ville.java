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
    private String codeCommune;

    @Column(name = "Nom_commune", nullable = false)
    private String nomCommune;

    @Column(name = "Code_postal", nullable = false)
    private String codePostal;

    @Column(name = "Libelle_acheminement", nullable = false)
    private String libelleAcheminement;

    @Column(name = "Ligne_5", nullable = false)
    private String ligne5;

    @Column(name = "Latitude", nullable = false)
    private String latitude;

    @Column(name = "Longitude", nullable = false)
    private String longitude;
}