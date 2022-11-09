package fr.eseo.twic.service.impl;

import fr.eseo.twic.model.Ville;
import fr.eseo.twic.repository.VilleRepository;
import fr.eseo.twic.service.VilleService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Ville> getVillesByCodePostal(String codePostal) {
        return villeRepository.findVillesByCodePostal(codePostal);
    }
}