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
    public ResponseEntity<List<Client>> getClienti() {
        return ResponseEntity.status(HttpStatus.OK).body(mainService.getClienti());
    }

    @GetMapping("/getClientiByName")
    public ResponseEntity<Client> getClientByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getClientByName(name));
    }

    @GetMapping("/getClientiById")
    public ResponseEntity<Client> getClientById(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getClientById(id));
    }

    @PostMapping("/adaugaClient")
    public ResponseEntity<List<Client>> adaugaClient(@RequestBody @Valid Client c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mainService.adaugaClient(c));
    }

    @PostMapping("/updateTelefon")
    public ResponseEntity<List<Client>> updateTelefon(@RequestParam @Valid int id, long nr) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.updateTelefon(id, nr));
    }

    @DeleteMapping("/stergeClient")
    public ResponseEntity<List<Client>> stergeClient(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.stergeClient(id));
    }

//    Angajati

    @GetMapping("/getAngajati")
    public ResponseEntity<List<Angajat>> getAngajati() {
        return ResponseEntity.status(HttpStatus.OK).body(mainService.getAngajati());
    }

    @GetMapping("/getAngajatiByName")
    public ResponseEntity<Angajat> getAngajatByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getAngajatiByName(name));
    }

    @GetMapping("/getAngajatiById")
    public ResponseEntity<Angajat> getAngajatiById(@RequestParam int idAngajat) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getAngajatiById(idAngajat));
    }

    @PostMapping("/adaugaAngajat")
    public ResponseEntity<List<Angajat>> adaugaAngajat(@RequestBody @Valid Angajat a) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mainService.adaugaAngajat(a));
    }

    @PostMapping("/updateTaxa")
    public ResponseEntity<List<Angajat>> updateTaxa(@RequestParam @Valid int idAngajat, Double taxa) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.updateTaxa(idAngajat,taxa));
    }

    @DeleteMapping("/stergeAngajat")
    public ResponseEntity<List<Angajat>> stergeAngajat(@RequestParam int idAngajat) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.stergeAngajat(idAngajat));
    }

//    Servicii Frizerie

    @GetMapping("/getServicii")
    public ResponseEntity<List<ServiciuFrizerie>> getServicii() {
        return ResponseEntity.status(HttpStatus.OK).body(mainService.getServicii());
    }

    @GetMapping("/getServiciuByName")
    public ResponseEntity<?> getServiciuByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getServiciuByName(name));
    }

    @GetMapping("/getServiciuById")
    public ResponseEntity<ServiciuFrizerie> getServiciuById(int idServiciu) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getServiciuById(idServiciu));
    }

    @PostMapping("/adaugaServiciu")
    public ResponseEntity<List<ServiciuFrizerie>> adaugaServiciu(@RequestBody @Valid ServiciuFrizerie a) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mainService.adaugaServiciu(a));
    }

    @PostMapping("/updatePretServiciu")
    public ResponseEntity<List<ServiciuFrizerie>> updatePretServiciu(int idServiciu, Double pret) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.updatePretServiciu(idServiciu, pret));
    }

    @DeleteMapping("/stergeServiciu")
    public ResponseEntity<List<ServiciuFrizerie>> stergeServiciu(@RequestParam int idServiciu) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.stergeServiciu(idServiciu));
    }

//    Programari

    @GetMapping("/getProgramari")
    public ResponseEntity<List<Programare>> getProgramari() {
        return ResponseEntity.status(HttpStatus.OK).body(mainService.getProgramari());
    }

    @GetMapping("/getProgramareById")
    public ResponseEntity<Programare> getProgramareById(@RequestParam int idProg) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getProgramareById(idProg));
    }

    @PostMapping("/adaugaProgramare")
    public ResponseEntity<List<Programare>> adaugaProgramare(@RequestBody @Valid Programare p, @RequestParam String metPlata) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mainService.adaugaProgramare(p, metPlata));
    }

    @PostMapping("/updateDataProg")
    public ResponseEntity<List<Programare>> updateDataProg(@RequestParam @Valid int idProg, String data) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.updateDataProg(idProg, data));
    }

    @PostMapping("/updateOraProg")
    public ResponseEntity<List<Programare>> updateOraProg(@RequestParam @Valid int idProg, String ora) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.updateOraProg(idProg, ora));
    }

    @DeleteMapping("/stergeProgramare")
    public ResponseEntity<List<Programare>> stergeProgramare(@RequestParam int idProg) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.stergeProgramare(idProg));
    }

    //    Plati
    @GetMapping("/getPlati")
    public ResponseEntity<List<Plata>> getPlati() {
        return ResponseEntity.status(HttpStatus.OK).body(mainService.getPlati());
    }

    @GetMapping("/getPlataById")
    public ResponseEntity<Plata> getPlataById(@RequestParam int idPlata) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getPlataById(idPlata));
    }

    @GetMapping("/getPlataByIdProg")
    public ResponseEntity<?> getPlataByIdProg(@RequestParam int idProg) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.getPlataByIdProg(idProg));
    }

    @GetMapping("/afisarePretFinal")
    public ResponseEntity<?> afisarePretFinal(@RequestParam int idProgramare) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mainService.afisarePretFinal(idProgramare));
    }

    @PostMapping("/adaugaPlata")
    public ResponseEntity<List<Plata>> adaugaPlata(@RequestParam int idProgramare, String metPlata) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mainService.adaugaPlata(idProgramare, metPlata));
    }

    @DeleteMapping("/stergePlata")
    public ResponseEntity<List<Plata>> stergePlata(@RequestParam int idPlata) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mainService.stergePlata(idPlata));
    }

}
