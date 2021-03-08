package com.tts.ecommerce.controller;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Data
@Controller
@ControllerAdvice //this makes the "@ModelAttribute's of this controller available to all controllers, for the navbar
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute("categories")
    public List<String> categories() {
        return productService.findDistinctCategories();
    }

    @ModelAttribute("brands")
    public List<String> brands() {
        return productService.findDistinctBrands();
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String category, @RequestParam(required = false) String brand, Model model) {
        List<Product> filtered = productService.findByBrandAndOrCategory(brand, category);
        model.addAttribute("products", filtered); //Overrides the @ModelAttribute above.
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    public void addNew() {
        List<Product> allProducts = productService.findAll();

            if (allProducts.isEmpty()) {
                List<Product> newProducts = new ArrayList<Product>();
                newProducts.add(new Product(4, (float) 1500.00, "Apple MacBook Pro", "Apple", "Macbook Pro", "computer",
                        "images/macbook_pro_blk.jpg"));
                newProducts.add(new Product(3, (float) 1000.00, "C7 ST Desktop Front Edit", "Dell", "Desktop", "computer",
                        "images/C7_Desktop.jpg"));
                newProducts.add(new Product(12, (float) 800.00, "New iPhone 8, Silver", "Apple IPhone 8", "Apple Iphone", "phone",
                        "images/iphone8_silver.jpg"));
                newProducts.add(new Product(7,(float) 700.00, "New iPhone", "IPhone", "IPhone", "phone",
                        "images/iphone_black.jpg"));
                for (Product product : newProducts) {
                    productService.save(product);
                }
        }

        else {
            System.out.println("You don't need more items!");
        }
    }
}