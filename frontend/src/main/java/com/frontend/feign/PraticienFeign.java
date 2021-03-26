package com.frontend.feign;

import com.frontend.beans.PraticienBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@FeignClient(name = "mpraticien",url = "localhost:8082")
public interface PraticienFeign {

    @GetMapping("prat/praticiens")
    List<PraticienBean> getAllPraticiens();

    @PostMapping("/prat/add")
    PraticienBean addNewPraticien(@RequestBody PraticienBean praticien);

    @GetMapping("/prat/speciality/{speciality}")
    List<PraticienBean> getPraticensBySpeciality(@PathVariable("speciality") String speciality);

    @GetMapping("/prat/id/{id}")
    PraticienBean getPraticenById(@PathVariable("id") int id);

    @GetMapping("/prat/firstname/{firstName}")
    List<PraticienBean> getPraticensByFirstName(@PathVariable ("firstName") String firstName);

    @GetMapping("/prat/lastname/{lastName}")
    List<PraticienBean> getPraticiensByLastName(@PathVariable ("lastName") String lastName);

    @GetMapping("/prat/{firstName}/{lastName}")
    PraticienBean getPratcien(@PathVariable ("firstName") String firstName, @PathVariable("lastName") String lastName);

    @PutMapping("/prat/update/{id}")
    void updatePraticien(@PathVariable ("id") int id,@RequestBody PraticienBean praticien);

    @GetMapping("/prat/delete/{id}")
    void deletePraticien(@PathVariable ("id") int id);
}
