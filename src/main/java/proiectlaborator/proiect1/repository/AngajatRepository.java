package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.Angajat;
import proiectlaborator.proiect1.model.Client;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AngajatRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Angajat> angajati = new ArrayList<>();
    Date currentDate = new Date();
    String repoLogger = "AngajatRepository";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Angajat> getAngajati() {
        angajati = jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        LOGGER.info("Se vor prelua din repo angajatii: {}", angajati);
        String loggerText = "Se vor prelua din repo angajatii: " + angajati;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        return jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
    }

    public Angajat getAngajatiByName(String name) {
        LOGGER.info("Se va afisa angajatul cu numele: {}", name);
        String loggerText = "Se va afisa angajatul cu numele: " + name;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        angajati = jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        return angajati.stream()
                .filter(angajat -> angajat.getNumeAngajat().equals(name))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Angajatul cu acest nume nu exista!"));
    }

    public Angajat getAngajatiById(int idAngajat) {
        LOGGER.info("Se va afisa angajatul cu id-ul: {}", idAngajat);
        String loggerText = "Se va afisa angajatul cu id-ul: " + idAngajat;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        angajati = jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        return angajati.stream()
                .filter(angajat -> angajat.getIdAngajat() == idAngajat)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Angajatul cu acest id nu exista!"));
    }

    public List<Angajat> adaugaAngajat(Angajat a) {
        LOGGER.info("Se va adauga angajatul: {}", a);
        String loggerText = "Se va adauga angajatul: " + a;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        jdbcTemplate.update(Querys.ADAUGA_ANGAJAT, a.getNumeAngajat(), a.getPrenumeAngajat(), a.getTaxaPerServiciu());
        return jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
    }

    public List<Angajat> updateTaxa(int idAngajat, Double taxa) {
        if(taxa <= 5) {
            throw new ObjectNotFoundException("Taxa trebuie sa fie mai mare ca 5!!!");
        } else if(getAngajatiById(idAngajat) != null) {
            LOGGER.info("Se va actualiza angajatul {} cu taxa {}", idAngajat, taxa);
            String loggerText = "Se va actualiza angajatul " + idAngajat + " cu taxa " + taxa;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.UPDATE_TAXA, taxa, idAngajat);
            return jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        } else {
            return null;
        }
    }

    public List<Angajat> stergeAngajat(int idAngajat) {
        if(getAngajatiById(idAngajat) != null) {
            LOGGER.info("Se va sterge angajatul cu id-ul: {}", idAngajat);
            String loggerText = "Se va sterge angajatul cu id-ul: " + idAngajat;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.STERGE_ANGAJAT, idAngajat);
            return jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        } else {
            return null;
        }
    }
}
