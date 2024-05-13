package dao;
import entite.Utilisateur;

public interface IDaoUtilisateur {
    
    public boolean authentifier();
    public boolean creerCompte();
    public boolean modifierCompte(int id, Utilisateur utilisateur);
    public boolean supprimerCompte();
    public Utilisateur consulterCompte(int id);
    public boolean modifierMotDePasse(String newPass);
    
}
