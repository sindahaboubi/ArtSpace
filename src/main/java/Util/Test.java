package Util;

import Entités.OeuvreArtistique;
import Services.ServiceOeuvreArtistique;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //Connexion à la base de données
        Connection con1= DataSource.getInstance().getCon();
        System.out.println(con1);

        //Ajout d'une oeuvre artistique
        ServiceOeuvreArtistique serviceOeuvreArtistique = new ServiceOeuvreArtistique();
        OeuvreArtistique OA1 = new OeuvreArtistique("test","t",35F,0, 0, 0, 0,0);
        try {
            serviceOeuvreArtistique.ajouterOeuvre(OA1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Listing des oeuvres artistiques
        try {
            List<OeuvreArtistique> oeuvres = serviceOeuvreArtistique.listerOeuvres();
            for (OeuvreArtistique oeuvre : oeuvres) {
                System.out.println(oeuvre); // Utilise la méthode toString() ou affiche les attributs individuellement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Oeuvre artistique par id
        try {
            int idToFind = 14;
            OeuvreArtistique oeuvre = serviceOeuvreArtistique.OeuvreById(idToFind);
            if (oeuvre != null) {
                System.out.println("Oeuvre trouvée : " + oeuvre);
            } else {
                System.out.println("Aucune oeuvre trouvée avec l'ID : " + idToFind);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        OeuvreArtistique OA2 = new OeuvreArtistique(15,"titre","desc",14F,new Date(),0,0,0,0,0);
        //Supprimer oeuvre
        try {
            serviceOeuvreArtistique.supprimerOeuvre(OA2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Modifier oeuvre
        OeuvreArtistique NouvelleOeuvre = new OeuvreArtistique(17,"ttt","dddd",40F,new Date(),1,1,2,3,4);
        try {
            serviceOeuvreArtistique.modifierOeuvre(NouvelleOeuvre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
