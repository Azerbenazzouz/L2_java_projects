/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpharmacie;

import dao.DaoClient;
import entite.Client;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DbConnection;

/**
 *
 * @author Lenovo
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    
    @FXML
    TableView<Client> tv = new TableView<Client>();
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
        System.out.println("Ajouter");
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
        System.out.println("hello");
        try {
            ResultSet rs = cd.createStatement().executeQuery("SELECT * FROM `client`");
            while (rs.next()) {
                observableList.add(new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       
        tv.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ............
        this.lister();
    }  
}
