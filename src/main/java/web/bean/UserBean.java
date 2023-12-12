package web.bean;

import java.time.LocalDate;

public class UserBean {

    public String getMexB() {
        return mexB;
    }

    public void setMexB(String mexB) {
        this.mexB = mexB;
    }

    enum Ruoli {
        ADMIN,
        UTENTE,
        SCRITTORE,
        EDITORE;
    }

    private int idB;
    private String ruoloB;
    private String emailB;
    private String passB;
    private String mexB;

    private String nomeB;
    private String cognomeB;

    private LocalDate dataDiNascitaB;

    public String getNomeB() {
        return nomeB;
    }

    public void setNomeB(String nomeB) {
        this.nomeB = nomeB;
    }

    public String getCognomeB() {
        return cognomeB;
    }

    public void setCognomeB(String cognomeB) {
        this.cognomeB = cognomeB;
    }

    public LocalDate getDataDiNascitaB() {
        return dataDiNascitaB;
    }

    public void setDataDiNascitaB(LocalDate dataDiNascitaB) {
        this.dataDiNascitaB = dataDiNascitaB;
    }

    public String getDescrizioneB() {
        return descrizioneB;
    }

    public void setDescrizioneB(String descrizioneB) {
        this.descrizioneB = descrizioneB;
    }

    private String descrizioneB;

    private static UserBean instance=null;

    public static UserBean getInstance()
    {
        if(instance==null)
            instance=new UserBean();
        return instance;
    }

    private UserBean() {}

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public String getRuoloB() {
        return ruoloB;
    }

    public void setRuoloB(String ruoloB) {
       this.ruoloB= getRuolo(ruoloB);

    }

    public String getEmailB() {
        return emailB;
    }

    public void setEmailB(String emailB) {
        if(emailB.isEmpty() )
        {
            this.emailB=null;
        }
        this.emailB = emailB;
    }

    public String getPassB() {
        return passB;
    }

    public void setPassB(String passB) {
        if(passB.isEmpty())
            this.passB=null;
        this.passB = passB;
    }

    private String getRuolo(String r)
    {
        String rB = switch (r) {
            case "ADMIN", "A" -> Ruoli.ADMIN.toString();
            case "EDITORE", "E" -> Ruoli.EDITORE.toString();
            case "SCRITTORE", "W" -> Ruoli.SCRITTORE.toString();
            case "UTENTE", "U" -> Ruoli.UTENTE.toString();
            default -> null;
        };

        assert rB != null;
        return rB.substring(0,1);
    }
}
