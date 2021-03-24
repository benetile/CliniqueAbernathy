package com.frontend.controller;

import com.frontend.beans.PraticienBean;
import com.frontend.feign.PraticienFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PraticenController {

    @Autowired
    private PraticienFeign praticienFeign;

    @GetMapping("/prat/add")
    public String praticienPage(Model model){
        return "praticien/addPrat";
    }

    @PostMapping("/prat/validate")
    public String validatePraticien(@Valid PraticienBean praticienBean, Model model){
        if(praticienFeign.addNewPraticien(praticienBean)!=null){
        }
        return null;
    }
}
