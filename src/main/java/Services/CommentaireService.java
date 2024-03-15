package Services;

import Entités.Commentaire;
import Entités.OeuvreArtistique;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireService {

    static Statement ste;
    static Connection con;

    // Create
    // Create

    public void ajouterCommentaire(Commentaire commentaire) throws SQLException {
        try{
            con = DataSource.getInstance().getCon();
            ste = con.createStatement();
            String req = "INSERT INTO commentaire(content, idOeuvre, idClient, dateCreation) VALUES ('" +
                    commentaire.getContent() + "', " +
                    commentaire.getIdOeuvre() + ", " +
                    commentaire.getIdClient() + ", NOW()" +
                    ")";

            ste.executeUpdate(req);
            System.out.println("Commentaire ajouté avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout du comentaire: " + e.getMessage());
        }
    }

    // Read
   public List<Commentaire> getAllCommentaires() throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Commentaire commentaire = new Commentaire(
                        resultSet.getInt("id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("datecreation"),
                        resultSet.getInt("idoeuvre"),
                        resultSet.getInt("idclient")
                );
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }

    // Update
        public void updateCommentaire(Commentaire commentaire) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DataSource.getInstance().getCon();
            stmt = conn.createStatement();
            String query = "UPDATE commentaire SET content = '" + commentaire.getContent() +
                    "', idClient = '" + commentaire.getIdClient() +
                    "', idOeuvre = " + commentaire.getIdOeuvre() +
                    ", dateCreation = '" + new java.sql.Date(commentaire.getDateCreation().getTime()) +
                    "' WHERE id = " + commentaire.getId();


            stmt.executeUpdate(query);
            System.out.println("Mise à jour effectuée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du commentaire : " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    // Delete
    public void deleteCommentaire(int id) {
        String query = "DELETE FROM commentaire WHERE id=?";
        try {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
