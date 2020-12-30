package proiectlaborator.proiect1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import proiectlaborator.proiect1.model.Programare;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProgramareRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
    private static List<Programare> programari = new ArrayList<>();
    Date currentDate = new Date();
    String repoLogger = "ProgramareRepository";

    @Autowired
    PlataRepository plataRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ServiciuFrizerieRepository serviciuFrizerieRepository;

    @Autowired
    AngajatRepository angajatRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Programare> getProgramari() {
        programari = jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        LOGGER.info("Se vor prelua din repo programarile: {}", programari);
        String loggerText = "Se vor prelua din repo programarile: " + programari;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
    }

    public Programare getProgramareById(int id) {
        LOGGER.info("Se va afisa programarea cu id-ul: {}", id);
        String loggerText = "Se va afisa programarea cu id-ul: " + id;
        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
        programari = jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        return programari.stream()
                .filter(serviciu -> serviciu.getIdProgramare() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Programarea cu acest id nu exista!"));
    }

    public List<Programare> adaugaProgramare(Programare p, String metPlata) {
        if( clientRepository.getClientById(p.getIdClient()) != null
        && serviciuFrizerieRepository.getServiciuById(p.getIdServiciu()) != null
        && angajatRepository.getAngajatiById(p.getIdAngajat()) != null) {
            PreparedStatementCreator preparedStatementCreator = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(Querys.ADAUGA_PROGRAMARE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, p.getIdClient());
                preparedStatement.setInt(2, p.getIdServiciu());
                preparedStatement.setInt(3, p.getIdAngajat());
                preparedStatement.setString(4, p.getData());
                preparedStatement.setString(5, p.getOra());
                return preparedStatement;
            };
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
            LOGGER.info("Se va adauga programarea {} cu id-ul {}", p, generatedKeyHolder.getKey().intValue());
            String loggerText = "Se va adauga programarea " + p + " cu id-ul " + generatedKeyHolder.getKey().intValue();
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            plataRepository.adaugaPlata(generatedKeyHolder.getKey().intValue(),metPlata);
            return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        } else {
            return null;
        }
    }

    public List<Programare> updateDataProg(int idProg, String data) {
        if(getProgramareById(idProg) != null) {
            LOGGER.info("Se va actualiza programarea {} cu data {}", idProg, data);
            String loggerText = "Se va actualiza programarea " + idProg + " cu data " + data;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.UPDATE_DATA, data, idProg);
            return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        } else {
            return null;
        }
    }

    public List<Programare> updateOraProg(int idProg, String ora) {
        if(getProgramareById(idProg) != null) {
            LOGGER.info("Se va actualiza programarea {} cu ora {}", idProg, ora);
            String loggerText = "Se va actualiza programarea " + idProg + " cu ora " + ora;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.UPDATE_ORA, ora, idProg);
            return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        } else {
            return null;
        }
    }

    public List<Programare> stergeProgramare(int idProgramare) {
        if(getProgramareById(idProgramare) != null) {
            LOGGER.info("Se vor sterge programarea si plata cu id-ul: {}", idProgramare);
            String loggerText = "Se vor sterge programarea si plata cu id-ul: " + idProgramare;
            String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
            jdbcTemplate.update(Querys.ADAUGA_LOG, currentDate, currentHour, repoLogger, loggerText);
            jdbcTemplate.update(Querys.STERGE_PLATA_DUPA_PROG, idProgramare);
            jdbcTemplate.update(Querys.STERGE_PROGRAMARE, idProgramare);
            return jdbcTemplate.query(Querys.GET_PROGRAMARI_SQL, new BeanPropertyRowMapper<>(Programare.class));
        } else {
            return null;
        }
    }
}
