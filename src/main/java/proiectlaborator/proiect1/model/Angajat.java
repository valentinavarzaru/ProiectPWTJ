package proiectlaborator.proiect1.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Angajat {
    private int idAngajat;
    @NotNull(message = "Numele angajatului nu poate fi null!")
    private String numeAngajat;
    @NotNull(message = "Prenumele angajatului nu poate fi null!")
    private String prenumeAngajat;
    @Min(5)
    private Double taxaPerServiciu;

    public Angajat() {}

    public Angajat(int idAngajat, String numeAngajat, String prenumeAngajat, Double taxaPerServiciu) {
        this.idAngajat = idAngajat;
        this.numeAngajat = numeAngajat;
        this.prenumeAngajat = prenumeAngajat;
        this.taxaPerServiciu = taxaPerServiciu;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public String getNumeAngajat() {
        return numeAngajat;
    }

    public void setNumeAngajat(String numeAngajat) {
        this.numeAngajat = numeAngajat;
    }

    public String getPrenumeAngajat() {
        return prenumeAngajat;
    }

    public void setPrenumeAngajat(String prenumeAngajat) {
        this.prenumeAngajat = prenumeAngajat;
    }

    public Double getTaxaPerServiciu() {
        return taxaPerServiciu;
    }

    public void setTaxaPerServiciu(Double taxaPerServiciu) {
        this.taxaPerServiciu = taxaPerServiciu;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "idAngajat=" + idAngajat +
                ", numeAngajat='" + numeAngajat + '\'' +
                ", prenumeAngajat='" + prenumeAngajat + '\'' +
                ", taxaPerServiciu=" + taxaPerServiciu +
                '}';
    }
}
