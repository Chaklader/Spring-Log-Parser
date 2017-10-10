package com.ef.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {


//    @Autowired
//    private GreetingService greetingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


//    @GetMapping("/greeting")
//    public String greetingForm(Model model) {
//        model.addAttribute("greeting", new Greeting());
//        return "greeting";
//    }


//    @PostMapping("/greeting")
//    public String greetingSubmit(@ModelAttribute("greeting") Greeting greeting, Model model) {
//
//        greetingService.save(greeting);
//
//        model.addAttribute("greeting", greetingService.findAll());
//        return "result";
//    }

}
