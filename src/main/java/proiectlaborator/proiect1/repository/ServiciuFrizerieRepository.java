package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.Angajat;
import proiectlaborator.proiect1.model.ServiciuFrizerie;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiciuFrizerieRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<ServiciuFrizerie> servicii = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServiciuFrizerie> getServicii() {
        servicii = jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        LOGGER.info("Se vor prelua din repo serviciile: {}", servicii);
        return jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
    }

    public ServiciuFrizerie getServiciuByName(String name) {
        LOGGER.info("Se va afisa serviciul cu numele: {}", name);
        servicii = jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        return servicii.stream()
                .filter(serviciu -> serviciu.getNumeServiciu().equals(name))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Serviciul cu acest nume nu exista!"));
    }

    public List<ServiciuFrizerie> adaugaServiciu(ServiciuFrizerie s) {
        LOGGER.info("Se va adauga serviciul: {}", s);
        jdbcTemplate.update(Querys.ADAUGA_SERVICIU, s.getNumeServiciu(), s.getDescriereServiciu(), s.getPretServiciu());
        return jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
    }

    public List<ServiciuFrizerie> stergeServiciu(int idServiciu) {
        servicii = jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        if(servicii.stream()
                .filter(serviciu -> serviciu.getIdServiciu() == idServiciu)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Serviciul cu acest id nu exista!!!")) != null) {
            LOGGER.info("Se va sterge serviciul cu id-ul: {}", idServiciu);
            jdbcTemplate.update(Querys.STERGE_SERVICIU, idServiciu);
            return jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        } else {
            return null;
        }
    }
}
