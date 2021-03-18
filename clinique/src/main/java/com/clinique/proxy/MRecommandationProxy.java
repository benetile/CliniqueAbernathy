package com.clinique.proxy;

import com.clinique.beans.RecommandationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "mpraticien",url = "localhost:8082")
public interface MRecommandationProxy {

    @GetMapping("/recommandations/patients/{id}")
    List<RecommandationBean> getAllRecommandationPatient(@PathVariable ("id") int id);

    @GetMapping("/recommandations/patients/id/{id}")
    RecommandationBean getRecommandationById(@PathVariable("id") int id);

    @PostMapping("/recommandations/add/{id}")
    RecommandationBean addNewRecommandation(@RequestBody RecommandationBean input,@PathVariable("id") int id);

    @PutMapping("/recommandations/update/{id}")
    void updateAObservation(@PathVariable ("id") int id, @RequestBody RecommandationBean updateObs);

    @DeleteMapping("/recommandations/delete/{id}")
    void deleteRecommandation(@PathVariable("id") int id);
}
