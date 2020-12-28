package proiectlaborator.proiect1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import proiectlaborator.proiect1.model.*;
import proiectlaborator.proiect1.query.Querys;
import proiectlaborator.proiect1.repository.*;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import java.util.List;

@Service
public class MainService {

//    Clienti
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClienti() {
        return clientRepository.getClienti();
    }

    public Client getClientByName(String name) {
        return clientRepository.getClientByName(name);
    }

    public Client getClientById(int id) {
        return clientRepository.getClientById(id);
    }

    public List<Client> adaugaClient(Client c) {
        return clientRepository.adaugaClient(c);
    }

    public List<Client> stergeClient(int id) {
        return clientRepository.stergeClient(id);
    }

    public List<Client> updateTelefon(int id, long nr) {
        if( nr >= 100000 && nr < 1000000) {
            return clientRepository.updateTelefon(id, nr);
        } else {
            throw new ObjectNotFoundException("Numarul de telefon nu este valid!");
        }
    }

//    Angajati
    @Autowired
    private AngajatRepository angajatRepository;

    public List<Angajat> getAngajati() {
        return angajatRepository.getAngajati();
    }

    public Angajat getAngajatiByName(String name) {
        return angajatRepository.getAngajatiByName(name);
    }

    public List<Angajat> adaugaAngajat(Angajat a) {
        return angajatRepository.adaugaAngajat(a);
    }

    public List<Angajat> stergeAngajat(int idAngajat) {
        return angajatRepository.stergeAngajat(idAngajat);
    }

//    Servicii Frizerie
    @Autowired
    private ServiciuFrizerieRepository serviciuFrizerieRepository;

    public List<ServiciuFrizerie> getServicii() {
        return serviciuFrizerieRepository.getServicii();
    }

    public ServiciuFrizerie getServiciuByName(String name) {
        return serviciuFrizerieRepository.getServiciuByName(name);
    }

    public List<ServiciuFrizerie> adaugaServiciu(ServiciuFrizerie s) {
        return serviciuFrizerieRepository.adaugaServiciu(s);
    }

    public List<ServiciuFrizerie> stergeServiciu(int idServiciu) {
        return serviciuFrizerieRepository.stergeServiciu(idServiciu);
    }

//    Programari
    @Autowired
    private ProgramareRepository programareRepository;

    public List<Programare> getProgramari() {
        return programareRepository.getProgramari();
    }

    public Programare getProgramareById(int id) {
        return programareRepository.getProgramareById(id);
    }

    public List<Programare> adaugaProgramare(Programare p) {
        return programareRepository.adaugaProgramare(p);
    }

    public List<Programare> stergeProgramare(int idProgramare) {
        return programareRepository.stergeProgramare(idProgramare);
    }

//    Plati
    @Autowired
    private PlataRepository plataRepository;

    public List<Plata> getPlati() {
        return plataRepository.getPlati();
    }

    public List<Plata> adaugaPlata(int idProgramare) {
        return plataRepository.adaugaPlata(idProgramare);
    }
}
