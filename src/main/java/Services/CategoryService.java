package Services;

import Entités.Category;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private Connection connection;

    public CategoryService() throws SQLException {
        connection = DataSource.getConnection();
    }


    public void addCategory(Category category) {
        String query = "INSERT INTO category (Id_category, Name, Description) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, category.getName());
            // Assuming datecreation is a timestamp
            preparedStatement.setString(3, category.getDescription());
            preparedStatement.setString(1, category.getId_category());

            preparedStatement.executeUpdate();

            // Retrieve the generated id and set it in the Category object
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.set(generatedKeys.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Read
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getString("Id_category"),
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getTimestamp("Created_Date")

                );
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Update
    public void updateCategory(Category category) {
        String query = "UPDATE category SET Name=?, Description=?, Created_Date=? WHERE Id_category=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, category.getName());
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Assuming datecreation is a timestamp
            preparedStatement.setString(3, category.getDescription());

            preparedStatement.setString(1, category.getId_category());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteCategory(String Id_category) {
        String query = "DELETE FROM category WHERE Id_category=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Id_category);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
