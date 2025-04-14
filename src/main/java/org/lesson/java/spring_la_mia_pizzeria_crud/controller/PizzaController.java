package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;







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


    @GetMapping("/{id}")
    public String dettaglioPizza(Model model, @PathVariable("id") int id) {
        // prendo tutte le pizze e le faccio diventare una lista
        List<Pizza> pizze = repository.findAll();
        // faccio un ciclo for su tutta la lista
        for (Pizza pizza : pizze) {
            // faccio un if che mi controlla gli id, quando trovo quello corrispondente allora accedo
            if (pizza.getId() == id) {
                // al model aggiungo tutta la pizza con tutte le info
                model.addAttribute("pizza", pizza);  // Aggiungi l'intero oggetto pizza
                return "dettaglio";
            }
        }
        model.addAttribute("titolo", "Pizza " + id + " non trovata");
        return "dettaglio";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(name="name") String name, Model model) {
        List<Pizza> pizze = repository.findByNameContaining(name);
        model.addAttribute("pizze" , pizze);
        return "IndexPizze";
        
    }

    // creo il get e il post per il form di creazione

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "create";
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute ("pizza") Pizza PizzaForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "create";}
            repository.save(PizzaForm);
        
        return "redirect:/pizze";
    }
    
    // DELETE

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable ("id") Integer id) {
        repository.deleteById(id);
        
        return "redirect:/pizze";
    }
    
    // UPDATE

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute ("pizza") Pizza PizzaForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "edit";}
            repository.save(PizzaForm);
        
        return "redirect:/pizze";
    }
    
    
 }
    
    
    
    



