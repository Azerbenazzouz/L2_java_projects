package entite;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private StringProperty codeCli;
    private StringProperty nomCli;
    private StringProperty adrCli;
    private StringProperty emailCli; 

    public Client(String codeCli, String nomCli, String adrCli, String emailCli) {
        this.codeCli = new SimpleStringProperty(codeCli);
        this.nomCli = new SimpleStringProperty(nomCli);
        this.adrCli = new SimpleStringProperty(adrCli);
        this.emailCli = new SimpleStringProperty(emailCli);
    }

    public String getCodeCli(){
        return this.codeCli.get();
    }

    public String getNomCli(){
        return this.nomCli.get();
    }

    public String getAdrCli(){
        return this.adrCli.get();
    }

    public String getEmailCli(){
        return this.emailCli.get();
    }

    public void setCodeCli(String c){
        this.codeCli.set(c);
    }

    public void setNomCli(String nc) {
        this.nomCli.set(nc);
    }

    public void setAdrCli(String adr) {
        this.adrCli.set(adr);
    }

    public void setEmailCli(String ec) {
        this.emailCli.set(ec);
    }

    @Override
    public String toString() {
        return this.codeCli.get()+" " +this.nomCli.get()+" "+this.adrCli.get()+" "+this.emailCli.get();
    }
}
