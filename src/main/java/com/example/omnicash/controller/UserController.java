package com.example.omnicash.controller;

import com.example.omnicash.model.Location;
import com.example.omnicash.model.Outlet;
import com.example.omnicash.model.User;
import com.example.omnicash.repository.OutletRepository;
import com.example.omnicash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    userRepository.save(user);)
    return "Success";

    }

    @GetMapping("/fetch_nearest_stores/{latitude}/{longitude}/{city}")
    public String fetch_nearest_stores(@PathVariable(value = "latitude") String latitude,
                                       @PathVariable(value = "longitude") String longitude,
                                       @PathVariable(value = "city") String city){
        List<Outlet> outlet_list=new ArrayList<>();
        Location user_loc = new Location(latitude, longitude, city);
        for (Outlet outlet : outletRepository.findAll()){
            if (consider(outlet.getLocation(), user_loc)){

            }
        }

        return "";
    }
}
