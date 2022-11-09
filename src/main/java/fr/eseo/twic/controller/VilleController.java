package fr.eseo.twic.controller;

import fr.eseo.twic.model.Ville;
import fr.eseo.twic.model.dto.VilleDto;
import fr.eseo.twic.service.VilleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/villes")
@CrossOrigin("*")
public class VilleController {

    private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public List<Ville> villes() {
        return villeService.getVilles();
    }

    @GetMapping("{codeCommune}")
    public Optional<Ville> ville(@PathVariable String codeCommune) {
        return villeService.getVilleByCodeCommune(codeCommune);
    }

    @PostMapping()
    public Ville addNewVille(@RequestBody VilleDto villeDto) {
        return villeService.addNewVille(villeDto.toEntity());
    }

    @DeleteMapping("{codeCommune}")
    public void deleteVille(@PathVariable String codeCommune) {
        villeService.deleteVille(codeCommune);
    }

    @PutMapping("{codeCommune}")
    public Ville updateVille(@PathVariable String codeCommune, @RequestBody Ville ville) {
        return villeService.updateVille(codeCommune, ville);
    }


    @GetMapping("/codePostal/{codePostal}")
    public List<Ville> villesByCodePostal(@PathVariable String codePostal) {
        return villeService.getVillesByCodePostal(codePostal);
    }

}
