package com.example.omnicash.controller;

import com.example.omnicash.model.Location;
import com.example.omnicash.model.Outlet;
import com.example.omnicash.model.User;
import com.example.omnicash.repository.OutletRepository;
import com.example.omnicash.repository.UserRepository;
import com.example.omnicash.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OutletRepository outletRepository;

    @GetMapping("/register/{name}/{phoneno}")
    public String registerDetails(@PathVariable(value = "name") String Name,
                                  @PathVariable(value = "phoneno") String Phone){
    User user=new User();
    user.setContact(Phone);
    user.setUserName(Name);
    userRepository.save(user);
    return "Success";
    }

    @GetMapping("/generateotp/{id}")
    public String generateOtp(@PathVariable(value = "id") Long id){
        User user=userRepository.findById(id).orElseThrow(null);
        String otp=""+(int)(Math.random()*10000);
        user.setActive_otp(otp);
        Date currDate = new Date();
        user.setOtpcreatedAt(currDate);
        userRepository.save(user);
        return otp;
    }

    @GetMapping("/fetch_nearest_stores/{latitude}/{longitude}/{city}/{req_amount}")
    public List<Outlet> fetch_nearest_stores(@PathVariable(value = "latitude") Double latitude,
                                             @PathVariable(value = "longitude") Double longitude,
                                             @PathVariable(value = "req_amount") Double req_amount,
                                             @PathVariable(value = "city") String city){
        List<Outlet> outlet_list=new ArrayList<>();
        for (Outlet outlet : outletRepository.findAll()){
            if ((utils.haversine(outlet.getLocation().getLatitude(),outlet.getLocation().getLongitude(),latitude, longitude) < 2) && outlet.getBalance_money()>=req_amount){
                outlet_list.add(outlet);
            }
        }

        return outlet_list;
    }

}
