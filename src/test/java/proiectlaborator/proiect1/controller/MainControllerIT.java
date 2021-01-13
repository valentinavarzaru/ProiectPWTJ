package proiectlaborator.proiect1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiectlaborator.proiect1.model.Client;
import proiectlaborator.proiect1.service.MainService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MainController.class)
public class MainControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainService mainService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void adaugaClient() throws Exception {
        // arrange
        Client client = new Client("Popescu", "Irina", "feminin", 555913);
        when(mainService.adaugaClient(any())).thenReturn(Collections.singletonList(client));

        // act + assert
        mockMvc.perform(
                post("/frizerie/adaugaClient")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(client))
        ).andExpect(status().isCreated())
         .andExpect(jsonPath("$..numeClient").value(client.getNumeClient()))
         .andExpect(jsonPath("$..prenumeClient").value(client.getPrenumeClient()))
         .andExpect(jsonPath("$..gen").value(client.getGen()));
    }

    @Test
    public void getClientByName() throws Exception {
        // arrange
        Client client = new Client("Popescu", "Irina", "feminin", 555913);
        when(mainService.getClienti()).thenReturn(Collections.singletonList(client));
        when(mainService.getClientByName("Popescu")).thenReturn(client);

        // act + assert
        mockMvc.perform(
                get("/frizerie/getClientiByName?name=Popescu")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(client))
        ).andExpect(status().isFound())
         .andExpect(jsonPath("$..numeClient").value(client.getNumeClient()))
         .andExpect(jsonPath("$..prenumeClient").value(client.getPrenumeClient()))
         .andExpect(jsonPath("$..gen").value(client.getGen()));
    }

    @Test
    public void stergeClient() throws Exception {
        // arrange
        Client client = new Client(1,"Popescu", "Irina", "feminin", 555913);
        when(mainService.stergeClient(1)).thenReturn(Collections.singletonList(client));

        // act + assert
        mockMvc.perform(
                delete("/frizerie/stergeClient?id=1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(client))
        ).andExpect(status().isAccepted());
    }

}
