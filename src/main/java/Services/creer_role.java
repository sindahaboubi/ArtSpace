package Services;

import Entités.role;
import Entités.utilisateur;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class creer_role {
    static Statement ste;
    static Connection con;
    public static void ajout_user(role role) throws SQLException {
        try {
            ste = con.createStatement();
            String req = "INSERT INTO 'role' VALUES (id,libelle)";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, role.getLibelle());
            ps.executeUpdate();
            System.out.println("role ajoutée");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
