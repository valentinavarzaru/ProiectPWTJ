package proiectlaborator.proiect1.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ServiciuFrizerie {
    private int idServiciu;
    @NotNull(message = "Numele serviciului nu poate fi null!")
    private String numeServiciu;
    private String descriereServiciu;
    @Min(5)
    private Double pretServiciu;

    public ServiciuFrizerie() {}

    public ServiciuFrizerie(int idServiciu, String numeServiciu, String descriereServiciu, Double pretServiciu) {
        this.idServiciu = idServiciu;
        this.numeServiciu = numeServiciu;
        this.descriereServiciu = descriereServiciu;
        this.pretServiciu = pretServiciu;
    }

    public int getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(int idServiciu) {
        this.idServiciu = idServiciu;
    }

    public String getNumeServiciu() {
        return numeServiciu;
    }

    public void setNumeServiciu(String numeServiciu) {
        this.numeServiciu = numeServiciu;
    }

    public String getDescriereServiciu() {
        return descriereServiciu;
    }

    public void setDescriereServiciu(String descriereServiciu) {
        this.descriereServiciu = descriereServiciu;
    }

    public Double getPretServiciu() {
        return pretServiciu;
    }

    public void setPretServiciu(Double pretServiciu) {
        this.pretServiciu = pretServiciu;
    }

    @Override
    public String toString() {
        return "ServiciuFrizerie{" +
                "idServiciu=" + idServiciu +
                ", numeServiciu='" + numeServiciu + '\'' +
                ", descriereServiciu='" + descriereServiciu + '\'' +
                ", pretServiciu=" + pretServiciu +
                '}';
    }
}
