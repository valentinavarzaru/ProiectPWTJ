package proiectlaborator.proiect1.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class Client {
    private int idClient;
    @NotNull(message = "Numele clientului nu poate fi null!")
    private String numeClient;
    @NotNull(message = "Prenumele clientului nu poate fi null!")
    private String prenumeClient;
    private String gen;
    @Range(min=100000, max = 999999, message = "Numarul de telefon nu este valid!")
    private long nrTelefon;

    public Client() {}

    public Client(@NotNull(message = "Numele clientului nu poate fi null!") String numeClient, @NotNull(message = "Prenumele clientului nu poate fi null!") String prenumeClient, String gen, @Range(min = 10, max = 10, message = "Numarul de telefon nu este valid!") long nrTelefon) {
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.gen = gen;
        this.nrTelefon = nrTelefon;
    }

    public Client(int idClient, @NotNull(message = "Numele clientului nu poate fi null!") String numeClient, @NotNull(message = "Prenumele clientului nu poate fi null!") String prenumeClient, String gen, @Range(min = 10, max = 10, message = "Numarul de telefon nu este valid!") long nrTelefon) {
        this.idClient = idClient;
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.gen = gen;
        this.nrTelefon = nrTelefon;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getPrenumeClient() {
        return prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public long getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(long nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", numeClient='" + numeClient + '\'' +
                ", prenumeClient='" + prenumeClient + '\'' +
                ", gen='" + gen + '\'' +
                ", nrTelefon=" + nrTelefon +
                '}';
    }
}
