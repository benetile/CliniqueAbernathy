package com.frontend.controller;

import com.frontend.beans.PatientBean;
import com.frontend.feign.PatientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientFeign patientFeign;

    @GetMapping("/pat/add")
    public String homePage(Model model){
        return "pat/addPat";
    }

    @PostMapping("/pat/validate")
    public String validatePatient(@Valid PatientBean patientBean,Model model){
        if(patientFeign.validatePatient(patientBean)!=null){
            model.addAttribute("patient",patientFeign.showAllPatients());
            return "redirect:/pat/patients";
        }
        return "pat/addPat";
    }

    @GetMapping("pat/patients")
    public String showAllPatients(Model model){
        List<PatientBean> patients = patientFeign.showAllPatients();
        model.addAttribute("patients",patients);
        return "pat/patients";
    }

    @GetMapping("/pat/update/{id}")
    public String updatePage(@PathVariable("id") Integer id, Model model){
        PatientBean bean = patientFeign.getPatientById(id);
        model.addAttribute("patientBean",bean);
        return "pat/update";
    }

    @PostMapping("/pat/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id,@Valid PatientBean patientBean ,Model model){
        if(patientFeign.updateInfoPatient(id,patientBean)!=null){
            model.addAttribute("patients",patientFeign.showAllPatients());
            return "redirect:/pat/patients";
        }
        return "pat/update";
    }

    @GetMapping("pat/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model){
        PatientBean patientBean = patientFeign.getPatientById(id);
        patientFeign.deletePatient(id);
        model.addAttribute("patients",patientFeign.showAllPatients());
        return "redirect:/pat/patients";
    }
}
