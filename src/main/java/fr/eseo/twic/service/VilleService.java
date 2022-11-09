package fr.eseo.twic.service;

import fr.eseo.twic.model.Ville;

import java.util.List;
import java.util.Optional;

public interface VilleService {
    List<Ville> getVilles();

    Optional<Ville> getVilleByCodeCommune(String codeCommune);

    Ville addNewVille(Ville ville);

    void deleteVille(String id);

    Ville updateVille(String codeCommune, Ville ville);

    List<Ville> getVillesByCodePostal(String codePostal);

    Optional<Ville> getVillesByNomCommune(String nomCommune);

    Optional<Ville> getVillesByLibelleAcheminement(String libelleAcheminement);
}
