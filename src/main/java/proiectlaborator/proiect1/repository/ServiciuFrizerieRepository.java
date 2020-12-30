package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.ServiciuFrizerie;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ServiciuFrizerieRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<ServiciuFrizerie> servicii = new ArrayList<>();
    Date currentDate = new Date();
    String repoLogger = "ServiciuFrizerieRepository";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServiciuFrizerie> getServicii() {
        servicii = jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        LOGGER.info("Se vor prelua din repo serviciile: {}", servicii);
        String loggerText = "Se vor prelua din repo serviciile: " + servicii;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        return jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
    }

    public ServiciuFrizerie getServiciuByName(String name) {
        LOGGER.info("Se va afisa serviciul cu numele: {}", name);
        String loggerText = "Se va afisa serviciul cu numele: " + name;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        servicii = jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        return servicii.stream()
                .filter(serviciu -> serviciu.getNumeServiciu().equals(name))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Serviciul cu acest nume nu exista!"));
    }

    public ServiciuFrizerie getServiciuById(int idServiciu) {
        LOGGER.info("Se va afisa serviciul cu id-ul: {}", idServiciu);
        String loggerText = "Se va afisa serviciul cu id-ul: " + idServiciu;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        servicii = jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        return servicii.stream()
                .filter(serviciu -> serviciu.getIdServiciu() == idServiciu)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Serviciul cu acest id nu exista!"));
    }

    public List<ServiciuFrizerie> updatePretServiciu(int idServiciu, Double pret) {
        if(pret <= 5) {
            throw new ObjectNotFoundException("Pretul trebuie sa fie de minim 5 lei!!!");
        } else if (getServiciuById(idServiciu) != null) {
            LOGGER.info("Se va modifica pretul serviciului {} la {} lei", idServiciu, pret);
            String loggerText = "Se va modifica pretul serviciului " + idServiciu + " la " + pret + " lei";
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.UPDATE_PRET_SERVICIU, pret, idServiciu);
            return jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        } else {
            return null;
        }
    }

    public List<ServiciuFrizerie> adaugaServiciu(ServiciuFrizerie s) {
        LOGGER.info("Se va adauga serviciul: {}", s);
        String loggerText = "Se va adauga serviciul: " + s;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        jdbcTemplate.update(Querys.ADAUGA_SERVICIU, s.getNumeServiciu(), s.getDescriereServiciu(), s.getPretServiciu());
        return jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
    }

    public List<ServiciuFrizerie> stergeServiciu(int idServiciu) {
        if(getServiciuById(idServiciu) != null) {
            LOGGER.info("Se va sterge serviciul cu id-ul: {}", idServiciu);
            String loggerText = "Se va sterge serviciul cu id-ul: " + idServiciu;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.STERGE_SERVICIU, idServiciu);
            return jdbcTemplate.query(Querys.GET_SERVICII_SQL, new BeanPropertyRowMapper<>(ServiciuFrizerie.class));
        } else {
            return null;
        }
    }
}
