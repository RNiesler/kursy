package rniesler.spring5guru.jokeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rniesler.spring5guru.jokeapp.services.JokeService;

@Controller
public class JokeController {
    private JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @RequestMapping({"/", ""})
    public String getRandomJoke(Model model) {
        model.addAttribute("joke", jokeService.randomQuote());
        return "chucknorris";
    }
}
