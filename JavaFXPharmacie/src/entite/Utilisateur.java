package entite;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Utilisateur {
    private int id;
    private StringProperty nom;
    private StringProperty email;
    private StringProperty motDePasse;
    private IntegerProperty telephone;
    private StringProperty role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String email, String motDePasse, int telephone, String role) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.email = new SimpleStringProperty(email);
        this.motDePasse = new SimpleStringProperty(hashMotDePasse(motDePasse));
        this.telephone = new SimpleIntegerProperty(telephone);
        this.role = new SimpleStringProperty(role);
    }

    public Utilisateur(String nom, String email, String motDePasse, int telephone, String role) {
        this.nom = new SimpleStringProperty(nom);
        this.email = new SimpleStringProperty(email);
        this.motDePasse = new SimpleStringProperty(hashMotDePasse(motDePasse));
        this.telephone = new SimpleIntegerProperty(telephone);
        this.role = new SimpleStringProperty(role);
    }

    public Utilisateur(String email, String motDePasse) {
        this.email = new SimpleStringProperty(email);
        this.motDePasse = new SimpleStringProperty(hashMotDePasse(motDePasse));
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getMotDePasse() {
        return motDePasse.get();
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = new SimpleStringProperty(hashMotDePasse(motDePasse));
    }

    public int getTelephone() {
        return telephone.get();
    }

    public void setTelephone(int telephone) {
        this.telephone = new SimpleIntegerProperty(telephone);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role = new SimpleStringProperty(role);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id.get() +
                ", nom='" + nom.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", motDePasse='" + motDePasse.get() + '\'' +
                ", telephone=" + telephone.get() +
                '}';
    }

    public String hashMotDePasse(String motDePasse){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(motDePasse.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();

            for(byte b : digest){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }catch(NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
}
