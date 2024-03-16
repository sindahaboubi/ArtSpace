package Controllers.Musee;

import Entités.Musee;
import Services.MuseeService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.List;

public class ConsulterMusee {

    @FXML
    private TableView<Musee> TableMusee;

    @FXML
    private TableColumn<Musee, String> nomColumn;

    @FXML
    private TableColumn<Musee, String> descriptionColumn;

    @FXML
    private TableColumn<Musee, String> villeColumn;

    @FXML
    private TableColumn<Musee, String> dateDebutColumn;

    @FXML
    private TableColumn<Musee, String> dateFinColumn;

    @FXML
    private TableColumn<Musee, String> heureOuvertureColumn;

    @FXML
    private TableColumn<Musee, String> heureFermetureColumn;

    private MuseeService museeService;

    @FXML
    private void initialize() {
        museeService = new MuseeService();

        // Initialize columns
        nomColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getNom()));
        descriptionColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
        villeColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getVille()));
        dateDebutColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDateDebut()));
        dateFinColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDateFin()));
        heureOuvertureColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getHeureOuverture()));
        heureFermetureColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getHeureFermeture()));


        // Load data from the service
        List<Musee> musees = museeService.getAllMusees();

        // Populate TableView
        TableMusee.getItems().addAll(musees);
    }
    private void setHeaderColor(TableColumn<?, ?> column) {
        // Set the background color of the column header
        column.setStyle("-fx-background-color: #ACE2E1;");
    }
    @FXML
    void handleAjouterMusee(ActionEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/AjoutMusee.fxml"));
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
    @FXML
    void handleTableViewClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            // Double-click detected
            Musee selectedMusee = TableMusee.getSelectionModel().getSelectedItem();
            if (selectedMusee != null) {
                // Redirect to DetailsMusee page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/DetailsMusee.fxml"));
                Parent root = loader.load();
                DetailsMusee controller = loader.getController();

                // Populate fields with selected museum's information
                controller.populateFields(selectedMusee);

                // Navigate to DetailsMusee page
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }


}
