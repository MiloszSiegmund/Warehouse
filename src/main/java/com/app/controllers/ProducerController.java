package com.app.controllers;

import com.app.model.Producer;
import com.app.model.Product;
import com.app.service.ProducerService;
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
public class ProducerController {

    private ProducerService producerService;
    private ModelMapper modelMapper;

    @Autowired
    public ProducerController(ProducerService producerService, ModelMapper modelMapper) {
        this.producerService = producerService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping (value = "/producer/form", method = RequestMethod.GET)
    public String producerFormGet(Model model) {
        Producer producer = new Producer();
        model.addAttribute("producer", producer);
        model.addAttribute("errors", new LinkedHashMap<>());
        return "producer/insert";
    }

    @RequestMapping(value = "/producer/form", method = RequestMethod.POST)
    public String producerFormPost(
            Producer producer,
            Model model,
            HttpServletRequest request
    ){
        model.addAttribute("producer",producer);
        producerService.saveProducer(producer);
        return "redirect:/producer/select_all";
    }
    @RequestMapping(value = "/producer/select_all", method = RequestMethod.GET)
    public String producerSelectAll(Model model) {
        model.addAttribute("producers", producerService.getAll());
        return "producer/select_all";
    }
    @RequestMapping (value = "/producer/delete/{id}",method = RequestMethod.GET)
    public String producerDelete(@PathVariable Long id)
    {
        producerService.deleteProducer(id);
        return "redirect:/producer/select_all";
    }
    @RequestMapping (value = "/producer/details/{id}", method = RequestMethod.GET)
    public String producerDetails(@PathVariable Long id, Model model)
    {
        Optional<Producer> producerOp = producerService.getById(id);
        if (producerOp.isPresent())
        {
            model.addAttribute("producer",producerOp.get());
            return "producer/details";
        }
        return "redirect:/producer/select_all";
    }
    @RequestMapping (value = "/producer/update/{id}",method = RequestMethod.GET)
    public String producerUpdateGet(@PathVariable Long id, Model model)
    {
        Optional<Producer> producerOp = producerService.getById(id);
        if (producerOp.isPresent())
        {
            model.addAttribute("producer", producerOp.get());
            return "producer/update";
        }
        return "redirect:/producer/select_all";
    }
    @RequestMapping(value = "/producer/update",method = RequestMethod.POST)
    public String producerUpdatePost(@ModelAttribute Producer producer)
    {
        if (producer != null) {
            producerService.modifyProducer(producer);
        } return "redirect:/producer/select_all";
    }
}
