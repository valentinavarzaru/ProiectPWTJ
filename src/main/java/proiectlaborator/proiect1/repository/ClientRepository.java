package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.Client;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Client> clients = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Client> getClienti() {
        clients = jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        LOGGER.info("Se vor prelua din repo clientii: {}", clients);
        return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
    }

    public Client getClientByName(String name) {
        LOGGER.info("Se va afisa clientul cu numele: {}", name);
        clients = jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        return clients.stream()
                .filter(client -> client.getNumeClient().equals(name))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Clientul cu acest nume nu exista!"));
    }

    public Client getClientById(int id) {
        LOGGER.info("Se va afisa clientul cu id-ul: {}", id);
        clients = jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        return clients.stream()
                .filter(client -> client.getIdClient() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Clientul cu acest id nu exista!"));
    }

    public List<Client> adaugaClient(Client c) {
        LOGGER.info("Se va adauga clientul: {}", c);
        jdbcTemplate.update(Querys.ADAUGA_CLIENT, c.getNumeClient(), c.getPrenumeClient(), c.getGen(), c.getNrTelefon());
        return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
    }

    public List<Client> stergeClient(int idClient) {
        clients = jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        if(clients.stream()
                        .filter(client -> client.getIdClient() == idClient)
                        .findFirst()
                        .orElseThrow(() -> new ObjectNotFoundException("Clientul cu acest id nu exista!!!")) != null) {
            LOGGER.info("Se va sterge clientul cu id-ul: {}", idClient);
            jdbcTemplate.update(Querys.STERGE_CLIENT, idClient);
            return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        } else {
            return null;
        }
    }

    public List<Client> updateTelefon(int id, long nr) {
        clients = jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        if(clients.stream()
                .filter(client -> client.getIdClient() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Clientul cu acest id nu exista!!!")) != null) {
            LOGGER.info("Se va updata numarul de telefon cu {} pentru clientul cu id-ul: {}", nr, id);
            jdbcTemplate.update(Querys.UPDATE_TELEFON, nr, id);
            return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        } else {
            return null;
        }
    }


}
