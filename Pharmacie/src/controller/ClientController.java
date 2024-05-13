package controller;

import entite.Utilisateur;
import dao.DaoUtilisateur;
import utils.DbConnection;

public class ClientController {
    @FXML
    TableView<Utilisateur> tv = new TableView<>();
    @FXML
    Integer Id;
    @FXML
    TextField Nom;
    @FXML
    TextField Email;
    
    @FXML
    TableColumn<Utilisateur, String> colId;
    @FXML
    TableColumn<Utilisateur, String> colNom;
    @FXML
    TableColumn<Utilisateur, String> colEmail;

    static Connection cd= DbConnection.seConnecter();

    @FXML
    private void Ajouter() {
        DaoUtilisateur.Ajouter(Id.getText(), Nom.getText(), Email.getText());
        lister();
        remiseAzéro();
    }
}
