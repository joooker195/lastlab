package ru.ssau.controllers;

import model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.BuyerService;

import java.util.List;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(method = RequestMethod.GET, value = "/welcome")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllBuyers")
    public @ResponseBody List<Buyer> getAllBuyerrs(){
        List<Buyer> buyers = buyerService.getAll();
        return buyers;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateBuyer")
    public void updateBuyer(){
        buyerService.updateBuyer(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addBuyer")
    public void addBuyer(){
        buyerService.addBuyer(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteBuyer")
    public void deleteBuyer(){
        buyerService.removeBuyer(null);
    }
}
