package Controllers.Musee;
import Entités.Musee;
import Services.MuseeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailsMusee {

    @FXML
    private Button ConsulterMusee;

    @FXML
    private TextField DateDeb;

    @FXML
    private TextField DateF;

    @FXML
    private TextArea DescriptionMusee;

    @FXML
    private Button ModifierMusee;

    @FXML
    private TextField NomMusee;

    @FXML
    private Button SupprimerMusee;

    @FXML
    private TextField VilleMusee;

    @FXML
    private TextField heureF;

    @FXML
    private TextField heureO;

    @FXML
    void handleAjouterMusee(ActionEvent event) {

    }

    int id;
    public void populateFields(Musee musee) {
        NomMusee.setText(musee.getNom());
        DescriptionMusee.setText(musee.getDescription());
        VilleMusee.setText(musee.getVille());
        DateDeb.setText(musee.getDateDebut());
        DateF.setText(musee.getDateFin());
        heureO.setText(musee.getHeureOuverture());
        heureF.setText(musee.getHeureFermeture());
        id = musee.getId();
    }
    @FXML
    void handleModifierMusee(ActionEvent event) {
        // Create a Musee object with updated information from the UI fields
        Musee updatedMusee = new Musee(
                /* Pass the updated information from the UI fields */
                NomMusee.getText(),
                DescriptionMusee.getText(),
                VilleMusee.getText(),
                DateDeb.getText(),
                DateF.getText(),
                heureO.getText(),
                heureF.getText(),1

                /* You may need to obtain the artist ID from somewhere */
        );

        // Use the MuseeService to update the museum
        MuseeService museeService = new MuseeService();
        museeService.updateMusee(updatedMusee);

        // Optionally, you can display a confirmation message or perform other actions after updating
    }
    @FXML
    void handleSupprimerMusee(ActionEvent event) {
        // You may need to confirm the deletion with the user before proceeding
        // Get the Musee ID from somewhere (e.g., a hidden field in the UI or from the Musee object)

        // Use the MuseeService to delete the museum
        MuseeService museeService = new MuseeService();
        museeService.deleteMusee(id);

        // Optionally, you can display a confirmation message or perform other actions after deletion
    }



}
