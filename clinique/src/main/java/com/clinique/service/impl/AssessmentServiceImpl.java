package com.clinique.service.impl;

import com.clinique.beans.PatientBean;
import com.clinique.beans.RecommandationBean;
import com.clinique.config.ConfigBD;
import com.clinique.model.Assessment;
import com.clinique.proxy.MPatientProxy;
import com.clinique.proxy.MRecommandationProxy;
import com.clinique.service.dao.AssessmentRepository;
import com.clinique.service.dao.AssessmentServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.Collator;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class AssessmentServiceImpl implements AssessmentServiceDao {

    @Autowired
    MPatientProxy mPatientProxy;

    @Autowired
    MRecommandationProxy mRecommandationProxy;

    @Autowired
    private ConfigBD configBD;

    @Autowired
    AssessmentRepository repository;

    Locale french = new Locale("fr","FR");

    private Connection connection;

    private static  final List<String> triggers = Arrays.asList("Hémoglobine A1C","hémoglobine A1C","Microalbumine","microalbumine","Poids","poids","fumeur","Fumeur","Anormal","anormal","Cholestérol","cholestérol","Vertige","vertige",
            "Rechute","rechute","réaction", "Réaction","Anticorps","anticorps","Taille","taille");

    Collator fr = Collator.getInstance(new Locale("fr"));
    @Override
    public void saveAssessment(Assessment input) throws SQLException {
        //save in mongoDb
        repository.save(input);
        //save in Sql
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO assessment(id,idPatient,firstName,age,assessment) values (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1,input.getId());
        statement.setInt(2,input.getIdPatient());
        statement.setString(3,input.getFirstName());
        statement.setInt(4,input.getAge());
        statement.setString(5,input.getAssessment());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            input.setId(rs.getInt(1));
        }
        statement.close();
    }

    @Override
    public void updateAssessment(int id,Assessment update) throws SQLException {
        //update in mongoDb
        repository.save(update);
        //update in Sql
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE assessment SET firstName=?, age=?, assessment=? WHERE idPatient=?;");

        statement.setString(1,update.getFirstName());
        statement.setInt(2,update.getAge());
        statement.setString(3, update.getAssessment());
        statement.setInt(4,id);

        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void deleteAssessment(int id) throws SQLException {
        //delete in mongo
        repository.deleteById(id);
        //delete in Sql
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM assessment WHERE idPatient=?;");
        statement.setInt(1,id);

        statement.executeUpdate();
        statement.close();
    }

    @Override
    public List<Assessment> findAssessments() throws SQLException {
        return repository.findAll();
    }

    @Override
    public Assessment findAssessmentById(int id) throws SQLException {
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM assessment WHERE idPatient=?;");
        statement.setInt(1,id);

        ResultSet rs = statement.executeQuery();
        Assessment assessment = new Assessment();
        while (rs.next()){
            assessment.setIdPatient(rs.getInt("idPatient"));
            assessment.setFirstName(rs.getString("firstName"));
            assessment.setAge(rs.getInt("age"));
            assessment.setAssessment(rs.getString("assessment"));
        }
        return assessment;
    }

    @Override
    public Assessment findAssessmentByFirstName(String firstName) throws SQLException {
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM assessment WHERE firstName=?;");
        statement.setString(1,firstName);

        ResultSet rs = statement.executeQuery();
        Assessment assessment = new Assessment();
        while (rs.next()){
            assessment.setIdPatient(rs.getInt("idPatient"));
            assessment.setFirstName(rs.getString("firstName"));
            assessment.setAge(rs.getInt("age"));
            assessment.setAssessment(rs.getString("assessment"));
        }
        return assessment;
    }

    @Override
    public String generateAssessment(int id,long age,String sex) {
        Assessment assessment = new Assessment();

        int number = verifyTriggers(id);
        if(age>30 && number==2){
            return "Bordeline";
        }
        else if((age>30) && (number>2 && number<=7)){
            return "In Danger";
        }
        else if( age>30 && number > 7){
            return "Early onset";
        }
        else if((age<30) &&(sex.contains("M") && number==6)){
           return "In Danger";
        }
        else if(age<30 && number<5){
            assessment.setAssessment(determinatedForInDanger(number,id,sex));
            return assessment.getAssessment();
        }
        else if((age<30) && (number>=5 && number<=7)){
            assessment.setAssessment(determinatedForEarlyOnset(number,id,sex));
            return assessment.getAssessment();
        }
        else if (number<2){
            assessment.setAssessment("None");
            return assessment.getAssessment();
        }
        return "None";
    }

    public int verifyTriggers(int id){
        List<RecommandationBean> recommandationBean = mRecommandationProxy.getAllRecommandationPatient(id);
        int count = 0;
        for (RecommandationBean bean : recommandationBean){
            for (String trigger : triggers){
                if(bean.getObservation().contains(trigger)){
                    System.out.println(trigger);
                   count+=1;
                }
            }
        }
        return count;
    }

    public String determinatedForInDanger(int number, int id,String sex){
        PatientBean patientBean = mPatientProxy.getPatientById(id);

        if((sex.contains("M")) && number == 3){
            return "In Danger";
        }
        else if((sex.contains("F")) && number== 4){
            return "In Danger";
        }
        return "Bordeline";
    }

    public String determinatedForEarlyOnset(int number, int id,String sex){
        if((sex.contains("M")) && number == 5){
            return "Early onset";
        }
        else if((sex.contains("F")) && number == 7){
            return "Early onset";
        }
        return "In Danger";
    }

}
