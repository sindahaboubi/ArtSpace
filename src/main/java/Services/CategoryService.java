package Services;

import Entités.Category;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryService {

    private Connection connection;
    static Statement ste;

    public CategoryService() {
    }

    public void addCategory(Category category) {
        String query = "INSERT INTO category (Id_category, Name, Description) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, category.getName());
            // Assuming datecreation is a timestamp
            preparedStatement.setString(3, category.getDescription());
            preparedStatement.setString(1, String.valueOf(category.getId_category()));

            preparedStatement.executeUpdate();

            // Retrieve the generated id and set it in the Category object
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.set(Integer.parseInt(generatedKeys.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void updateCategory(Category category) {
        String query = "UPDATE category SET Name=?, Description=?, Created_Date=? WHERE Id_category=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, category.getName());
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Assuming datecreation is a timestamp
            preparedStatement.setString(3, category.getDescription());
            preparedStatement.setString(1, String.valueOf(category.getId_category()));
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

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category");
        while (resultSet.next()) {
            int Id_category = resultSet.getInt("Id_category");
            String Name = resultSet.getString("Name");
            String Description = resultSet.getString("Description");
            Date Created_Date = resultSet.getDate("Created_Date");
            Category category = new Category(Id_category, Name, Description, Created_Date);
            categories.add(category);
        }
        return categories;
    }

    public static Category getCategorieById(int id) throws SQLException {
        Category category = null;
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE Id_category = " + id);
        if (resultSet.next()) {
            String Name = resultSet.getString("Name");
            String Description = resultSet.getString("Description");
            Date Created_Date = resultSet.getDate("Created_Date");

            category = new Category(Name, Description, Created_Date);
            category.set(id);
            category.setName(Name);
        }
        return category;
    }


}
