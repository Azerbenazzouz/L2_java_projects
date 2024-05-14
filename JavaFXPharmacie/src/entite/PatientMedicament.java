package entite;

import java.sql.Date;

public class PatientMedicament {
    private int qte;
    private Date date;
    private Medicament med;
    private Patient patient;

    public PatientMedicament() {
    }
    public PatientMedicament(int qte, Date date, Medicament med, Patient patient) throws IllegalArgumentException {
        if(qte<0){
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
        if(date == null){
            throw new IllegalArgumentException("La date ne peut pas être null");
        }
        if(med == null){
            throw new IllegalArgumentException("Le médicament ne peut pas être null");
        }
        if(patient == null){
            throw new IllegalArgumentException("Le patient ne peut pas être null");
        }
        this.qte = qte;
        this.date = date;
        this.med = med;
        this.patient = patient;
    }

    // Getters
    public int getQte() {
        return qte;
    }

    public Date getDate() {
        return date;
    }

    public Medicament getMed() {
        return med;
    }

    public Patient getPatient() {
        return patient;
    }

    // Setters

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMed(Medicament med) {
        this.med = med;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "PatientMedicament{" +
                "qte=" + qte +
                ", date=" + date +
                ", med=" + med +
                ", patient=" + patient +
                '}';
    }
    
}
