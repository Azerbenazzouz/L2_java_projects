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
            String sql = "INSERT INTO `utilisateur` (`nom`, `email`, `password`, `telephone`, `role`) VALUES (?, ?, ?, ?, ?)";
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
    public boolean modifierCompte(int id, Utilisateur utilisateur) {
        try{
            String sql = "SELECT * FROM utilisateur WHERE id = ?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String sql2 = "UPDATE utilisateur SET nom = ?, email = ?, password = ?, telephone = ?, role = ? WHERE id = ?";
                PreparedStatement pst2 = cnn.prepareStatement(sql2);
                pst2.setString(1, utilisateur.getNom());
                pst2.setString(2, utilisateur.getEmail());
                pst2.setString(3, utilisateur.getMotDePasse());
                pst2.setInt(4, utilisateur.getTelephone());
                pst2.setString(5, utilisateur.getRole());
                pst2.setInt(6, id);
                int result = pst2.executeUpdate();
                if(result > 0){
                    return true;
                }
                pst2.close();
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Erreur de modification de compte ..."+ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean supprimerCompte() {
        try{
            String sql = "DELETE FROM utilisateur WHERE id = ?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, utilisateur.getId());
            int result = pst.executeUpdate();
            if(result > 0){
                return true;
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Erreur de suppression de compte ..."+ex.getMessage());
        }
        return false;
    }

    @Override
    public Utilisateur consulterCompte(int id) {
        Utilisateur utilisateur = new Utilisateur();
        try{
            String sql = "SELECT * FROM utilisateur WHERE id = ?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setMotDePasse(rs.getString("password"));
                utilisateur.setTelephone(rs.getInt("telephone"));
                utilisateur.setRole(rs.getString("role"));
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Erreur de consultation de compte ..."+ex.getMessage());
        }
        return utilisateur;
    }

    @Override
    public boolean modifierMotDePasse(String newPass) {
        if( utilisateur.getEmail().equals("") || utilisateur.getMotDePasse().equals("") ){
            return false;
        }
        try{
            String sql = "SELECT * FROM utilisateur WHERE id = ?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, utilisateur.getId());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String sql2 = "UPDATE utilisateur SET password = ? WHERE id = ?";
                PreparedStatement pst2 = cnn.prepareStatement(sql2);
                pst2.setString(1, utilisateur.hashMotDePasse(newPass));
                pst2.setInt(2, utilisateur.getId());
                int result = pst2.executeUpdate();
                if(result > 0){
                    utilisateur.setMotDePasse(newPass);
                    return true;
                }
                pst2.close();
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Erreur de modification de mot de passe ..."+ex.getMessage());
        }
        return false;
    }

}
