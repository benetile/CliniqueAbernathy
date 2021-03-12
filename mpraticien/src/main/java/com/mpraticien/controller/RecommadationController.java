package com.mpraticien.controller;

import com.mpraticien.beans.PatientBean;
import com.mpraticien.model.Recommandation;
import com.mpraticien.proxy.MPatientProxy;
import com.mpraticien.service.dao.RecommandationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class RecommadationController {

    @Autowired
    private MPatientProxy MPatientProxy;

    @Autowired
    RecommandationDao recommandationDao;

    @GetMapping("/recommandation/patients/{id}")
    public List<Recommandation> getAllRecommandationPatient(@PathVariable ("id") int id) throws SQLException{
        PatientBean patientBean = MPatientProxy.getPatientById(id);
        List<Recommandation> recommandations = recommandationDao.findById(patientBean.getId());
        if(recommandations!=null){
            return recommandations;
        }
        else {
            throw new SQLException(+id+ " n'existe pas");
        }
    }

    @PostMapping("/recommandations/add/{id}")
    public void addNewRecommandation(@RequestBody Recommandation inputRecommandation,@PathVariable("id") int id) throws SQLException{
        PatientBean patientBean = MPatientProxy.getPatientById(id);
        if(patientBean!=null){
            inputRecommandation.setIdPatient(patientBean.getId());
            recommandationDao.addRecommandation(inputRecommandation);
        }
        else {
            throw new SQLException("erreur");
        }
    }

    @PutMapping("/recommandations/update/{id}")
    public void updateAObservation(@PathVariable ("id") int id, @RequestBody Recommandation updateObs)throws SQLException{
       Recommandation recommandation = recommandationDao.findRecommandationById(id);
        if(recommandation!=null){
            recommandationDao.updateRecommandation(id, updateObs.getObservation());
        }
        else {
            throw new SQLException("erreur");
        }
    }
    @DeleteMapping("/recommandations/delete/{id}")
    public void deleteAnRecommandation(@PathVariable("id") int id) throws SQLException{
        recommandationDao.deleteRecommandation(id);
    }
}
