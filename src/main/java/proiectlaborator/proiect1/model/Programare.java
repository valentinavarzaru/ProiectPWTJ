package proiectlaborator.proiect1.model;

import javax.validation.constraints.NotNull;

public class Programare {
    private int idProgramare;
    private int idClient;
    private int idServiciu;
    private int idAngajat;
    @NotNull(message = "Data nu poate fi nula!")
    private String data;
    @NotNull(message = "Ora nu poate fi nula!")
    private String ora;

    public Programare() {}

    public Programare(int idProgramare, int idClient, int idServiciu, int idAngajat, String data, String ora) {
        this.idProgramare = idProgramare;
        this.idClient = idClient;
        this.idServiciu = idServiciu;
        this.idAngajat = idAngajat;
        this.data = data;
        this.ora = ora;
    }

    public int getIdProgramare() {
        return idProgramare;
    }

    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(int idServiciu) {
        this.idServiciu = idServiciu;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    @Override
    public String toString() {
        return "Programare{" +
                "idProgramare=" + idProgramare +
                ", idClient=" + idClient +
                ", idServiciu=" + idServiciu +
                ", idAngajat=" + idAngajat +
                ", data='" + data + '\'' +
                ", ora='" + ora + '\'' +
                '}';
    }
}
