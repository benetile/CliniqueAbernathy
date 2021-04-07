package com.clinique.service.impl;

import com.clinique.beans.PatientBean;
import com.clinique.beans.RecommandationBean;
import com.clinique.model.Assessment;
import com.clinique.proxy.MPatientProxy;
import com.clinique.proxy.MRecommandationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class GenerateAssessService {

    @Autowired
    MPatientProxy mPatientProxy;

    @Autowired
    MRecommandationProxy mRecommandationProxy;

    private static  final List<String> triggers = Arrays.asList("Hémoglobine A1C","hémoglobine A1C","Microalbumine","microalbumine","Poids","poids","fumeur","Fumeur","Anormal","anormal","Cholestérol","cholestérol","Vertige","vertige",
            "Rechute","rechute","réaction", "Réaction","Anticorps","anticorps","Taille","taille");


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
        else if((age<30)){
            assessment.setAssessment(determinatedAssessThan(number,sex));
            return assessment.getAssessment();
        }
        return "None";
    }
    public List<Assessment> getAssessments(){
        List<Assessment> assessments= new ArrayList<>();
        Assessment assess = new Assessment();
        List<PatientBean> patientBeans = mPatientProxy.showAllPatients();
        //Method Multithread
        patientBeans.parallelStream().forEach(patientBean ->{
            Assessment assessment = new Assessment();
            assessment.setIdPatient(patientBean.getId());
            assessment.setFirstName(patientBean.getFirstName());
            assessment.setAge(new Date().getYear() - patientBean.getBirthday().getYear());
            assessment.setAssessment(generateAssessment(patientBean.getId(), assessment.getAge(), patientBean.getSex()));
            assessments.add(assessment);
        });
        return assessments;
    }

    public int verifyTriggers(int id){
        List<RecommandationBean> recommandationBean = mRecommandationProxy.getAllRecommandationPatient(id);
        System.out.println(recommandationBean.size());
        int count = 0;

        for (RecommandationBean bean : recommandationBean){
            for (TriggersService read : TriggersService.values()){
                if((bean.getObservation().contains(read.getTriggerEnMaj())|| bean.getObservation().contains(read.getTriggerEnmin()))
                || bean.getObservation().contains(read.getTriggerFrMaj()) || bean.getObservation().contains(read.getTriggerFrMin())){
                    System.out.println(read);
                    count+=1;
                }
            }
        }
        System.out.println(count);
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
        else if(number<2){
            return "None";
        }
        return "Bordeline";
    }

    public String determinatedForEarlyOnset(int number, int id,String sex){
        if((sex.contains("M")) && number > 4){
            return "Early onset";
        }
        else if((sex.contains("F")) && number == 7){
            return "Early onset";
        }
        return "In Danger";
    }

    public String determinatedAssessThan(int number, String sex){

        if((sex.contains("M")) && (number == 3)){
            return "In Danger";
        }
        else if((sex.contains("M")) && number >= 5){
            return "Early onset";
        }
        else if((sex.contains("F")) && number== 4){
            return "In Danger";
        }
        else if((sex.contains("F")) && number >= 7){
            return "Early onset";
        }
        else if(number<2){
            return "None";
        }

        return "Bordeline";
    }

}
