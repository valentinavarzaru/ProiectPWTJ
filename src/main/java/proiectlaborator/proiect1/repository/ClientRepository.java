package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.Client;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ClientRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Client> clients = new ArrayList<>();
    Date currentDate = new Date();
    String repoLogger = "ClientRepository";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Client> getClienti() {
        clients = jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        LOGGER.info("Se vor prelua din repo clientii: {}", clients);
        String loggerText = "Se vor prelua din repo clientii: " + clients;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
    }

    public Client getClientByName(String name) {
        LOGGER.info("Se va afisa clientul cu numele: {}", name);
        String loggerText = "Se va afisa clientul cu numele: " + name;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        return getClienti().stream()
                .filter(client -> client.getNumeClient().equals(name))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Clientul cu acest nume nu exista!"));
    }

    public Client getClientById(int id) {
        LOGGER.info("Se va afisa clientul cu id-ul: {}", id);
        String loggerText = "Se va afisa clientul cu id-ul: " + id;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        clients = jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        return clients.stream()
                .filter(client -> client.getIdClient() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Clientul cu acest id nu exista!"));
    }

    public List<Client> adaugaClient(Client c) {
        LOGGER.info("Se va adauga clientul: {}", c);
        String loggerText = "Se va adauga clientul: " + c;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        jdbcTemplate.update(Querys.ADAUGA_CLIENT, c.getNumeClient(), c.getPrenumeClient(), c.getGen(), c.getNrTelefon());
        return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
    }

    public List<Client> updateTelefon(int id, long nr) {
        if(getClientById(id) != null) {
            LOGGER.info("Se va updata numarul de telefon cu {} pentru clientul cu id-ul: {}", nr, id);
            String loggerText = "Se va updata numarul de telefon cu " + nr + " pentru clientul cu id-ul: " + id;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.UPDATE_TELEFON, nr, id);
            return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        } else {
            return null;
        }
    }

    public List<Client> stergeClient(int idClient) {
        if(getClientById(idClient) != null) {
            LOGGER.info("Se va sterge clientul cu id-ul: {}", idClient);
            String loggerText = "Se va sterge clientul cu id-ul: " + idClient;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.STERGE_CLIENT, idClient);
            return jdbcTemplate.query(Querys.GET_CLIENTI_SQL, new BeanPropertyRowMapper<>(Client.class));
        } else {
            return null;
        }
    }


}
