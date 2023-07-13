package com.projet.projet.web;



import com.projet.projet.entities.Client;
import com.projet.projet.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor

public class ClientController {
    private ClientRepository clientRepo;





    //----------------------------ajout-----------------
    @GetMapping(path = "/addClient")
    public String addClient(){
        return "addClient";
    }

    @GetMapping(path = "/ShowClient")
    public String showClient(Model model){
        List<Client> client=clientRepo.findAll();
        model.addAttribute("listClient",client);

        return "ShowClient";
    }



    @PostMapping(path = "/newClient")
    public String ajout(Model model, Client newClient){
        clientRepo.save(newClient);
        return "redirect:/ShowClient";
    }
    //------------------------------------------------
    @GetMapping(path = "/deleteClient")
    public String delClient(@RequestParam Long id){
        Client d = clientRepo.findById(id).orElse(null);
        clientRepo.delete(d);

        return "redirect:/ShowClient";
    }

    //----------------------Update----------------------
    @GetMapping(path = "/updateClient" )
    public String updateClient(@RequestParam Long id, Model model){
        Client d = clientRepo.findById(id).orElse(null);
        if(d != null) {
            model.addAttribute("update_clt"
                    , d);
        }

        return "updateClient";
    }
    //-------------------------------------------------------
    @PostMapping(path = "/updateClient")
    public String updateDirect(Model model, Client newClient){
        clientRepo.save(newClient);
        return "redirect:/ShowClient";
    }


}
