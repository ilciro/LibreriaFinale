package web.bean;

public class NegozioBean {
    private String nomeB;
    private boolean isOpenB;
    private boolean isValidB;

    private String mexB;

    public String getNomeB() {
        return nomeB;
    }

    public void setNomeB(String nomeB) {
        this.nomeB = nomeB;
    }

    public boolean isOpenB() {
        return isOpenB;
    }

    public void setOpenB(boolean openB) {
        isOpenB = openB;
    }

    public boolean isValidB() {
        return isValidB;
    }

    public void setValidB(boolean validB) {
        isValidB = validB;
    }

    public String getMexB() {
        return mexB;
    }

    public void setMexB(String mexB) {
        this.mexB = mexB;
    }
}
