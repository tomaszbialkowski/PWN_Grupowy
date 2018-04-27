package Project.model;

public class ProjektModel {
    private int id_user;
    private String imie, nazwisko, p1, p2, p3, p4, o1, o2, o3, o4; //wszystkie ktore sa w bazie

    public ProjektModel(int id_user, String imie, String nazwisko, String p1, String p2, String p3, String p4, String o1, String o2, String o3, String o4) {
        this.id_user = id_user;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
    }

    public ProjektModel(String imie, String nazwisko, String p1, String p2, String p3, String p4, String o1, String o2, String o3, String o4) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
    }

    public ProjektModel(int id_user, String imie, String nazwisko, String p1, String p2, String p3, String p4) {
        this.id_user = id_user;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getO1() {
        return o1;
    }

    public void setO1(String o1) {
        this.o1 = o1;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getO4() {
        return o4;
    }

    public void setO4(String o4) {
        this.o4 = o4;
    }

    @Override
    public String toString() {
        return "ProjektModel{" +
                "id_user=" + id_user +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", p1='" + p1 + '\'' +
                ", p2='" + p2 + '\'' +
                ", p3='" + p3 + '\'' +
                ", p4='" + p4 + '\'' +
                ", o1='" + o1 + '\'' +
                ", o2='" + o2 + '\'' +
                ", o3='" + o3 + '\'' +
                ", o4='" + o4 + '\'' +
                '}';
    }
}
