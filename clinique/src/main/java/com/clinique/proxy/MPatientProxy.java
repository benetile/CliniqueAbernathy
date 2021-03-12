package com.clinique.proxy;

import com.clinique.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient",url = "localhost:8081")
public interface MPatientProxy {
    @PostMapping(value ="/add")
    PatientBean addNewPatient(PatientBean patientBean);

    @GetMapping(value = "/patients")
    List<PatientBean> showAllPatients();

    @GetMapping(value ="/patients/{id}")
    PatientBean getPatientById(@PathVariable("id") int id);

    @GetMapping(value ="/patients/{firstName}/{lastName}")
    PatientBean getPatientByLastANDFirstName(@PathVariable("firstName")String firstName, @PathVariable("lastName") String lastName);

    @PutMapping("/patients/update/{id}")
    PatientBean updateInfoPatient(@PathVariable("id") int id, @RequestBody PatientBean patientBean);

    @DeleteMapping("/patients/delete/{id}")
    void deletePatient(@PathVariable("id") int id);
}
