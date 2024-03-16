package Entités;

import java.util.Date;

// Entité Commande
public class Commande {
    private int idCommande;
    private int Qte;
    private Date dateCommande;
    private int idOeuvre;
    private int idClient;

    // Constructeurs
    public Commande() {
    }

    public Commande(int idCommande, int idClient, int idOeuvre, int Qte, Date dateCommande) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.idOeuvre = idOeuvre;
        this.Qte = Qte;
        this.dateCommande = dateCommande;
    }

    // Getters et Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
}
