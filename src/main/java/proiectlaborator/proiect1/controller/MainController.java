package proiectlaborator.proiect1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiectlaborator.proiect1.model.*;
import proiectlaborator.proiect1.service.MainService;
import proiectlaborator.proiect1.utils.ObjectNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/frizerie")
public class MainController {
    @Autowired
    private MainService mainService;

    //    Clienti
    @GetMapping("/getClienti")
    public List<Client> getClienti() {
        return mainService.getClienti();
    }

    @GetMapping("/getClientiByName")
    public ResponseEntity<?> getClientByName(@RequestParam String name) {
        return ResponseEntity.ok().body(mainService.getClientByName(name));
    }

    @GetMapping("/getClientiById")
    public ResponseEntity<?> getClientById(@RequestParam int id) {
        return ResponseEntity.ok().body(mainService.getClientById(id));
    }

    @PostMapping("/adaugaClient")
    public List<Client> adaugaClient(@RequestBody @Valid Client c) {
        return mainService.adaugaClient(c);
    }

    @PostMapping("/updateTelefon")
    public List<Client> updateTelefon(@RequestParam @Valid int id, long nr) {
        return mainService.updateTelefon(id, nr);
    }

    @DeleteMapping("/stergeClient")
    public List<Client> stergeClient(@RequestParam int id) {
        return mainService.stergeClient(id);
    }

//    Angajati

    @GetMapping("/getAngajati")
    public List<Angajat> getAngajati() {
        return mainService.getAngajati();
    }

    @GetMapping("/getAngajatiByName")
    public ResponseEntity<?> getAngajatByName(@RequestParam String name) {
        return ResponseEntity.ok().body(mainService.getAngajatiByName(name));
    }

    @PostMapping("/adaugaAngajat")
    public List<Angajat> adaugaAngajat(@RequestBody @Valid Angajat a) {
        return mainService.adaugaAngajat(a);
    }

    @DeleteMapping("/stergeAngajat")
    public List<Angajat> stergeAngajat(@RequestParam int id) {
        return mainService.stergeAngajat(id);
    }

//    Servicii Frizerie

    @GetMapping("/getServicii")
    public List<ServiciuFrizerie> getServicii() {
        return mainService.getServicii();
    }

    @GetMapping("/getServiciuByName")
    public ResponseEntity<?> getServiciuByName(@RequestParam String name) {
        return ResponseEntity.ok().body(mainService.getServiciuByName(name));
    }

    @PostMapping("/adaugaServiciu")
    public List<ServiciuFrizerie> adaugaServiciu(@RequestBody @Valid ServiciuFrizerie a) {
        return mainService.adaugaServiciu(a);
    }

    @DeleteMapping("/stergeServiciu")
    public List<ServiciuFrizerie> stergeServiciu(@RequestParam int id) {
        return mainService.stergeServiciu(id);
    }

//    Programari

    @GetMapping("/getProgramari")
    public List<Programare> getProgramari() {
        return mainService.getProgramari();
    }

    @GetMapping("/getProgramareById")
    public ResponseEntity<?> getProgramareByName(@RequestParam int id) {
        return ResponseEntity.ok().body(mainService.getProgramareById(id));
    }

    @PostMapping("/adaugaProgramare")
    public List<Programare> adaugaProgramare(@RequestBody @Valid Programare p) {
        return mainService.adaugaProgramare(p);
    }

    @DeleteMapping("/stergeProgramare")
    public List<Programare> stergeProgramare(@RequestParam int id) {
        return mainService.stergeProgramare(id);
    }

    //    Plati
    @GetMapping("/getPlati")
    public List<Plata> getPlati() {
        return mainService.getPlati();
    }

    @PostMapping("/adaugaPlata")
    public List<Plata> adaugaPlata(@RequestParam int idProgramare) {
        return mainService.adaugaPlata(idProgramare);
    }

}
