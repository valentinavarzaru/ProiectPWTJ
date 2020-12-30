package proiectlaborator.proiect1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiectlaborator.proiect1.model.*;
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
        clientRepository.getClienti();
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

    public Angajat getAngajatiById(int idAngajat) {
        return angajatRepository.getAngajatiById(idAngajat);
    }

    public List<Angajat> adaugaAngajat(Angajat a) {
        return angajatRepository.adaugaAngajat(a);
    }

    public List<Angajat> updateTaxa(int idAngajat, Double taxa) {
        return angajatRepository.updateTaxa(idAngajat, taxa);
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

    public ServiciuFrizerie getServiciuById(int idServiciu) {
        return serviciuFrizerieRepository.getServiciuById(idServiciu);
    }

    public List<ServiciuFrizerie> updatePretServiciu(int idServiciu, Double pret) {
        return serviciuFrizerieRepository.updatePretServiciu(idServiciu, pret);
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

    public List<Programare> adaugaProgramare(Programare p, String metPlata) {
        return programareRepository.adaugaProgramare(p, metPlata);
    }

    public List<Programare> updateDataProg(int idProg, String data) {
        return programareRepository.updateDataProg(idProg, data);
    }

    public List<Programare> updateOraProg(int idProg, String ora) {
        return programareRepository.updateOraProg(idProg, ora);
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

    public Plata getPlataById(int id) {
        return plataRepository.getPlataById(id);
    }

    public List<Plata> getPlataByIdProg(int idProg) {
        return plataRepository.getPlataByIdProg(idProg);
    }

    public Double afisarePretFinal(int idPlata) {
        return plataRepository.afisarePretFinal(idPlata);
    }

    public List<Plata> adaugaPlata(int idProgramare, String metPlata) {
        return plataRepository.adaugaPlata(idProgramare, metPlata);
    }

    public List<Plata> stergePlata(int idPlata) {
        return plataRepository.stergePlata(idPlata);
    }
}
