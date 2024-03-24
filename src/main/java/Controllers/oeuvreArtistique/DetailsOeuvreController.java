package Controllers.oeuvreArtistique;

import Entités.Category;
import Entités.OeuvreArtistique;
import Services.CategoryService;
import Services.ServiceOeuvreArtistique;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DetailsOeuvreController {
    @FXML
    private TextField titleLabel;

    @FXML
    private TextField priceLabel;

    @FXML
    private ImageView imageView;
    @FXML
    private Label dateCreationOeuvre;
    @FXML
    private TextArea descriptionOeuvre;
    @FXML
    private Label acceptationLabel;
    private OeuvreArtistique oeuvre;
    private final ServiceOeuvreArtistique serviceOeuvreArtistique = new ServiceOeuvreArtistique();
    @FXML
    private Button btwModifier;
    @FXML
    private ComboBox categorieComboBox;
    @FXML
    private ComboBox<String> etatComboBox;
    @FXML
    private void initialize() {
        categorieComboBox.setDisable(true);
        etatComboBox.setDisable(true);
        ObservableList<String> etatList = FXCollections.observableArrayList("En stock", "Vendu");
        etatComboBox.setItems(etatList);
    }

    public void afficherDetails(OeuvreArtistique oeuvre) {
        CategoryService categoryService = new CategoryService();
        this.oeuvre = oeuvre;
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
            for (Category category : categories) {
                categorieComboBox.getItems().add(category.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int idCategorie = oeuvre.getIdCategorie();
        for (Category category : categories) {
            if (category.getId_category() == idCategorie) {
                categorieComboBox.getSelectionModel().select(category.getName());
                break;
            }
        }

        List<Category> finalCategories = categories;
        categorieComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue instanceof String) {
                String categoryName = (String) newValue;
                Category selectedCategory = null;
                for(Category category : finalCategories) {
                    if(category.getName().equals(categoryName)) {
                        selectedCategory = category;
                        break;
                    }
                }
                if(selectedCategory != null) {
                    oeuvre.setIdCategorie(selectedCategory.getId_category());
                }
            }
        });
        titleLabel.setText(oeuvre.getTitre());
        priceLabel.setText(String.valueOf(oeuvre.getPrix()));
        dateCreationOeuvre.setText(String.valueOf(oeuvre.getDateCreation()));
        descriptionOeuvre.setText(oeuvre.getDescription());
        acceptationLabel.setText(String.valueOf(oeuvre.getAcceptation()));
        String etatTexte = (oeuvre.getEtat() == 0) ? "En stock" : "Vendu";
        etatComboBox.setValue(etatTexte);
        BackgroundFill backgroundFill;
        if (oeuvre.getAcceptation() == 0) {
            acceptationLabel.setText("Publication acceptée");
            backgroundFill = new BackgroundFill(Color.GREEN, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
        } else {
            acceptationLabel.setText("Publication en cours de validation");
            Color lightRed = Color.rgb(255, 99, 71, 0.7);
            backgroundFill = new BackgroundFill(lightRed, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
        }
        byte[] imageData = oeuvre.getImage();
        if (imageData != null) {
            Image image = new Image(new ByteArrayInputStream(imageData));
            ImageView fixedSizeImageView = new ImageView(image);
            fixedSizeImageView.setFitWidth(200);
            fixedSizeImageView.setFitHeight(200);
            fixedSizeImageView.setLayoutX(150);
            fixedSizeImageView.setLayoutY(137);
            AnchorPane parentPane = (AnchorPane) imageView.getParent();
            parentPane.getChildren().remove(imageView);
            parentPane.getChildren().add(fixedSizeImageView);
            imageView = fixedSizeImageView;
        }
    }

    public void afficherDetailsClient(OeuvreArtistique oeuvre) {
        CategoryService categoryService = new CategoryService();
        this.oeuvre = oeuvre;
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
            for (Category category : categories) {
                categorieComboBox.getItems().add(category.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int idCategorie = oeuvre.getIdCategorie();
        for (Category category : categories) {
            if (category.getId_category() == idCategorie) {
                categorieComboBox.getSelectionModel().select(category.getName());
                break;
            }
        }

        List<Category> finalCategories = categories;
        categorieComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue instanceof String) {
                String categoryName = (String) newValue;
                Category selectedCategory = null;
                for(Category category : finalCategories) {
                    if(category.getName().equals(categoryName)) {
                        selectedCategory = category;
                        break;
                    }
                }
                if(selectedCategory != null) {
                    oeuvre.setIdCategorie(selectedCategory.getId_category());
                }
            }
        });
        titleLabel.setText(oeuvre.getTitre());
        priceLabel.setText(String.valueOf(oeuvre.getPrix())+" DT");
        dateCreationOeuvre.setText(String.valueOf(oeuvre.getDateCreation()));
        descriptionOeuvre.setText(oeuvre.getDescription());
        acceptationLabel.setText(String.valueOf(oeuvre.getAcceptation()));
        String etatTexte = (oeuvre.getEtat() == 0) ? "En stock" : "Vendu";
        etatComboBox.setValue(etatTexte);
        byte[] imageData = oeuvre.getImage();
        if (imageData != null) {
            Image image = new Image(new ByteArrayInputStream(imageData));
            ImageView fixedSizeImageView = new ImageView(image);
            fixedSizeImageView.setFitWidth(200);
            fixedSizeImageView.setFitHeight(200);
            fixedSizeImageView.setLayoutX(150);
            fixedSizeImageView.setLayoutY(137);
            AnchorPane parentPane = (AnchorPane) imageView.getParent();
            parentPane.getChildren().remove(imageView);
            parentPane.getChildren().add(fixedSizeImageView);
            imageView = fixedSizeImageView;
        }
    }

    private void supprimerOeuvre() {
        if (oeuvre != null) {
            try {
                serviceOeuvreArtistique.supprimerOeuvre(oeuvre);
                retournerALaPagePrecedenteArtiste();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void retournerALaPagePrecedenteArtiste() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retournerALaPagePrecedenteClient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void confirmerSuppression() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette œuvre ?");
        ButtonType buttonTypeOK = new ButtonType("Oui");
        ButtonType buttonTypeCancel = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            supprimerOeuvre();
        } else {
        }
    }

    @FXML
    private void modifierOeuvre() {
        titleLabel.setEditable(true);
        descriptionOeuvre.setEditable(true);
        priceLabel.setEditable(true);
        categorieComboBox.setDisable(false);
        etatComboBox.setDisable(false);
        titleLabel.setStyle("-fx-border-color: gray;");
        descriptionOeuvre.setStyle("-fx-border-color: gray;");
        priceLabel.setStyle("-fx-border-color: gray;");
        etatComboBox.setStyle("-fx-border-color: gray;");
        btwModifier.setText("Enregistrer");
        btwModifier.setOnAction(event -> enregistrerModifications());
    }

    private void enregistrerModifications() {
        String nouveauTitre = titleLabel.getText();
        String nouvelleDescription = descriptionOeuvre.getText();
        float nouveauPrix = Float.parseFloat(priceLabel.getText());
        String nouvelleCategorie = (String) categorieComboBox.getValue();
        oeuvre.setTitre(nouveauTitre);
        oeuvre.setDescription(nouvelleDescription);
        oeuvre.setPrix(nouveauPrix);
        String etatTexte = etatComboBox.getValue();
        int nouvelEtat;
        if (etatTexte.equals("En stock")) {
            nouvelEtat = 0;
        } else {
            nouvelEtat = 1;
        }
        oeuvre.setEtat(nouvelEtat);
        try {
            serviceOeuvreArtistique.modifierOeuvre(oeuvre);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Oeuvre Artistique mise à jour avec succès");
            successAlert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        titleLabel.setEditable(false);
        descriptionOeuvre.setEditable(false);
        priceLabel.setEditable(false);
        categorieComboBox.setDisable(true);
        etatComboBox.setDisable(true);
        titleLabel.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        descriptionOeuvre.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        priceLabel.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        etatComboBox.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        btwModifier.setText("Modifier");
        btwModifier.setOnAction(event -> modifierOeuvre());
    }
}
