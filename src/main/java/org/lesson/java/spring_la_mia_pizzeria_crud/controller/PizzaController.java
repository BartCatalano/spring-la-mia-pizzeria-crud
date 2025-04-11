package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("pizze")
public class PizzaController {

      @Autowired
    private PizzaRepository repository;

    @GetMapping()
    public String pizze(Model model) {
        List<Pizza> pizze = repository.findAll();
        model.addAttribute("pizze", pizze );
        
        return "IndexPizze";
    }
    
    
    
    
}


