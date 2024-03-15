package Services;

import Entités.Musee;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MuseeService {

    private Connection connection;

    public MuseeService() throws SQLException {
        connection = DataSource.getConnection();
    }

    // Create
    public void addMusee(Musee musee) {
        String query = "INSERT INTO musee (nom, description, ville, dateDebut, dateFin, heureOuverture, heureFermeture, idArtiste) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, musee.getNom());
            preparedStatement.setString(2, musee.getDescription());
            preparedStatement.setString(3, musee.getVille());
            preparedStatement.setString(4, musee.getDateDebut());
            preparedStatement.setString(5, musee.getDateFin());
            preparedStatement.setString(6, musee.getHeureOuverture());
            preparedStatement.setString(7, musee.getHeureFermeture());
            preparedStatement.setInt(8, musee.getIdArtist());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Musee> getAllMusees() {
        List<Musee> musees = new ArrayList<>();
        String query = "SELECT * FROM musee";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Musee musee = new Musee(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("description"),
                        resultSet.getString("ville"),
                        resultSet.getString("dateDebut"),
                        resultSet.getString("dateFin"),
                        resultSet.getString("heureOuverture"),
                        resultSet.getString("heureFermeture"),
                        resultSet.getInt("idArtiste")
                );
                musees.add(musee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musees;
    }

    // Update
    public void updateMusee(Musee musee) {
        String query = "UPDATE musee SET nom=?, description=?, ville=?, dateDebut=?, dateFin=?, heureOuverture=?, heureFermeture=?, idArtiste=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, musee.getNom());
            preparedStatement.setString(2, musee.getDescription());
            preparedStatement.setString(3, musee.getVille());
            preparedStatement.setString(4, musee.getDateDebut());
            preparedStatement.setString(5, musee.getDateFin());
            preparedStatement.setString(6, musee.getHeureOuverture());
            preparedStatement.setString(7, musee.getHeureFermeture());
            preparedStatement.setInt(8, musee.getIdArtist());
            preparedStatement.setInt(9, musee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteMusee(int id) {
        String query = "DELETE FROM musee WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
