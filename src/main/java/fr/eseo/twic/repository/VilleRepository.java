package fr.eseo.twic.repository;

import fr.eseo.twic.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, String> {

    List<Ville> findVillesByCodePostal(String codePostal);

    Optional<Ville> findVillesByNomCommune(String nomCommune);

    Optional<Ville> findVillesByLibelleAcheminement(String libelleAcheminement);

}