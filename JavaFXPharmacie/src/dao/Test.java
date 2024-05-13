package dao;
import entite.Utilisateur;
public class Test {
    public static void main(String[] args) {
        Utilisateur utilisateur = new Utilisateur(2,"John Doe", "admin@gmail.com", "admin", 123456789, "ROLE_ADMIN");
        DaoUtilisateur daoUtilisateur = new DaoUtilisateur();
        daoUtilisateur.setUtilisateur(utilisateur);

        // if(daoUtilisateur.creerCompte()){
        //     System.out.println("Compte cree avec succes");
        // }else{
        //     System.out.println("Erreur lors de la creation du compte");
        // }

        // if(daoUtilisateur.authentifier()){
        //     System.out.println("Authentification reussie");
        // }else{
        //     System.out.println("Authentification echouee");
        // }
        // utilisateur.setNom("Azer Ben Azzouz");
        // if(daoUtilisateur.modifierCompte(1, utilisateur)){
        //     System.out.println("Compte modifie avec succes");
        // }else{
        //     System.out.println("Erreur lors de la modification du compte");
        // }
        // if(daoUtilisateur.supprimerCompte()){
        //     System.out.println("Compte supprime avec succes");
        // }else{
        //     System.out.println("Erreur lors de la suppression du compte");
        // }
        // Utilisateur user = daoUtilisateur.consulterCompte(2);
        // System.out.println(user.toString());
        // if(daoUtilisateur.modifierMotDePasse("azerazer")){
        //     System.out.println("Mot de passe modifie avec succes");
        // }else{
        //     System.out.println("Erreur lors de la modification du mot de passe");
        // }
        System.out.println("Hello, World!");
    }    
}
