package proiectlaborator.proiect1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import proiectlaborator.proiect1.model.Client;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MainServiceIT {

    @Autowired
    private MainService mainService;

    @Test
    public void adaugaClientHappyFlow() {
        Client client = new Client("Popovici", "Irina", "feminin", 555913);

        List<Client> result = mainService.adaugaClient(client);

        Client addedClient = result.get(result.size() - 1);
        assertNotNull(addedClient.getIdClient());
        assertEquals(client.getNumeClient(), addedClient.getNumeClient());
        assertEquals(client.getPrenumeClient(), addedClient.getPrenumeClient());
        assertEquals(client.getGen(), addedClient.getGen());
        assertEquals(client.getNrTelefon(), addedClient.getNrTelefon());
    }

    @Test
    public void getClientByNameHappyFlow() {
        Client client = new Client("Popovici", "Irina", "feminin", 555913);

        Client result = mainService.getClientByName("Popovici");

        assertNotNull(result.getIdClient());
        assertEquals(client.getNumeClient(), result.getNumeClient());
        assertEquals(client.getPrenumeClient(), result.getPrenumeClient());
        assertEquals(client.getGen(), result.getGen());
        assertEquals(client.getNrTelefon(), result.getNrTelefon());
    }

    @Test
    public void stergeClientHappyFlow() {
        Client client = new Client("Tomescu", "Radu", "masculin", 754313);

        List<Client> listaClienti = mainService.adaugaClient(client);
        Client addedClient = listaClienti.get(listaClienti.size() - 1);
        List<Client> stergere = mainService.stergeClient(addedClient.getIdClient());

        Client result = stergere.get(stergere.size() - 1);
        assertNotEquals(client.getNumeClient(), result.getNumeClient());
        assertNotEquals(client.getPrenumeClient(), result.getPrenumeClient());
    }


}
