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
        // Instantiate CommentaireService
        CommentaireService commentaireService = new CommentaireService();
        // Example usage: Adding a Commentaire
        Commentaire commentaireToAdd = new Commentaire("Great arts-pace!", null, 1, 2);
        commentaireService.addCommentaire(commentaireToAdd);
        System.out.println("Added Commentaire: " + commentaireToAdd);

        // Example usage: Getting all Commentaires
        List<Commentaire> commentaires = commentaireService.getAllCommentaires();
        for (Commentaire commentaire : commentaires) {
            System.out.println(commentaire);
        }
        // Example usage: Updating a Commentaire (assuming the Commentaire with id 1 exists)
        if (!commentaires.isEmpty()) {
            Commentaire commentaireToUpdate = commentaires.get(0); // Assuming the first Commentaire in the list should be updated
            commentaireToUpdate.setContent("Updated arts-pace comment!");
            commentaireService.updateCommentaire(commentaireToUpdate);
            System.out.println("Updated Commentaire: " + commentaireToUpdate);
        } else {
            System.out.println("No Commentaire to update.");
        }
// Example usage: Deleting a Commentaire (assuming there is at least one Commentaire in the list)
if (!commentaires.isEmpty()) {

    //Commentaire commentaireToDelete = commentaires.get(0); // Assuming the first Commentaire in the list should be deleted
    Commentaire commentaireToDelete = new Commentaire();
    commentaireToDelete.setId(17);
    int commentToDeleteId = commentaireToDelete.getId();
    commentaireService.deleteCommentaire(commentToDeleteId);
    System.out.println("Deleted Commentaire with id: " + commentToDeleteId);
} else {
    System.out.println("No Commentaire to delete.");
}
    }

    
}
