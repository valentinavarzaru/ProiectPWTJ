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

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlataRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Plata> plati = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Plata> getPlati() {
        plati = jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
        LOGGER.info("Se vor prelua din repo platile: {}", plati);
        return jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
    }

    public List<Plata> adaugaPlata(int idProgramare) {
        LOGGER.info("Se va adauga plata pentru programarea: {}", idProgramare);
        jdbcTemplate.update(Querys.ADAUGA_PLATA, idProgramare, "cu cardul", idProgramare, idProgramare);
        return jdbcTemplate.query(Querys.GET_PLATI_SQL, new BeanPropertyRowMapper<>(Plata.class));
    }
}
