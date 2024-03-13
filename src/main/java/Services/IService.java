package Services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    void ajouterOeuvre(T t) throws SQLException;
    List<T> listerOeuvres() throws SQLException;
    T OeuvreById(int id) throws SQLException;
    void supprimerOeuvre(T t) throws SQLException;
    void modifierOeuvre(T t) throws SQLException;
}
