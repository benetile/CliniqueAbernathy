package com.clinique.proxy;

import com.clinique.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient",url = "localhost:8081")
public interface MPatientProxy {
    @PostMapping("/pat/add")
    PatientBean validatePatient(@RequestBody PatientBean patient);

    @GetMapping("/pat/patients")
    List<PatientBean> showAllPatients();

    @GetMapping("/pat/patients/{firstname}/{lastname}")
    PatientBean getPatientByFirstAndLastname(@PathVariable("firstname") String firstname,
                                             @PathVariable("lastname") String lastname);
    @GetMapping("/pat/id/{id}")
    PatientBean getPatientById(@PathVariable("id") Integer id);

    @PutMapping("/pat/update/{id}")
    PatientBean updateInfoPatient(@PathVariable("id") Integer id, @RequestBody PatientBean patient);

    @GetMapping("/pat/delete/{id}")
    void deletePatient(@PathVariable("id") Integer id);
}
