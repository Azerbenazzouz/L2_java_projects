package controller;
import java.sql.Connection;

import dao.DaoMedicament;
import entite.Medicament;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.Node;
//import javafx.scene.control.TableView;

public class MedicamentController {
    static Connection cd= DbConnection.seConnecter();
    private DaoMedicament daoMedicament = new DaoMedicament();
    
    @FXML
    TableView<Medicament> tv = new TableView<Medicament>();
    
    @FXML
    TextField Id;
    @FXML
    TextField Nom;
    @FXML
    TextField Prix;
    @FXML
    TextField Stock;
    @FXML
    TextField Type;


    @FXML
    TableColumn<Medicament, String> colId;
    @FXML
    TableColumn<Medicament, String> colNom;
    @FXML
    TableColumn<Medicament, String> colPrix;
    @FXML
    TableColumn<Medicament, String> colStock;
    @FXML
    TableColumn<Medicament, String> colType;
    ObservableList<Medicament> observableList;

    

    @FXML
    private void Ajouter() {
        Medicament medicament = new Medicament();
        medicament.setCodeMed(Integer.parseInt(Id.getText()));
        medicament.setNomMed(Nom.getText());
        medicament.setPrixMed(Float.parseFloat(Prix.getText()));
        medicament.setQte(Integer.parseInt(Stock.getText()));
        medicament.setTypeMed(Type.getText());
        if(daoMedicament.add(medicament)){
            System.out.println("Ajout avec succès");
        }else{
            System.out.println("Echec d'ajout");
        }
        lister();
        remiseAzéro();
    }

    @FXML
    private void Modifier() {
        Medicament medicament = new Medicament();
        medicament.setCodeMed(Integer.parseInt(Id.getText()));
        medicament.setNomMed(Nom.getText());
        medicament.setPrixMed(Float.parseFloat(Prix.getText()));
        medicament.setQte(Integer.parseInt(Stock.getText()));
        medicament.setTypeMed(Type.getText());
        if(daoMedicament.update(medicament)){
            System.out.println("Modification avec succès");
        }else{
            System.out.println("Echec de modification");
        }
        lister();
        remiseAzéro();
    }

    @FXML
    private void Archiver() {
       if(daoMedicament.delete(Integer.parseInt(Id.getText()))){
            System.out.println("Archivage avec succès");
        }else{
            System.out.println("Echec d'archivage");
        }
        lister();
        remiseAzéro();
    }
    @FXML
    private void Clear(){
        lister();
        remiseAzéro();
    }

    private void lister() {
        tv.getItems().clear();

        observableList.addAll(daoMedicament.findAll());
        tv.setItems(observableList);
    }
    
    @FXML
    private void Consulter() {
        int id = this.tv.getSelectionModel().getSelectedItem().getCodeMed();
        String nom = this.tv.getSelectionModel().getSelectedItem().getNomMed();
        float prix = this.tv.getSelectionModel().getSelectedItem().getPrixMed();
        int stock = this.tv.getSelectionModel().getSelectedItem().getQte();
        String type = this.tv.getSelectionModel().getSelectedItem().getTypeMed();
        Id.setText(id+"");
        Nom.setText(nom);
        Prix.setText(prix+"");
        Stock.setText(stock+"");
        Type.setText(type);
    }

    private void remiseAzéro() {
        Id.setText("");
        Nom.setText("");
        Prix.setText("");
        Stock.setText("");
        Type.setText("");
    }


}
