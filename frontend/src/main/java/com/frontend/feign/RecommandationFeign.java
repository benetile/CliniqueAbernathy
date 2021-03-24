package com.frontend.feign;

import com.frontend.beans.RecommandationBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mpraticien",url = "localhost:8082")
public interface RecommandationFeign {

    @GetMapping("/recommandations/patients/{id}")
    List<RecommandationBean> getAllRecommandationPatient(@PathVariable("id") int id);

    @GetMapping("/recommandations/patients/id/{id}")
    RecommandationBean getRecommandationById(@PathVariable("id") int id);

    @PostMapping("/recommandations/add/{id}")
    RecommandationBean addNewRecommandation(@RequestBody String observation, @PathVariable("id") int id);

    @PutMapping("/recommandations/update/{id}")
    RecommandationBean updateAObservation(@PathVariable ("id") int id, @RequestBody RecommandationBean updateObs);

    @GetMapping("/recommandations/delete/{id}")
    void deleteRecommandation(@PathVariable("id") int id);
}
