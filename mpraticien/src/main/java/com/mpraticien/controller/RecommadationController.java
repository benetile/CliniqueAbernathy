package com.mpraticien.controller;

import com.mpraticien.beans.PatientBean;
import com.mpraticien.model.Recommandation;
import com.mpraticien.proxy.MPatientProxy;
import com.mpraticien.service.dao.RecommandationDao;
import com.mpraticien.service.impl.GenerateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class RecommadationController {

    @Autowired
    private MPatientProxy mPatientProxy;

    @Autowired
    private RecommandationDao recommandationDao;

    @Autowired
    private GenerateIdService generateIdService;

    @GetMapping("/recommandation/patients/{id}")
    public List<Recommandation> getAllRecommandationPatient(@PathVariable ("id") int id) throws SQLException{
        return recommandationDao.findById(id);
    }

    @GetMapping("/recommandation/patients/id/{id}")
    public Optional<Recommandation> getRecommandationById(@PathVariable("id") int id) throws SQLException {
        return recommandationDao.findRecommandationById(id);
    }
    @PostMapping("/recommandations/add/{id}")
    public Recommandation addNewRecommandation(@RequestBody Recommandation inputRecommandation,@PathVariable("id") int id) throws SQLException{
        PatientBean bean = mPatientProxy.getPatientById(id);
        inputRecommandation.setId(generateIdService.getSequenceNumberRecommandtaion(Recommandation.SEQUENCE_RECOMMANDATION));
        inputRecommandation.setIdPatient(bean.getId());
        return recommandationDao.addRecommandation(inputRecommandation);
    }

    @PutMapping("/recommandations/update/{id}")
    public void updateAObservation(@PathVariable ("id") int id, @RequestBody Recommandation updateObs)throws SQLException{
       updateObs.setId(id);
       recommandationDao.updateRecommandation(id,updateObs);
    }
    @DeleteMapping("/recommandations/delete/{id}")
    public void deleteRecommandation(@PathVariable("id") int id) throws SQLException{
        recommandationDao.deleteRecommandation(id);
    }
}
