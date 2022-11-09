package fr.eseo.twic.model.dto;

import fr.eseo.twic.model.Ville;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VilleDto {

    private String codeCommune;
    private String nomCommune;
    private String codePostal;
    private String libelleAcheminement;
    private String ligne5;
    private String latitude;
    private String longitude;

    public static VilleDto fromEntity(Ville ville) {
        return VilleDto.builder().codeCommune(ville.getCodeCommune()).nomCommune(ville.getNomCommune()).
                codePostal(ville.getCodePostal()).libelleAcheminement((ville.getLibelleAcheminement()))
                .ligne5(ville.getLigne5()).latitude(ville.getLatitude()).longitude(ville.getLongitude()).build();
    }

    public Ville toEntity() {
        return Ville.builder().codeCommune(getCodeCommune()).nomCommune(getNomCommune())
                .codePostal(getCodePostal()).libelleAcheminement(getLibelleAcheminement()).ligne5(getLigne5())
                .latitude(getLatitude()).longitude(getLongitude()).build();
    }

}
