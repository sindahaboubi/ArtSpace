package Entités;

public class role {
    private int id;
    private String libelle;

    public role(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public role(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "role{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
