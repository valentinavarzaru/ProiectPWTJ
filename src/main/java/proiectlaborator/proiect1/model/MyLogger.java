package proiectlaborator.proiect1.model;

import java.util.Date;

public class MyLogger {
    private int idLogger;
    private Date dataLogger;
    private String oraLogger;
    private String repoLogger;
    private String contentLogger;

    public MyLogger() {}

    public MyLogger(int idLogger, Date dataLogger, String oraLogger, String repoLogger, String contentLogger) {
        this.idLogger = idLogger;
        this.dataLogger = dataLogger;
        this.oraLogger = oraLogger;
        this.repoLogger = repoLogger;
        this.contentLogger = contentLogger;
    }

    public int getIdLogger() {
        return idLogger;
    }

    public void setIdLogger(int idLogger) {
        this.idLogger = idLogger;
    }

    public Date getDataLogger() {
        return dataLogger;
    }

    public void setDataLogger(Date dataLogger) {
        this.dataLogger = dataLogger;
    }

    public String getOraLogger() {
        return oraLogger;
    }

    public void setOraLogger(String oraLogger) {
        this.oraLogger = oraLogger;
    }

    public String getRepoLogger() {
        return repoLogger;
    }

    public void setRepoLogger(String repoLogger) {
        this.repoLogger = repoLogger;
    }

    public String getContentLogger() {
        return contentLogger;
    }

    public void setContentLogger(String contentLogger) {
        this.contentLogger = contentLogger;
    }

    @Override
    public String toString() {
        return "MyLogger{" +
                "idLogger=" + idLogger +
                ", dataLogger=" + dataLogger +
                ", oraLogger='" + oraLogger + '\'' +
                ", repoLogger='" + repoLogger + '\'' +
                ", contentLogger='" + contentLogger + '\'' +
                '}';
    }
}
