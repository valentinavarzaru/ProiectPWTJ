package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.Plata;
import proiectlaborator.proiect1.model.Programare;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlataRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Plata> plati = new ArrayList<>();
    private static List<Programare> programari = new ArrayList<>();
    Date currentDate = new Date();
    String repoLogger = "PlataRepository";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Plata> getPlati() {
        plati = jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
        LOGGER.info("Se vor prelua din repo platile: {}", plati);
        String loggerText = "Se vor prelua din repo platile: " + plati;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        return jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
    }

    public Plata getPlataById(int id) {
        LOGGER.info("Se va afisa plata cu id-ul: {}", id);
        String loggerText = "Se va afisa plata cu id-ul: " + id;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        plati = jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
        return plati.stream()
                .filter(plata -> plata.getIdPlata() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Plata cu acest id nu exista!"));
    }

    public List<Plata> getPlataByIdProg(int idProg) {
        plati = jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
        LOGGER.info("Se va afisa plata pentru programarea: {}", idProg);
        String loggerText = "Se va afisa plata pentru programarea: " + idProg;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        return plati.stream()
                .filter(plata -> plata.getIdProgramare() == idProg)
                .collect(Collectors.toList());
    }

    public List<Plata> adaugaPlata(int idProgramare, String metPlata) {
        programari = jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        if(programari.stream()
                .filter(programare -> programare.getIdProgramare() == idProgramare)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Programarea cu acest id nu exista!!!")) != null) {
            if(getPlataByIdProg(idProgramare).isEmpty()) {
                LOGGER.info("Se va adauga plata pentru programarea: {}", idProgramare);
                String loggerText = "Se va adauga plata pentru programarea: " + idProgramare;
                String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
                jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
                jdbcTemplate.update(Querys.ADAUGA_PLATA, idProgramare, metPlata, idProgramare, idProgramare);
                return jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
            } else {
                throw new ObjectNotFoundException("Plata pentru aceasta programare a fost deja efectuata!!!");
            }
        } else {
            return null;
        }
    }

    public Double afisarePretFinal(int idProgramare) {
        LOGGER.info("Se va afisa pretul final pentru programarea: {}", idProgramare);
        String loggerText = "Se va afisa pretul final pentru programarea: " + idProgramare;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        plati = jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
        Plata result = plati.stream()
                .filter(plata -> plata.getIdProgramare() == idProgramare)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Programarea cu acest id nu exista!!!"));
        return result.getPretFinal();
    }

    public List<Plata> stergePlata(int idPlata) {
        if(getPlataById(idPlata) != null) {
            LOGGER.info("Se va sterge plata cu id-ul: {}", idPlata);
            String loggerText = "Se va sterge plata cu id-ul: " + idPlata;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.STERGE_PLATA, idPlata);
            return jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
        } else {
            return null;
        }
    }
    
}
