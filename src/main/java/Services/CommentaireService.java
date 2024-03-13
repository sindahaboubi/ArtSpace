package Services;

import Entités.Commentaire;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireService {

    private Connection connection;

    public CommentaireService() throws SQLException {
        connection = DataSource.getConnection();
    }

    // Create
    // Create
    public void addCommentaire(Commentaire commentaire) {
        String query = "INSERT INTO commentaire (content, datecreation, idouevre, idclient) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, commentaire.getContent());
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Assuming datecreation is a timestamp
            preparedStatement.setInt(3, commentaire.getIdOeuvre());
            preparedStatement.setInt(4, commentaire.getIdClient());
            preparedStatement.executeUpdate();

            // Retrieve the generated id and set it in the Commentaire object
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                commentaire.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Read
    public List<Commentaire> getAllCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Commentaire commentaire = new Commentaire(
                        resultSet.getInt("id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("datecreation"),
                        resultSet.getInt("idouevre"),
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
    public void updateCommentaire(Commentaire commentaire) {
        String query = "UPDATE commentaire SET content=?, datecreation=?, idouevre=?, idclient=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, commentaire.getContent());
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Assuming datecreation is a timestamp
            preparedStatement.setInt(3, commentaire.getIdOeuvre());
            preparedStatement.setInt(4, commentaire.getIdClient());
            preparedStatement.setInt(5, commentaire.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteCommentaire(int id) {
        String query = "DELETE FROM commentaire WHERE id=?";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
