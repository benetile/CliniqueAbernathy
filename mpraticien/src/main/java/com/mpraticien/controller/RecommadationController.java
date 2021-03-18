package com.mpraticien.controller;

import com.mpraticien.beans.PatientBean;
import com.mpraticien.model.Recommandation;
import com.mpraticien.proxy.MPatientProxy;
import com.mpraticien.service.dao.RecommandationRepository;
import com.mpraticien.service.impl.GenerateIdService;
import com.mpraticien.service.impl.RecommandationService;
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
    private RecommandationRepository recommandationRepository;

    @Autowired
    private RecommandationService recommandationService;

    @Autowired
    private GenerateIdService generateIdService;

    @GetMapping("/recommandations/patients/{id}")
    public List<Recommandation> getAllRecommandationPatient(@PathVariable ("id") int id) throws SQLException{
        return recommandationRepository.findByIdPatient(id);
    }

    @GetMapping("/recommandations/patients/id/{id}")
    public Optional<Recommandation> getRecommandationById(@PathVariable("id") int id) throws SQLException {
        return recommandationRepository.findById(id);
    }

    @PostMapping("/recommandations/add/{id}")
    public Recommandation addNewRecommandation(@RequestBody Recommandation input,@PathVariable("id") int id) throws SQLException{
        PatientBean bean = mPatientProxy.getPatientById(id);
        if(bean==null){
            return null;
        }
        input.setId(generateIdService.getSequenceNumberRecommandtaion(Recommandation.SEQUENCE_RECOMMANDATION));
        input.setIdPatient(id);
        return recommandationRepository.save(input);
    }

    @PutMapping("/recommandations/update/{id}")
    public void updateAObservation(@PathVariable ("id") int id, @RequestBody Recommandation updateObs)throws SQLException{
       updateObs.setId(id);
      // recommandationService.updateRecommandation(id,updateObs.getObservation());
       //(id, updateObs);
    }
    @DeleteMapping("/recommandations/delete/{id}")
    public void deleteRecommandation(@PathVariable("id") int id) throws SQLException{
        recommandationRepository.deleteById(id);
    }
}
