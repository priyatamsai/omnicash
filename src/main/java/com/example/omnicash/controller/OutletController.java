package com.example.omnicash.controller;

import com.example.omnicash.model.Outlet;
import com.example.omnicash.model.User;
import com.example.omnicash.repository.OutletRepository;
import com.example.omnicash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@RestController
@RequestMapping("/outlet")
public class OutletController {
    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Outlet createPlayer(@Valid @RequestBody Outlet outlet) {
    	return outletRepository.save(outlet);
    }

    @GetMapping("/maketransaction/{usrid}/{outletid}/{user_otp}/{amount}")
    public String MakeTransaction(@PathVariable(value = "usrid") long usrid,
                                  @PathVariable(value = "outletid") long outletid,
                                  @PathVariable(value = "user_otp") String user_otp,
                                  @PathVariable(value = "amount") int amount) {
        Outlet outlet = outletRepository.findById(outletid).orElseThrow(null);
        User user = userRepository.findById(usrid).orElseThrow(null);

        if (user.getActive_otp().equals("") || !user.getActive_otp().equals(user_otp)){
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
    @GetMapping("/IsActive/{outletid}/{status}")
    public String setActive(@PathVariable(value = "outletid") long outletid,
                            @PathVariable(value = "status") Boolean isactive){
        Outlet outlet = outletRepository.findById(outletid).orElseThrow(null);
        outlet.setIsActive(isactive);
        outletRepository.save(outlet);
        return "SUCCESS";
    }

    @GetMapping("/InformCash/{outletid}/{cash}")
    public String InformCash(@PathVariable(value = "outletid") long outletid,
                             @PathVariable(value = "cash") long cash){
        Outlet outlet = outletRepository.findById(outletid).orElseThrow(null);
        outlet.setBalance_money(cash);
        outletRepository.save(outlet);
        String message = "The amount of cash available is "+cash;
        return message;
    }

}
