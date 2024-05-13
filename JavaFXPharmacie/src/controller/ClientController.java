package controller;

import entite.Client;
import dao.DaoClient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Node;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import utils.DbConnection;

public class ClientController {
    @FXML
    TableView<Client> tv = new TableView<>();
    @FXML
    TextField Id;
    @FXML
    TextField Nom;
    @FXML
    TextField Adresse;
    @FXML
    TextField Email;
    
    @FXML
    TableColumn<Client, String> colId;
    @FXML
    TableColumn<Client, String> colNom;
    @FXML
    TableColumn<Client, String> colAdresse;
    @FXML
    TableColumn<Client, String> colEmail;

    static Connection cd= DbConnection.seConnecter();
    ObservableList<Client> observableList;

    @FXML
    private void Ajouter() {
        DaoClient.Ajouter(Id.getText(), Nom.getText(), Email.getText(), Adresse.getText());
        lister();
        remiseAzéro();
    }

    @FXML
    private void Modifier() {
        DaoClient.Modifier(Id.getText(), Nom.getText(), Email.getText(), Adresse.getText());
        lister();
        remiseAzéro();
    }
    
    @FXML
    private void Archiver() {
        DaoClient.Archiver(Id.getText());
        lister();
        remiseAzéro();
    }

    @FXML
    private void Consulter() {
        String cin = this.tv.getSelectionModel().getSelectedItem().getCodeCli();
        String nom = this.tv.getSelectionModel().getSelectedItem().getNomCli();
        String adresse = this.tv.getSelectionModel().getSelectedItem().getAdrCli();
        String email = this.tv.getSelectionModel().getSelectedItem().getEmailCli();
        Id.setText(cin);
        Nom.setText(nom);
        Adresse.setText(adresse);
        Email.setText(email);
    }
    
    @FXML
    private void GestionFacture(ActionEvent event) throws Exception {
      ((Node) event.getSource()).getScene().getWindow().hide();
         Stage st2= new Stage();
         Parent root=FXMLLoader.load(getClass().getResource("/View/FXMLFacture.fxml"));
         
         Scene se=new Scene(root);
         st2.setScene(se);
         st2.setTitle("Gestion Facture");
         st2.show();
    }

    private void remiseAzéro() {
        Id.clear();
        Id.requestFocus();
        Nom.clear();
        Adresse.clear();
        Email.clear();
    }

    public void lister() {
       // Connection cd = cn.seConnecter();
         tv.getItems().clear();
        try {
            ResultSet rs = cd.createStatement().executeQuery("select * from client");
            while (rs.next()) {
                observableList.add(new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       
        tv.setItems(observableList);
    }
}
