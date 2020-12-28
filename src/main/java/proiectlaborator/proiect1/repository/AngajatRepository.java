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

import java.util.ArrayList;
import java.util.List;

@Repository
public class AngajatRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Angajat> angajati = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Angajat> getAngajati() {
        angajati = jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        LOGGER.info("Se vor prelua din repo ANGAJATIi: {}", angajati);
        return jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
    }

    public Angajat getAngajatiByName(String name) {
        LOGGER.info("Se va afisa angajatul cu numele: {}", name);
        angajati = jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        return angajati.stream()
                .filter(angajat -> angajat.getNumeAngajat().equals(name))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Angajatul cu acest nume nu exista!"));
    }

    public List<Angajat> adaugaAngajat(Angajat a) {
        LOGGER.info("Se va adauga clientul: {}", a);
        jdbcTemplate.update(Querys.ADAUGA_ANGAJAT, a.getNumeAngajat(), a.getPrenumeAngajat(), a.getTaxaPerServiciu());
        return jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
    }

    public List<Angajat> stergeAngajat(int idAngajat) {
        angajati = jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        if(angajati.stream()
                .filter(angajat -> angajat.getIdAngajat() == idAngajat)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Angajatul cu acest id nu exista!!!")) != null) {
            LOGGER.info("Se va sterge angajatul cu id-ul: {}", idAngajat);
            jdbcTemplate.update(Querys.STERGE_ANGAJAT, idAngajat);
            return jdbcTemplate.query(Querys.GET_ANGAJATI_SQL, new BeanPropertyRowMapper<>(Angajat.class));
        } else {
            return null;
        }
    }
}
