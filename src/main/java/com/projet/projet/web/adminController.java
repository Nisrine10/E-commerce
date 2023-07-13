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

public class adminController {
    private AdminRepository adminRepo;


    @GetMapping(path = "/Admin")
    public String Signin(){
        return "Admin";
    }

    //----------------------------------
    @GetMapping(path = "/admin")
    public String getAllAdmins(Model model, ModelMap modelE, @RequestParam String email, @RequestParam String password){
        List<Admin> admin=adminRepo.findAll();
        model.addAttribute("listAdmins",admin);

        if((email.equals(adminRepo.findByEmail(email)) && (password.equals(adminRepo.findByEmail(password))))) {
            return "Home";
        }

            modelE.put("ErrorMsg","Please enter the correct email or password");
            return "admin";
    }

    //---------------------------home--------------------


    @GetMapping(path = "/addAdmin")
    public String addCategories(){
        return "addAdmin";
    }



    @GetMapping(path = "/ShowAdmin")
    public String showCategories(Model model){
        List<Admin> admin= adminRepo.findAll();
        model.addAttribute("listAdmin", admin);

        return "ShowAdmin";
    }



    @PostMapping(path = "/newAdmin")
    public String ajoutAdmin(Model model,Admin newAdmin){
        adminRepo.save(newAdmin);
        return "redirect:/Admin";
    }

    @GetMapping(path = "/updateAdmin" )
    public String updateAdmin(@RequestParam Long id, Model model){
        Admin d = adminRepo.findById(id).orElse(null);
        if(d != null) {
            model.addAttribute("update_pro"
                    , d);
        }

        return "updateAdmin";
    }
    //-------------------------------------------------------
    @PostMapping(path = "/updateAdmin")
    public String updateAdmin(Model model, Admin newAdmin){
        adminRepo.save(newAdmin);
        return "redirect:/ShowAdmin";
    }





}
