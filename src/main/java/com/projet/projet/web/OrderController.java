package com.projet.projet.web;

import com.projet.projet.entities.Client;
import com.projet.projet.entities.Order;
import com.projet.projet.entities.OrderLine;
import com.projet.projet.entities.Product;
import com.projet.projet.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor

public class OrderController {
    private OrderRepository orderRepo;
    private OrderLineRepository orderRepoL;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;


    //----------------------------------
    @GetMapping(path = "/order")
    public String getAllOrders(Model model){
        List<Order> order=orderRepo.findAll();
        model.addAttribute("listOrders",order);
        return "order";
    }

    //---------------------------home--------------------



    //----------------------------ajout-----------------
    @GetMapping(path = "/addOrder")
    public String addOrder(Model model){
        List<Client> client = clientRepository.findAll();
        model.addAttribute("listeOrders" ,client);
        return "addOrder";
    }

    @GetMapping(path = "/ShowOrder")
    public String showOrder(Model model){
        List<Order> order=orderRepo.findAll();
        model.addAttribute("listOrders",order);

        return "ShowOrder";
    }



    @PostMapping(path = "/newOrder")
    public String ajout(Model model, Order newOrder){
        orderRepo.save(newOrder);

        return "redirect:/ShowOrder";
    }
    //------------------------------------------------
    @GetMapping(path = "/deleteOrder")
    public String delOrder(@RequestParam Long id){
        Order d = orderRepo.findById(id).orElse(null);
        orderRepo.delete(d);

        return "redirect:/ShowOrder";
    }

    //----------------------Update----------------------
    @GetMapping(path = "/updateOrder" )
    public String updateOrder(@RequestParam Long id, Model model){
        Order d = orderRepo.findById(id).orElse(null);
        if(d != null) {
            model.addAttribute("update_order"
                    , d);
        }

        return "updateOrder";
    }
    //-------------------------------------------------------
    @PostMapping(path = "/updateOrder")
    public String updateDirect(Model model, Order newOrder){
        orderRepo.save(newOrder);
        return "redirect:/ShowOrder";
    }

    @GetMapping(path = "/addOrderLine" )
    public String OrderLine(@RequestParam Long id, Model model){
        Order d = orderRepo.findById(id).orElse(null);

        if(d != null) {
            model.addAttribute("order_N"
                    , d);

        }
            List<Product> product = productRepository.findAll();
            model.addAttribute("listeproducts" ,product);

        Order o = orderRepo.findById(id).orElse(null);
        if(o != null) {
            model.addAttribute("update_order"
                    , o);
        }
        return "addOrderLine";
    }
    @PostMapping(path = "/newOrderLine")
    public String newOrderLine(OrderLine newOrderL){
        double price = newOrderL.getProduct().getPrice();
        newOrderL.setTotal(newOrderL.getQuantity()*price);
       orderRepoL.save(newOrderL);
        return "redirect:/ShowOrder";
    }

    @GetMapping(path = "/ShowOrderLine" )
    public String ShowOrderLine(@RequestParam Long idO, Model model){

        List<OrderLine> d =  orderRepoL.findAll();

        List<OrderLine> up =new ArrayList<>();
        for (OrderLine ord : d) {
            System.out.println(ord.getOrder_id().getId());
            if(ord.getOrder_id().getId() == idO){
                up.add(ord);
            }

        }

        if(up != null) {
            model.addAttribute("order_N"
                    , up);

        }
        List<Product> product = productRepository.findAll();

        model.addAttribute("listeorderL" ,product);


        return "ShowOrderLine";
    }

    @GetMapping(path = "/updateOrderLine" )
    public String updateOrderLine(@RequestParam Long id, Model model){
        OrderLine d = orderRepoL.findById(id).orElse(null);

        if(d != null) {
            model.addAttribute("update_o"
                    , d);
        }
        List<Product> product = productRepository.findAll();

        model.addAttribute("update_clt" ,product);

        return "newOrderLine";
    }
    //-------------------------------------------------------


    @GetMapping(path = "/deleteOrderLine")
    public String delOrderLine(@RequestParam Long id,@RequestParam Long idO){
        OrderLine d = orderRepoL.findById(id).orElse(null);
        orderRepoL.delete(d);

        return "redirect:/ShowOrderLine?idO="+idO;
    }
}
