package fr.eseo.twic.service.impl;

import fr.eseo.twic.model.Ville;
import fr.eseo.twic.repository.VilleRepository;
import fr.eseo.twic.service.VilleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleServiceImpl implements VilleService {

    private final VilleRepository villeRepository;

    public VilleServiceImpl(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @Override
    public List<Ville> getVilles() {
        return villeRepository.findAll();
    }

    @Override
    public Optional<Ville> getVilleByCodeCommune(String codeCommune) {
        return villeRepository.findById(codeCommune);
    }

    @Override
    public Ville addNewVille(Ville ville) {
        return null;
    }

    @Override
    public void deleteVille(String id) {

    }

    @Override
    public Ville updateVille(Ville ville) {
        return null;
    }

    @Override
    public List<Ville> getVillesByCodePostal(String codePostal) {
        return villeRepository.findVillesByCodePostal(codePostal);
    }
}