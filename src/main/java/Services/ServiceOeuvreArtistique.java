package Services;

import Entités.OeuvreArtistique;
import Util.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceOeuvreArtistique implements IService<OeuvreArtistique> {
    static Statement ste;
    static Connection con;
    @Override
    public void ajouterOeuvre(OeuvreArtistique oeuvreArtistique) throws SQLException {
        try{
            con = DataSource.getInstance().getCon();
            ste = con.createStatement();
            String req = "INSERT INTO oeuvreArtistique(titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation) VALUES ('" +
                    oeuvreArtistique.getTitre() + "', '" +
                    oeuvreArtistique.getDescription() + "', " +
                    oeuvreArtistique.getPrix() + ", NOW(), " +
                    oeuvreArtistique.getEtat() + ", " +
                    oeuvreArtistique.getIdArtiste() + ", " +
                    oeuvreArtistique.getIdCategorie() + ", " +
                    oeuvreArtistique.getIdMusee() + ", " +
                    oeuvreArtistique.getAcceptation() +
                    ")";
            ste.executeUpdate(req);
            System.out.println("Oeuvre artistique ajoutée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de l'oeuvre artistique: " + e.getMessage());
        }
    }

    @Override
    public List<OeuvreArtistique> listerOeuvres() throws SQLException {
        List<OeuvreArtistique> oeuvres = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");
            OeuvreArtistique oeuvre = new OeuvreArtistique(titre, description, prix, etat, idArtiste, idCategorie, idMusee, acceptation );
            oeuvre.setId(id);
            oeuvre.setDateCreation(dateCreation);
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }

    @Override
    public OeuvreArtistique OeuvreById(int id) throws SQLException {
        OeuvreArtistique oeuvre = null;
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique WHERE id = " + id);
        if (resultSet.next()) {
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");

            oeuvre = new OeuvreArtistique(titre, description, prix, etat, idArtiste, idCategorie, idMusee, acceptation);
            oeuvre.setId(id);
            oeuvre.setDateCreation(dateCreation);
        }
        return oeuvre;
    }

    @Override
    public void supprimerOeuvre(OeuvreArtistique oeuvreArtistique) throws SQLException {
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        String query = "DELETE FROM oeuvreArtistique WHERE id = " + oeuvreArtistique.getId();
        int rowsAffected = statement.executeUpdate(query);
        statement.close();

        if (rowsAffected == 0) {
            System.out.println("L'oeuvre avec l'ID " + oeuvreArtistique.getId() + " n'existe pas dans la base de données.");
        } else {
            System.out.println("L'oeuvre avec l'ID " + oeuvreArtistique.getId() + " a été supprimée avec succès.");
        }
    }

    public void modifierOeuvre(OeuvreArtistique oeuvreArtistique) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DataSource.getInstance().getCon();
            stmt = conn.createStatement();
            String query = "UPDATE oeuvreArtistique SET titre = '" + oeuvreArtistique.getTitre() +
                    "', description = '" + oeuvreArtistique.getDescription() +
                    "', prix = " + oeuvreArtistique.getPrix() +
                    ", dateCreation = '" + new java.sql.Date(oeuvreArtistique.getDateCreation().getTime()) +
                    "', Etat = " + oeuvreArtistique.getEtat() +
                    ", idArtiste = " + oeuvreArtistique.getIdArtiste() +
                    ", idCategorie = " + oeuvreArtistique.getIdCategorie() +
                    ", idMusee = " + oeuvreArtistique.getIdMusee() +
                    ", acceptation = " + oeuvreArtistique.getAcceptation() +
                    " WHERE id = " + oeuvreArtistique.getId();
            stmt.executeUpdate(query);
            System.out.println("Mise à jour effectuée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'oeuvre artistique : " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
