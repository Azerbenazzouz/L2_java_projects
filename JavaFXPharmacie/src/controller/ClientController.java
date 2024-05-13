package controller;

import entite.Client;
import dao.DaoClient;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import utils.DbConnection;

public class ClientController {
    @FXML
    TableView<Client> tv = new TableView<>();
    @FXML
    TextField Id;
    @FXML
    TextField Nom;
    @FXML
    TextField Email;
    
    @FXML
    TableColumn<Client, String> colId;
    @FXML
    TableColumn<Client, String> colNom;
    @FXML
    TableColumn<Client, String> colEmail;

    static Connection cd= DbConnection.seConnecter();

    @FXML
    private void Ajouter() {
        DaoClient.Ajouter(Id.getText(), Nom.getText(), Email.getText());
        lister();
        remiseAzéro();
    }
}
