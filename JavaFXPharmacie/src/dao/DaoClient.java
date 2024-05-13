package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DbConnection;


public class DaoClient {
    static Connection cnn = DbConnection.seConnecter();

    public static void Ajouter(String id , String nom , String email , String adresse){
        try {
            String sql = "insert into client values(?,?,?,?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, nom);
            ps.setString(3, email);
            ps.setString(4, adresse);
            ps.executeUpdate();
        } catch (SQLException
                ex) {
            System.out.println("Erreur lors de l'ajout du client "+ex.getMessage());
        }
    }

    public static void Modifier(String id , String nom , String email , String adresse){
        try {
            String sql = "update client set nom = ? , email = ? , adresse = ? where id = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, email);
            ps.setString(3, adresse);
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du client "+ex.getMessage());
        }
    }

    public static void Archiver(String id){
        try {
            String sql = "delete from client where id = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'archivage du client "+ex.getMessage());
        }
    }

    
}
