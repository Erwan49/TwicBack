package fr.eseo.twic.service.impl;

import fr.eseo.twic.model.Ville;
import fr.eseo.twic.repository.VilleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VilleServiceImplTest {

    private List<Ville> villes;

    private Ville ville1;
    private Ville ville2;

    @Autowired
    private VilleServiceImpl villeService;

    @Mock
    private VilleRepository villeRepository;

    @BeforeEach
    void setUp() {
        villeService = new VilleServiceImpl(villeRepository);
        ville1 = Ville.builder().codeCommune("01006").nomCommune("AMBLEON").codePostal("01300").
                libelleAcheminement("AMBLEON").ligne5("").latitude("45.7328309975").longitude("5.65715901636").build();
        ville2 = Ville.builder().codeCommune("01008").nomCommune("AMBUTRIX").codePostal("01500").
                libelleAcheminement("AMBUTRIX").ligne5("").latitude("45.9756078125").longitude("5.34313562094").build();
        villes = List.of(ville1, ville2);
    }

    @Test
    void shouldGetAllVilles() {

        when(villeRepository.findAll()).thenReturn(villes);

        List<Ville> result = villeService.getVilles();

        verify(villeRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(villes, result);
    }

    @Test
    void shouldGetVilleByCodeCommune() {

        when(villeRepository.findById(ville1.getCodeCommune())).thenReturn(Optional.of(ville1));

        Optional<Ville> optionalResult = villeService.getVilleByCodeCommune(ville1.getCodeCommune());
        Ville result = new Ville();
        if (optionalResult.isPresent()) {
            result = optionalResult.get();
        }
        verify(villeRepository, times(1)).findById(ville1.getCodeCommune());

        assertNotNull(result);
        assertEquals(ville1, result);
    }

    @Test
    void shouldReturnEmptyListIfNoVillesFound() {

        List<Ville> villes = new ArrayList<>();

        when(villeRepository.findAll()).thenReturn(villes);

        List<Ville> result = villeService.getVilles();

        verify(villeRepository, times(1)).findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(villes, result);
    }

    @Test
    void shouldReturnNullIfVilleDoesNotExists() {

        when(villeRepository.findById("00000")).thenReturn(null);

        Optional<Ville> result = villeService.getVilleByCodeCommune("00000");

        verify(villeRepository, times(1)).findById("00000");

        assertNull(result);
    }

}