package com.app.controllers;

import com.app.model.Category;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.ProductForm;
import com.app.service.CategoryService;
import com.app.service.ProducerService;
import com.app.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class ProductController {

    private ProductService productService;
    private ProducerService producerService;
    private CategoryService categoryService;
    private ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ProducerService producerService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.producerService = producerService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/product/form", method = RequestMethod.GET)
    public String productFormGet(Model model, Producer producer, Category category) {
        ProductForm product = new ProductForm();
        model.addAttribute("product", product);
        model.addAttribute("producer", producerService.getAll());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("errors", new LinkedHashMap<>());

        return "product/insert";
    }

    @RequestMapping(value = "/product/form", method = RequestMethod.POST)
    public String productFormPost(
            @ModelAttribute ProductForm productForm, Model model

    ) {

        //model.addAttribute("product", product);
        Product product
                = Product
                .builder()
                .name(productForm.getName())
                .price(productForm.getPrice())
                .category(categoryService.getById(productForm.getCategoryId()).orElseThrow(NullPointerException::new))
                .producer(producerService.getById(productForm.getProducerId()).orElseThrow(NullPointerException::new))
                .colour(productForm.getColour())
                .build();
        productService.saveProduct(product);
        return "redirect:/product/select_all";
    }

    @RequestMapping("/")
    public String test() {
        return "product/welcome";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
    public String personDelete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product/select_all";
    }

    @RequestMapping(value = "/product/select_all", method = RequestMethod.GET)
    public String produdctSelectAll(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("producer", producerService.getAll());
        model.addAttribute("category", categoryService.getAll());
        return "product/select_all";
    }

    @RequestMapping(value = "/product/details/{id}", method = RequestMethod.GET)
    public String productDetails(@PathVariable Long id, Model model) {
        Optional<Product> productOp = productService.getById(id);
        if (productOp.isPresent()) {
            model.addAttribute("product", productOp.get());
            return "product/details";
        }
        return "redirect:/product/select_all";
    }

    @RequestMapping(value = "/product/update/{id}", method = RequestMethod.GET)
    public String productUpdateGet(@PathVariable Long id, Model model) {
        Optional<Product> productOp = productService.getById(id);

        if (productOp.isPresent()) {
            model.addAttribute("product", productOp.get());
            return "product/update";
        }
        return "redirect:/product/select_all";
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String productUpdatePost(@ModelAttribute Product product, Model model, HttpServletRequest request) {
        if (product != null) {
            productService.modifyProduct(product);

        }
        return "redirect:/product/select_all";
    }
}
