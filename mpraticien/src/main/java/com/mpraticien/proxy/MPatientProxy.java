package com.mpraticien.proxy;

import com.mpraticien.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient",url = "localhost:8081")
public interface MPatientProxy {

    @PostMapping(value ="/pat/add")
    PatientBean addNewPatient(PatientBean patientBean);

    @GetMapping(value = "/pat/patients")
    List<PatientBean> showAllPatients();

    @GetMapping(value ="/pat/id/{id}")
    PatientBean getPatientById(@PathVariable("id") int id);

    @GetMapping(value ="/pat/patients/{firstname}/{lastname}")
    PatientBean getPatientByLastANDFirstName(@PathVariable("firstName")String firstName, @PathVariable("lastName") String lastName);

    @PutMapping("/pat/update/{id}")
    PatientBean updateInfoPatient(@PathVariable("id") int id, @RequestBody PatientBean patientBean);

    @DeleteMapping("/pat/delete/{id}")
    void deletePatient(@PathVariable("id") int id);
}
