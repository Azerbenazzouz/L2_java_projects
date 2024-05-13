package dao;
import entite.Utilisateur;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtilisateur implements IDaoUtilisateur{
    Utilisateur utilisateur = new Utilisateur();
    Connection cnn = DbConnection.seConnecter();

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    @Override
    public boolean authentifier() {
        if( utilisateur.getEmail().equals("") || utilisateur.getMotDePasse().equals("") ){
            return false;
        }
        try{
            String sql = "SELECT * FROM utilisateur WHERE email = ? AND password = ?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, utilisateur.getEmail());
            pst.setString(2, utilisateur.getMotDePasse());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Erreur d'authentification ..."+ex.getMessage());
        }
        return false;
    }
    
    @Override
    public boolean creerCompte() {
        if( utilisateur.getEmail().equals("") || utilisateur.getMotDePasse().equals("") ){
            return false;
        }
        try{
            String sql = "INSERT INTO `utilisateur` (`nom`, `email`, `password`, `telephone`, `role`) VALUES ('?', '?', '?', '?', '?')";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getEmail());
            pst.setString(3, utilisateur.getMotDePasse());
            pst.setInt(4, utilisateur.getTelephone());
            pst.setString(5, utilisateur.getRole());
            int result = pst.executeUpdate();
            if(result > 0){
                return true;
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Erreur de creation de compte ..."+ex.getMessage());
        }
        return false;
    }

    @Override
    public void modifierCompte() {
        // Implement the method here
    }

    @Override
    public boolean supprimerCompte() {
        return false;
        // Implement the method here
    }

    @Override
    public Utilisateur consulterCompte() {
        return new Utilisateur();
        // Implement the method here
    }

    @Override
    public boolean modifierMotDePasse() {
        return false;
        // Implement the method here
    }

}
