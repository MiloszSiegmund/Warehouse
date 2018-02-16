package com.app.controllers;

import com.app.model.Product;
import com.app.model.Trade;
import com.app.service.TradeService;
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
public class TradeController {

    private TradeService tradeService;
    private ModelMapper modelMapper;

    @Autowired
    public TradeController(TradeService tradeService, ModelMapper modelMapper) {
        this.tradeService = tradeService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/trade/form", method = RequestMethod.GET)
    public String tradeFormGet(Model model)
    {
        Trade trade = new Trade();
        model.addAttribute("trade",trade);
        model.addAttribute("errors", new LinkedHashMap<>());
        return "trade/insert";
    }
    @RequestMapping (value = "/trade/form", method = RequestMethod.POST)
    public String tradeFormPost(
            Trade trade,
            Model model,
            HttpServletRequest request
    ){
        model.addAttribute("trade", trade);
        tradeService.saveTrade(trade);
        return "redirect:/trade/select_all";

    }
    @RequestMapping(value = "/trade/select_all", method = RequestMethod.GET)
    public String tradeSelectAll(Model model) {
        model.addAttribute("tradies", tradeService.getAll());
        return "trade/select_all";
    }
    @RequestMapping(value = "/trade/delete/{id}" ,method = RequestMethod.GET)
    public String tradeDelete(@PathVariable Long id)
    {
        tradeService.deleteTrade(id);
        return "redirect:/trade/select_all";
    }
    @RequestMapping (value = "/trade/details/{id}", method = RequestMethod.GET)
    public String tradeDetails(@PathVariable Long id, Model model)
    {
        Optional<Trade> tradeOp = tradeService.getById(id);
        if (tradeOp.isPresent())
        {
            model.addAttribute("trade",tradeOp.get());
            return "trade/details";
        }
        return "redirect:/trade/select_all";
    }
    @RequestMapping (value = "/trade/update/{id}",method = RequestMethod.GET)
    public String tradeUpdateGet(@PathVariable Long id, Model model)
    {
        Optional<Trade> tradeOp = tradeService.getById(id);
        if (tradeOp.isPresent())
        {
            model.addAttribute("trade", tradeOp.get());
            return "trade/update";
        }
        return "redirect:/trade/select_all";
    }
    @RequestMapping(value = "/trade/update",method = RequestMethod.POST)
    public String tradeUpdatePost(@ModelAttribute Trade trade, Model model, HttpServletRequest request)
    {
        if (trade != null) {
            tradeService.modifyTrade(trade);
        } return "redirect:/trade/select_all";
    }
}
