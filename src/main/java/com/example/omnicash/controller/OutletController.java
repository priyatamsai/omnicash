package com.example.omnicash.controller;

import com.example.omnicash.model.Outlet;
import com.example.omnicash.model.User;
import com.example.omnicash.repository.OutletRepository;
import com.example.omnicash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@RestController
@RequestMapping("/outlet")
public class OutletController {
    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register/{name}")
    public String registerDetails(@PathVariable(value = "name") String Name) {
        Outlet o = new Outlet();
        o.setOutletName(Name);
        outletRepository.save(o);
        return "Success";
    }

    @GetMapping("/maketransaction/{usrid}/{outletid}/{user_otp}/{amount}")
    public String MakeTransaction(@PathVariable(value = "usrid") long usrid,
                                  @PathVariable(value = "outletid") long outletid,
                                  @PathVariable(value = "user_otp") int user_otp,
                                  @PathVariable(value = "amount") int amount) {
        Outlet outlet = outletRepository.findById(outletid).orElseThrow(null);
        User user = userRepository.findById(usrid).orElseThrow(null);

        if (user.getContact().equals("") || !user.getActive_otp().equals(user_otp)){
            return "Improper Transaction";
        }

        if(amount > outlet.getBalance_money()){
            return "Insufficient Balance at the store";
        }
        if(amount > user.getMoney_wallet()){
            return "Insufficient Balance in user wallet";
        }
        Date otp_createdAt = user.getOtpcreatedAt();
        Date curDate = new Date();
        if (TimeUnit.MILLISECONDS.toSeconds(curDate.getTime()- otp_createdAt.getTime()) > 10*60){
            return "OTP expired";
        }
        long curUserBal = user.getMoney_wallet();
        long curOutletBal = outlet.getBalance_money();

        user.setMoney_wallet(curUserBal - amount);
        user.setActive_otp("");
        outlet.setBalance_money(curOutletBal - amount);
        userRepository.save(user);
        outletRepository.save(outlet);
        return "Transaction Success";
    }
}
