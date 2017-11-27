package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TwitterRepository twitterRepository;

    @RequestMapping("/")
    public String listTwitter(Model model){
        model.addAttribute("messages", twitterRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String twitterForm(Model model){
        model.addAttribute("twitter", new Twitter());
        return "twitterform";
    }

    @PostMapping("/process")
    public String twitterForm(@Valid Twitter twitter, BindingResult result)
    {
        if (result.hasErrors()){
            return "twitterform";
        }
        twitterRepository.save(twitter);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateTwitter(@PathVariable("id") long id, Model model){
        model.addAttribute("twitter", twitterRepository.findOne(id));
        return "twitterform";
    }

    @RequestMapping("/detail/{id}")
    public String showTwitter(@PathVariable("id") long id, Model model){
        model.addAttribute("twiiter", twitterRepository.findOne(id));
        return "list";
    }
    @RequestMapping("/delete/{id}")
    public String delTwitter(@PathVariable("id") long id){
        twitterRepository.delete(id);
        return "redirect:/";
    }

        }
