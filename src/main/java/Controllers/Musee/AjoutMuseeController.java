import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Services.MuseeService;
import Entités.Musee;
import javafx.stage.Stage;

import java.io.IOException;

public class AjoutMuseeController {

    @FXML
    private Button AjouterMusee;
    @FXML
    private Button ConsulterMusee;

    @FXML
    private TextField DateDeb;

    @FXML
    private TextField DateF;

    @FXML
    private TextArea DescriptionMusee;

    @FXML
    private TextField NomMusee;

    @FXML
    private TextField VilleMusee;

    @FXML
    private TextField heureF;

    @FXML
    private TextField heureO;

    @FXML
    private void handleAjouterMusee(ActionEvent event) {
        try {
            MuseeService museeService = new MuseeService();
            // Create a Musee object with the data from the UI elements
            Musee musee = new Musee();
            musee.setNom(NomMusee.getText());
            musee.setDescription(DescriptionMusee.getText());
            musee.setVille(VilleMusee.getText());
// Assuming Musee has a method setDateDebutString(String dateDebut)
            musee.setDateDebut(DateDeb.getText());
            musee.setDateFin(DateF.getText());   // Set to null since there's no DatePicker
            musee.setHeureOuverture(heureO.getText());
            musee.setHeureFermeture(heureF.getText());
            musee.setIdArtist(1);  // Change as needed
            museeService.addMusee(musee);

            // Show success alert
            showAlert(Alert.AlertType.INFORMATION, "Success", "Musee ajouté avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            // Show failure alert
            showAlert(Alert.AlertType.ERROR, "Error", "Erreur lors de l'ajout du musee.");
        }
    }
    @FXML
    void handleConsulterMusee(ActionEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/ConsulterMusee.fxml"));
            Parent root = loader.load();

            // Create a new Scene
            Scene scene = new Scene(root);

            // Get the Stage from the current event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new Scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
