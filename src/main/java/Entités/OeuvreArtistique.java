package Entités;

import java.util.Date;

public class OeuvreArtistique {
    private int id;
    private String titre;
    private String description;
    private float prix;
    private Date dateCreation;
    private int Etat;
    private int idArtiste;
    private int idCategorie;
    private int idMusee;
    private int acceptation;

    public OeuvreArtistique(String titre, String description, float prix, int etat, int idArtiste, int idCategorie, int idMusee, int acceptation) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        Etat = etat;
        this.idArtiste = idArtiste;
        this.idCategorie = idCategorie;
        this.idMusee=idMusee;
        this.acceptation=acceptation;
    }

    public OeuvreArtistique(int id, String titre, String description, float prix, Date dateCreation, int etat, int idArtiste, int idCategorie, int idMusee, int acceptation) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.dateCreation = dateCreation;
        Etat = etat;
        this.idArtiste = idArtiste;
        this.idCategorie = idCategorie;
        this.idMusee=idMusee;
        this.acceptation=acceptation;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public int getEtat() {
        return Etat;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public int getIdMusee() {
        return idMusee;
    }

    public int getAcceptation() {
        return acceptation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setEtat(int etat) {
        Etat = etat;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setIdMusee(int idMusee) {
        this.idMusee = idMusee;
    }

    public void setAcceptation(int acceptation) {
        this.acceptation = acceptation;
    }

    @Override
    public String toString() {
        return "OeuvreArtistique{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", dateCreation=" + dateCreation +
                ", Etat=" + Etat +
                ", idArtiste=" + idArtiste +
                ", idCategorie=" + idCategorie +
                ", idMusee=" + idMusee +
                ", acceptation=" + acceptation +
                '}';
    }
}
