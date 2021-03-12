package com.clinique.proxy;

import com.clinique.beans.RecommandationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mpraticien",url = "localhost:8082")
public interface MRecommandationProxy {

    @GetMapping("/recommandation/patients/{id}")
    List<RecommandationBean> getAllRecommandationPatient(@PathVariable("id") int id);

    @PostMapping("/recommandations/add/{id}")
    void addNewRecommandation(@RequestBody RecommandationBean inputRecommandation, @PathVariable("id") int id);

    @PutMapping("/recommandations/update/{id}")
    void updateAObservation(@PathVariable ("id") int id, @RequestBody RecommandationBean updateObs);

    @DeleteMapping("/recommandations/delete/{id}")
    void deleteAnRecommandation(@PathVariable("id") int id);
}
