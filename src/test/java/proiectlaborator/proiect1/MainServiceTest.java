package proiectlaborator.proiect1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proiectlaborator.proiect1.model.Client;
import proiectlaborator.proiect1.repository.ClientRepository;
import proiectlaborator.proiect1.service.MainService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MainServiceTest {

    @InjectMocks
    private MainService mainService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Retrieve all clients")
    public void getClientiTest() {
        //arrange
        when(clientRepository.getClienti()).thenReturn(
                Arrays.asList(new Client(100,"Trandafir", "Alexandra", "feminin", 555444))
        );

        //act
        List<Client> result = mainService.getClienti();

        //assert
        Client c = result.get(0);
        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(c.getIdClient(), 100);
        Assertions.assertEquals(c.getNumeClient(), "Trandafir");
    }

    @Test
    @DisplayName("Get client by name from repo")
    public void getClientByNameTest() {
        //arrange
        String name = "Matei";
        when(clientRepository.getClientByName(name)).thenReturn(
               new Client(100,"Matei", "Alexandra", "feminin", 555444)
        );

        //act
        Client result = mainService.getClientByName(name);

        //assert
        Assertions.assertEquals(result.getNumeClient(), name);

    }

    @Test
    @DisplayName("Adaugare clienti")
    public void adaugaClientTest() {
        //arrange
        List<Client> client = Arrays.asList(new Client(100,"Trandafir", "Alexandra", "feminin", 555444));
        when(clientRepository.adaugaClient(client.get(0))).thenReturn(client);

        //act
        List<Client> result = mainService.adaugaClient(client.get(0));

        //assert
        Client clientAdaugat = result.get(result.size() - 1);
        Assertions.assertEquals(client.get(0).getNumeClient(), clientAdaugat.getNumeClient());
        Assertions.assertEquals(client.get(0).getPrenumeClient(), clientAdaugat.getPrenumeClient());
        Assertions.assertEquals(client.get(0).getIdClient(), clientAdaugat.getIdClient());
        Assertions.assertEquals(client.get(0).getGen(), clientAdaugat.getGen());
        Assertions.assertEquals(client.get(0).getNrTelefon(), clientAdaugat.getNrTelefon());
    }


}
