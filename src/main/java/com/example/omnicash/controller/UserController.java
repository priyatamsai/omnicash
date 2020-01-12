package com.example.omnicash.controller;


import com.example.omnicash.Pair;
import com.example.omnicash.model.Outlet;
import com.example.omnicash.model.User;
import com.example.omnicash.repository.OutletRepository;
import com.example.omnicash.repository.UserRepository;
import com.example.omnicash.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OutletRepository outletRepository;

    @PostMapping("/register")
    public User createUser(@Valid @RequestBody User user) {
    	return userRepository.save(user);

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
        List<Pair<Outlet, Double> > outlet_list=new ArrayList<>();
        for (Outlet outlet : outletRepository.findAll()){
            Double dist = utils.haversine(outlet.getLatitude(),outlet.getLongitude(),latitude, longitude);
            if (dist < 2 && outlet.getBalance_money()>=req_amount){
                Pair <Outlet, Double> o = new Pair(outlet, dist);
                outlet_list.add(o);
            }
        }
        Collections.sort(outlet_list, new OutletComparator());

        List<Outlet> outlets=new ArrayList<>();
        for (Pair<Outlet, Double> o : outlet_list){
            outlets.add(o.getFirst());
        }
        return outlets;
    }

    class OutletComparator implements Comparator<Pair<Outlet, Double>> {
        @Override
        public int compare(Pair<Outlet, Double> a, Pair<Outlet, Double> b) {
            return a.getSecond() < b.getSecond() ? -1 : a.getSecond() == b.getSecond() ? 0 : 1;
        }
    }


}
