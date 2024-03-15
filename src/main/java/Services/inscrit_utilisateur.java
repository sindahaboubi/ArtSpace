package Services;

import Entités.utilisateur;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class inscrit_utilisateur {
    static Statement ste;
    static Connection con;
    public static void ajout_user(utilisateur user) throws SQLException {
        try {
            ste = con.createStatement();
            String req = "INSERT INTO 'utilisateur' VALUES (nom,prenom,date_naiss,tel,email,adresse,id_role)";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setDate(3, user.getDate_naiss());
            ps.setString(4, user.getTel());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAdresse());
            ps.setString(5, String.valueOf(user.getId_role()));
            ps.executeUpdate();
            System.out.println("personne ajoutée");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
