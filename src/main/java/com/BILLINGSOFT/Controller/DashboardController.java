package com.BILLINGSOFT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

     @GetMapping("/home")
    public String dashboard(Model model){

        return "dashboard";
    }   

}
