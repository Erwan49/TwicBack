package fr.eseo.twic.service.impl;

import fr.eseo.twic.exception.BadRequestException;
import fr.eseo.twic.exception.ResourceNotFoundException;
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
        Optional<Ville> optionalVille1 = villeRepository.findById(ville.getCodeCommune());
        Optional<Ville> optionalVille2 = villeRepository.findVillesByNomCommune(ville.getNomCommune());
        Optional<Ville> optionalVille3 = villeRepository.findVillesByLibelleAcheminement(ville.getLibelleAcheminement());
        if (optionalVille1.isPresent()) {
            throw new BadRequestException("codeCommune is already taken");
        } else if (optionalVille2.isPresent()) {
            throw new BadRequestException("nomCommune is already taken");
        } else if (optionalVille3.isPresent()) {
            throw new BadRequestException("libelleAcheminement is already taken");
        }
        return villeRepository.save(ville);
    }

    @Override
    public void deleteVille(String codeCommune) {
        Ville ville = villeRepository.findById(codeCommune)
                .orElseThrow(() -> new ResourceNotFoundException("ville", codeCommune));
        villeRepository.delete(ville);
    }

    @Override
    public Ville updateVille(String codeCommune, Ville ville) {
        Ville villeToUpdate = villeRepository.findById(codeCommune)
                .orElseThrow(() -> new ResourceNotFoundException("ville", codeCommune));

        Optional<Ville> optionalVille1 = villeRepository.findById(ville.getCodeCommune());
        Optional<Ville> optionalVille2 = villeRepository.findVillesByNomCommune(ville.getNomCommune());
        Optional<Ville> optionalVille3 = villeRepository.findVillesByLibelleAcheminement(ville.getLibelleAcheminement());
        if (optionalVille1.isPresent()) {
            throw new BadRequestException("codeCommune is already taken");
        } else if (optionalVille2.isPresent() && !villeToUpdate.getNomCommune().equals(ville.getNomCommune())) {
            throw new BadRequestException("nomCommune is already taken");
        } else if (optionalVille3.isPresent() && !villeToUpdate.getLibelleAcheminement().equals(ville.getLibelleAcheminement())) {
            throw new BadRequestException("libelleAcheminement is already taken");
        }

        villeToUpdate.setNomCommune(ville.getNomCommune());
        villeToUpdate.setCodePostal(ville.getCodePostal());
        villeToUpdate.setLibelleAcheminement(ville.getLibelleAcheminement());
        villeToUpdate.setLigne5(ville.getLigne5());
        villeToUpdate.setLatitude(ville.getLatitude());
        villeToUpdate.setLongitude(ville.getLongitude());

        return villeRepository.save(villeToUpdate);
    }

    @Override
    public List<Ville> getVillesByCodePostal(String codePostal) {
        return villeRepository.findVillesByCodePostal(codePostal);
    }
}