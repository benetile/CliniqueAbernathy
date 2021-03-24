package com.frontend.feign;

import com.frontend.beans.AssessBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "clinique",url = "localhost:8080")
public interface AssessFeign {

    @GetMapping("/assess/delete/{id}")
    void deleteAnAssessment(@PathVariable("id") int id);

    @GetMapping("/assess/id/{id}")
    AssessBean generateAssessment(@PathVariable ("id") int id);

    @GetMapping("/assess")
    List<AssessBean> getAllAssessments();

    @GetMapping("/assess/firstName/{firstName}")
    AssessBean getAssementByname(@PathVariable("firstName") String firstName);
}
