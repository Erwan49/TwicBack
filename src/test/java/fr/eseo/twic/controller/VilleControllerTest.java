package fr.eseo.twic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eseo.twic.model.Ville;
import fr.eseo.twic.service.VilleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VilleController.class)
class VilleControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    Ville ville1;
    Ville ville2;

    List<Ville> villes;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VilleService villeService;

    @BeforeEach
    void setUp() {
        ville1 = Ville.builder().codeCommune("01006").nomCommune("AMBLEON").codePostal("01300").
                libelleAcheminement("AMBLEON").ligne5("").latitude("45.7328309975").longitude("5.65715901636").build();
        ville2 = Ville.builder().codeCommune("01008").nomCommune("AMBUTRIX").codePostal("01500").
                libelleAcheminement("AMBUTRIX").ligne5("").latitude("45.9756078125").longitude("5.34313562094").build();
        villes = List.of(ville1, ville2);
    }

    @Test
    void shouldGetAllVille() throws Exception {

        when(villeService.getVilles()).thenReturn(villes);

        mockMvc.perform(get("/api/villes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].codeCommune").value("01006"))
                .andExpect(jsonPath("$[0].nomCommune").value("AMBLEON"))
                .andExpect(jsonPath("$[1].codeCommune").value("01008"))
                .andExpect(jsonPath("$[1].nomCommune").value("AMBUTRIX"));

        verify(villeService, times(1)).getVilles();
        verifyNoMoreInteractions(villeService);
    }

    @Test
    void tryGetEmptyVilleList() throws Exception {

        List<Ville> villes = new ArrayList<>();

        when(villeService.getVilles()).thenReturn(villes);

        mockMvc.perform(get("/api/villes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(jsonPath("$[0].nomCommune").doesNotExist());

        verify(villeService, times(1)).getVilles();
        verifyNoMoreInteractions(villeService);
    }

    @Test
    void shouldGetAVille() throws Exception {

        Optional<Ville> optionalVille = Optional.of(ville1);

        when(villeService.getVilleByCodeCommune("01006")).thenReturn(optionalVille);

        mockMvc.perform(get("/api/villes/{nomCommune}", "01006"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.codeCommune").value("01006"))
                .andExpect(jsonPath("$.nomCommune").value("AMBLEON"));
        verify(villeService, times(1)).getVilleByCodeCommune("01006");
        verifyNoMoreInteractions(villeService);
    }

    @Test
    void tryGetAnInexistantVille() throws Exception {

        when(villeService.getVilleByCodeCommune("00000")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/villes/{nomCommune}", "00000"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("null")));

        verify(villeService, times(1)).getVilleByCodeCommune("00000");
        verifyNoMoreInteractions(villeService);
    }

}