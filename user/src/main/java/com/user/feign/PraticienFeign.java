package com.user.feign;

import com.user.beans.PraticienBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "mpraticien", url = "localhost:8082")
public interface PraticienFeign {

    @GetMapping("/praticiens")
    List<PraticienBean> getAllPraticiens();

    @PostMapping("/praticiens/add")
    PraticienBean addNewPraticien(PraticienBean praticien);

    @GetMapping("/praticiens/{speciality}")
    List<PraticienBean> getPraticensBySpeciality(@PathVariable("speciality") String speciality);

    @GetMapping("/praticiens/{id}")
    PraticienBean getPraticenById(@PathVariable("id") int id);

    @GetMapping("/praticiens/firstman/{firstName}")
    List<PraticienBean> getPraticensByFirstName(@PathVariable ("firstName") String firstName);

    @GetMapping("/praticiens/lastName/{lastName}")
    List<PraticienBean> getPraticiensByLastName(@PathVariable ("lastName") String lastName);

    @GetMapping("/praticiens/{firstName}/{lastName}")
    public PraticienBean getPratcien(@PathVariable ("firstName") String firstName, @PathVariable("lastName") String lastName);

    @PutMapping("/praticiens/update/{id}")
    void updatePraticien(@PathVariable ("id") int id,@RequestBody PraticienBean praticien);

    @DeleteMapping("/praticiens/delete/{id}")
    void deletePraticien(@PathVariable ("id") int id);
}
