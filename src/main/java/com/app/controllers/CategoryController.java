package com.app.controllers;

import com.app.model.Category;
import com.app.model.Product;
import com.app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Optional;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/category/form", method = RequestMethod.GET)
    public String categoryFormGet(Model model)
    {
        Category category = new Category();
        model.addAttribute("category",category);
        model.addAttribute("errors", new LinkedHashMap<>());
        return "category/insert";
    }
    @RequestMapping (value = "/category/form", method = RequestMethod.POST)
    public String categoryFormPost(
            Category category,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("category", category);
        categoryService.saveCategory(category);
        return "redirect:/category/select_all";
    }
    @RequestMapping(value = "/category/select_all", method = RequestMethod.GET)
    public String categorySelectAll(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/select_all";
    }
    @RequestMapping (value = "/category/delete/{id}", method = RequestMethod.GET)
    public String categoryDelete(@PathVariable Long id)
    {
        categoryService.deleteCategory(id);
        return "redirect:/category/select_all";
    }
    @RequestMapping(value = "/category/details/{id}",method = RequestMethod.GET)
    public String categoryDetails (@PathVariable Long id, Model model)
    {
        Optional<Category> categoryOp = categoryService.getById(id);
        if (categoryOp.isPresent())
        {
            model.addAttribute("category", categoryOp.get());
            return "category/details";
        }
        return "redirect:/category/select_all";
    }
    @RequestMapping (value = "/category/update/{id}",method = RequestMethod.GET)
    public String categoryUpdateGet(@PathVariable Long id, Model model)
    {
        Optional<Category> categoryOp = categoryService.getById(id);
        if (categoryOp.isPresent())
        {
            model.addAttribute("category", categoryOp.get());
            return "category/update";
        }
        return "redirect:/category/select_all";
    }
    @RequestMapping(value = "/category/update",method = RequestMethod.POST)
    public String categoryUpdatePost(@ModelAttribute Category category, Model model, HttpServletRequest request)
    {
        if (category != null) {
            categoryService.modifyCategory(category);
        } return "redirect:/category/select_all";
    }
}
