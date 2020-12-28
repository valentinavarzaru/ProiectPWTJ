package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.Programare;
import proiectlaborator.proiect1.model.Programare;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProgramareRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Programare> programari = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Programare> getProgramari() {
        programari = jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        LOGGER.info("Se vor prelua din repo programarile: {}", programari);
        return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
    }

    public Programare getProgramareById(int id) {
        LOGGER.info("Se va afisa programarea cu id-ul: {}", id);
        programari = jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        return programari.stream()
                .filter(serviciu -> serviciu.getIdProgramare() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Programarea cu acest id nu exista!"));
    }

    public List<Programare> adaugaProgramare(Programare p) {
        LOGGER.info("Se va adauga programarea: {}", p);
        jdbcTemplate.update(Querys.ADAUGA_PROGRAMARE, p.getIdClient(), p.getIdServiciu(), p.getIdAngajat(), p.getData(), p.getOra());
        return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
    }

    public List<Programare> stergeProgramare(int idProgramare) {
        programari = jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        if(programari.stream()
                .filter(programare -> programare.getIdProgramare() == idProgramare)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Programarea cu acest id nu exista!!!")) != null) {
            LOGGER.info("Se va sterge programarea cu id-ul: {}", idProgramare);
            jdbcTemplate.update(Querys.STERGE_PROGRAMARE, idProgramare);
            return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        } else {
            return null;
        }
    }
}
