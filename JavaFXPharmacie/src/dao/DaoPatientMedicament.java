package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entite.PatientMedicament;
import utils.DbConnection;

/*  
     private int qte;
    private Date date;
    private Medicament med;
    private Patient patient;
 */
public class DaoPatientMedicament implements IDao<PatientMedicament>{
    private Connection connection = DbConnection.seConnecter();
    private DaoPatient daoPatient = new DaoPatient();
    private DaoMedicament daoMedicament = new DaoMedicament();

    @Override
    public boolean add(PatientMedicament PM) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO patient_medicament(qte, date, codePatient, codeMedicament) VALUES(?, ?, ?, ?)");
            preparedStatement.setInt(1, PM.getQte());
            preparedStatement.setDate(2, PM.getDate());
            preparedStatement.setInt(3, PM.getPatient().getCode());
            preparedStatement.setInt(4, PM.getMed().getCodeMed());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                PM.setQte(resultSet.getInt(1));
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(PatientMedicament t) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE patient_medicament SET qte = ? WHERE DATE = ? AND codePatient = ? AND codeMedicament = ?");
            preparedStatement.setInt(1, t.getQte());
            preparedStatement.setDate(2, t.getDate());
            preparedStatement.setInt(3, t.getPatient().getCode());
            preparedStatement.setInt(4, t.getMed().getCodeMed());
            preparedStatement.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean delete(int id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM patient_medicament WHERE codePatient = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public PatientMedicament findById(int id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patient_medicament WHERE codePatient = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if(resultSet.next()){
                PatientMedicament PM = new PatientMedicament();
                PM.setQte(resultSet.getInt("qte"));
                PM.setDate(resultSet.getDate("date"));
                PM.setPatient(daoPatient.findById(resultSet.getInt("codePatient")));
                PM.setMed(daoMedicament.findById(resultSet.getInt("codeMedicament")));
                return PM;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<PatientMedicament> findAll() {
        ArrayList<PatientMedicament> listPM = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patient_medicament");
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                PatientMedicament PM = new PatientMedicament();
                PM.setQte(resultSet.getInt("qte"));
                PM.setDate(resultSet.getDate("date"));
                PM.setPatient(daoPatient.findById(resultSet.getInt("codePatient")));
                PM.setMed(daoMedicament.findById(resultSet.getInt("codeMedicament")));
                listPM.add(PM);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listPM;
    }

}
