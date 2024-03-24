package Util;

import Entités.Category;
import Entités.Commentaire;
import Entités.OeuvreArtistique;
import Services.CategoryService;
import Services.CommentaireService;
import Services.ServiceOeuvreArtistique;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        CategoryService categoryService = new CategoryService();

        byte[] image = null;
        String cheminImage = "D:/Etudes/ISET Charguia/DSI302/PFE Gestion de projets/Maquettage/account-settings.png";

        // Lecture de l'image et stockage dans un tableau de bytes
        try {
            Path cheminFichier = Paths.get(cheminImage);
            image = Files.readAllBytes(cheminFichier);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Création de l'instance OeuvreArtistique avec l'image
        OeuvreArtistique OA1 = new OeuvreArtistique("test", "t", 35F, 0, 0, 0, 0, 0, image);
        try {
            // Ajout de l'oeuvre dans la base de données
            serviceOeuvreArtistique.ajouterOeuvre(OA1);
            System.out.println("Oeuvre artistique ajoutée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Oeuvre Artistique by idArtiste
        int idArtiste = 1; // ID de l'artiste dont vous souhaitez obtenir les oeuvres
        List<OeuvreArtistique> oeuvresArtistique = serviceOeuvreArtistique.listerOeuvresParArtiste(idArtiste);
        for (OeuvreArtistique oeuvre : oeuvresArtistique) {
            System.out.println(oeuvre.getId()); // Affichage de chaque oeuvre
        }

        //Listing des catégories
        try {
            List<Category> categories = categoryService.getAllCategories();
            for (Category category : categories) {
                System.out.println(category); // Utilise la méthode toString() ou affiche les attributs individuellement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Catégorie par id
        try {
            int idToFind = 3;
            Category category = CategoryService.getCategorieById(idToFind);
            if (category != null) {
                System.out.println("Catégorie trouvée : " + category);
            } else {
                System.out.println("Aucune catégorie trouvée avec l'ID : " + idToFind);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
        //Ajout d'un commentaire
        /*Commentaire C1 = new Commentaire("test",1,1);
        try {
            commentaireService.ajouterCommentaire(C1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Listing des commentaires
        List<Commentaire> commentaires = commentaireService.getAllCommentaires();
        for (Commentaire commentaire : commentaires) {
            System.out.println(commentaire);
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

        //Modifier commentaire
        Commentaire NouveauCommentaire = new Commentaire(23,"testFinal2",new Date(), 3,4);
        try {
            commentaireService.updateCommentaire(NouveauCommentaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
}
