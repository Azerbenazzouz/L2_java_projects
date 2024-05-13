package modele;
import entite.Utilisateur;
import dao.DaoUtilisateur;

public class ModeleUtilisateur {
    private Utilisateur utilisateur;

    public Utilisateur getU() {
		return utilisateur;
	}

	public void setU(Utilisateur u) {
		this.utilisateur = u;
	}

    public boolean login() {
		DaoUtilisateur dao = new DaoUtilisateur();
        dao.setUtilisateur(utilisateur);
        return dao.authentifier();
	}

}
