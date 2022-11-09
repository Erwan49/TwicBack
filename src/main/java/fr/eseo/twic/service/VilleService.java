package fr.eseo.twic.service;

import fr.eseo.twic.model.Ville;

import java.util.List;

public interface VilleService {
    List<Ville> getVilles();

    List<Ville> getVillesByCodePostal(String codePostal);
}
