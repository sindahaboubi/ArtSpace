package Util;

import Entités.Commentaire;
import Entités.OeuvreArtistique;
import Services.CommentaireService;
import Services.ServiceOeuvreArtistique;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        //Connexion à la base de données
        Connection con1= DataSource.getInstance().getCon();
        System.out.println(con1);

        CommentaireService commentaireService = new CommentaireService();
        //Ajout d'une oeuvre artistique
        ServiceOeuvreArtistique serviceOeuvreArtistique = new ServiceOeuvreArtistique();
        OeuvreArtistique OA1 = new OeuvreArtistique("test","t",35F,0, 0, 0, 0,0);
        try {
            serviceOeuvreArtistique.ajouterOeuvre(OA1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Ajout d'un commentaire
        Commentaire C1 = new Commentaire("test",1,1);
        try {
            commentaireService.ajouterCommentaire(C1);
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

        // Listing des commentaires
        List<Commentaire> commentaires = commentaireService.getAllCommentaires();
        for (Commentaire commentaire : commentaires) {
            System.out.println(commentaire);
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


        OeuvreArtistique OA2 = new OeuvreArtistique(88,"titre","desc",14F,new Date(),0,0,0,0,0);
        //Supprimer oeuvre
        try {
            serviceOeuvreArtistique.supprimerOeuvre(OA2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Supprimer commentaire
        int commentToDeleteId = 37; // ID du commentaire à supprimer
        boolean commentaireExiste = false;
        for (Commentaire commentaire : commentaires) {
            if (commentaire.getId() == commentToDeleteId) {
                commentaireExiste = true;
                break;
            }
        }
        if (commentaireExiste) {
            commentaireService.deleteCommentaire(commentToDeleteId);
            System.out.println("Deleted Commentaire with id: " + commentToDeleteId);
        } else {
            System.out.println("No Commentaire with id " + commentToDeleteId + " to delete.");
        }

        //Modifier oeuvre
        OeuvreArtistique NouvelleOeuvre = new OeuvreArtistique(89,"ttt","dddd",40F,new Date(),1,1,2,3,4);
        try {
            serviceOeuvreArtistique.modifierOeuvre(NouvelleOeuvre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Modifier commentaire
        Commentaire NouveauCommentaire = new Commentaire(23,"testFinal2",new Date(), 3,4);
        try {
            commentaireService.updateCommentaire(NouveauCommentaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
