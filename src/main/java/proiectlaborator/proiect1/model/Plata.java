package proiectlaborator.proiect1.model;

import javax.validation.constraints.Min;

public class Plata {
    private int idPlata;
    private int idProgramare;
    private String metodaDePlata;
    @Min(5)
    private Double pretFinal;

    public Plata() {}

    public Plata(int idPlata, int idProgramare, String metodaDePlata, Double pretFinal) {
        this.idPlata = idPlata;
        this.idProgramare = idProgramare;
        this.metodaDePlata = metodaDePlata;
        this.pretFinal = pretFinal;
    }

    public int getIdPlata() {
        return idPlata;
    }

    public void setIdPlata(int idPlata) {
        this.idPlata = idPlata;
    }

    public int getIdProgramare() {
        return idProgramare;
    }

    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public String getMetodaDePlata() {
        return metodaDePlata;
    }

    public void setMetodaDePlata(String metodaDePlata) {
        this.metodaDePlata = metodaDePlata;
    }

    public Double getPretFinal() {
        return pretFinal;
    }

    public void setPretFinal(Double pretFinal) {
        this.pretFinal = pretFinal;
    }

    @Override
    public String toString() {
        return "Plata{" +
                "idPlata=" + idPlata +
                ", idProgramare=" + idProgramare +
                ", metodaDePlata='" + metodaDePlata + '\'' +
                ", pretFinal=" + pretFinal +
                '}';
    }
}
