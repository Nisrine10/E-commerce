package com.projet.projet.web;


import com.projet.projet.entities.Admin;
import com.projet.projet.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor

public class HomeController {



    @GetMapping(path = "/Home")
    public String Signin(){
        return "index";
    }

    //----------------------------------




}
