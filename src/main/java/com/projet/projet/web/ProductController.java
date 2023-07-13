package com.projet.projet.web;

import com.projet.projet.entities.Categories;
import com.projet.projet.entities.Product;
import com.projet.projet.repositories.CategoryRepository;
import com.projet.projet.repositories.ProductRepository;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





        import java.util.List;

@Controller
@AllArgsConstructor

public class ProductController {
    private ProductRepository productRepo;
    private final CategoryRepository categoryRepository;


    //----------------------------------
    @GetMapping(path = "/product")
    public String getAllProducts(Model model){
        List<Product> product=productRepo.findAll();
        model.addAttribute("listProducts",product);
        return "product";
    }

    //---------------------------home--------------------


    //----------------------------ajout-----------------
    @GetMapping(path = "/addProduct")
    public String addProduct(Model model){
        List<Categories> category = categoryRepository.findAll();
        model.addAttribute("listeProducts" ,category);
        return "addProduct";
    }

    @GetMapping(path = "/ShowProduct")
    public String showProduct(Model model){
        List<Product> product=productRepo.findAll();
        model.addAttribute("listProducts",product);

        return "ShowProduct";
    }



    @PostMapping(path = "/newProduct")
    public String ajout(Model model, Product newProduct){
        productRepo.save(newProduct);
        List<Categories> category = categoryRepository.findAll();
        model.addAttribute("listeProducts" ,category);
        return "redirect:/ShowProduct";
    }
    //------------------------------------------------
    @GetMapping(path = "/deleteProduct")
    public String delProduct(@RequestParam Long id){
        Product d = productRepo.findById(id).orElse(null);
        productRepo.delete(d);

        return "redirect:/ShowProduct";
    }

    //----------------------Update----------------------
    @GetMapping(path = "/updateProduct" )
    public String updateProduct(@RequestParam Long id, Model model){
        Product d = productRepo.findById(id).orElse(null);
        if(d != null) {
            model.addAttribute("update_pro"
                    , d);
        }

        return "updateProduct";
    }
    //-------------------------------------------------------
    @PostMapping(path = "/updateProduct")
    public String updateDirect(Model model, Product newProduct){
        productRepo.save(newProduct);
        return "redirect:/ShowProduct";
    }


}
