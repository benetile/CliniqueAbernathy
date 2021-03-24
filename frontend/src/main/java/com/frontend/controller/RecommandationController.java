package com.frontend.controller;

import com.frontend.beans.AssessBean;
import com.frontend.beans.PatientBean;
import com.frontend.beans.RecommandationBean;
import com.frontend.feign.AssessFeign;
import com.frontend.feign.PatientFeign;
import com.frontend.feign.RecommandationFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class RecommandationController {

    @Autowired
    private RecommandationFeign recommandationFeign;

    @Autowired
    private PatientFeign patientFeign;

    @Autowired
    private AssessFeign assessFeign;

    @GetMapping("rec/pat/{id}")
    public String getPatientRecommandation(@PathVariable("id") Integer id, Model model){
        PatientBean patientBean = patientFeign.getPatientById(id);
        AssessBean assess = assessFeign.generateAssessment(id);
        long age = new Date().getYear() - patientBean.getBirthday().getYear();
        List<RecommandationBean> bean = recommandationFeign.getAllRecommandationPatient(id);
        model.addAttribute("assess",assess.getAssessment());
        model.addAttribute("age",age);
        model.addAttribute("patientBean",patientBean);
        model.addAttribute("recommandations",bean);
        return "recommandation/listRec";
    }

    @GetMapping("rec/add/{id}")
    public String addObservation(@PathVariable("id") Integer id, Model model){
        RecommandationBean recommandation = new RecommandationBean();
        recommandation.setIdPatient(id);
        model.addAttribute("recommandation", recommandation);
        return "recommandation/addRec";
    }

    @PostMapping("rec/validate/{id}")
    public String validateObservation(@PathVariable("id") Integer id,@Valid RecommandationBean recommandation,Model model){
        System.out.println(recommandation.getObservation());
        if(recommandation.getObservation()!=null){
       // if(recommandationFeign.addNewRecommandation(recommandation,id)!=null){
            recommandationFeign.addNewRecommandation(recommandation.getObservation(),id);
            model.addAttribute("recommandations",recommandationFeign.getAllRecommandationPatient(id));
            return"redirect:/pat/patients";

        }
        return "recommandation/addRec";
    }

    @GetMapping("rec/update/{id}")
    public String updateRecommandation(@PathVariable("id") Integer id,Model model){
        RecommandationBean bean = recommandationFeign.getRecommandationById(id);
        model.addAttribute("recommandation",bean);
        return "recommandation/update";
    }

    @PostMapping("rec/update/{id}")
    public String validateUpdate(@PathVariable("id") Integer id, @Valid RecommandationBean recommandationBean,
                                                                Model model){
        if(recommandationFeign.updateAObservation(id,recommandationBean)!=null){
            model.addAttribute("recommandations",recommandationFeign.getAllRecommandationPatient(id));
            return"redirect:/pat/patients";
        }
        return "recommandation/update";
    }
    @GetMapping("rec/delete/{id}")
    public String deleteObservation(@PathVariable("id") Integer id,Model model){
        recommandationFeign.deleteRecommandation(id);
        model.addAttribute("recommandations",recommandationFeign.getAllRecommandationPatient(id));
        return "redirect:/pat/patients";
    }
}
