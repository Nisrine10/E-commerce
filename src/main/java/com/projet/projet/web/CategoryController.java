package com.projet.projet.web;



import com.projet.projet.entities.Categories;
import com.projet.projet.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor

public class CategoryController {
    private CategoryRepository categoriesRepo;





    //----------------------------ajout-----------------
    @GetMapping(path = "/addCategory")
    public String addCategories(){
        return "addCategory";
    }

    @GetMapping(path = "/ShowCategory")
    public String showCategories(Model model){
        List<Categories> categories=categoriesRepo.findAll();
        model.addAttribute("listCategory",categories);

        return "ShowCategory";
    }



    @PostMapping(path = "/newCategory")
    public String ajout(Model model, Categories newCategories){
        categoriesRepo.save(newCategories);
        return "redirect:/ShowCategory";
    }
    //------------------------------------------------
    @GetMapping(path = "/deleteCategory")
    public String delCategories(@RequestParam Long id){
        Categories d = categoriesRepo.findById(id).orElse(null);
        categoriesRepo.delete(d);

        return "redirect:/ShowCategory";
    }

    //----------------------Update----------------------
    @GetMapping(path = "/updateCategory" )
    public String updateCategories(@RequestParam Long id, Model model){
        Categories d = categoriesRepo.findById(id).orElse(null);
        if(d != null) {
            model.addAttribute("update_cat"
                    , d);
        }

        return "updateCategory";
    }
    //-------------------------------------------------------
    @PostMapping(path = "/updateCategory")
    public String updateDirect(Model model, Categories newCategories){
        categoriesRepo.save(newCategories);
        return "redirect:/ShowCategory";
    }


}
