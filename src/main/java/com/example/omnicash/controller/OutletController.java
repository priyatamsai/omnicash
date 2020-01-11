package com.example.omnicash.controller;

import com.example.omnicash.model.Outlet;
import com.example.omnicash.model.User;
import com.example.omnicash.repository.OutletRepository;
import com.example.omnicash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/outlet")
public class OutletController {
    @Autowired
    private OutletRepository outletRepository;

    @GetMapping("/register/{name}")
    public String registerDetails(@PathVariable(value = "name") String Name) {
        Outlet o = new Outlet();
        o.setOutletName(Name);
        outletRepository.save(o);
        return "Success";
    }
}
