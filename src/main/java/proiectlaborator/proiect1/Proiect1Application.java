package proiectlaborator.proiect1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proiectlaborator.proiect1.model.Angajat;
import proiectlaborator.proiect1.service.MainService;

import java.time.Instant;
import java.time.LocalTime;

@SpringBootApplication
public class Proiect1Application implements CommandLineRunner {

//    @Autowired
//    private MainService mainService;

    public static void main(String[] args) {
        SpringApplication.run(Proiect1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Angajat angajat = new Angajat(20,"Prajina", "Dan", 10.99);
//        mainService.adaugaAngajat(angajat);
//        mainService.getPlati();

//        String currentHour = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
//        System.out.println(currentHour);

    }

}
