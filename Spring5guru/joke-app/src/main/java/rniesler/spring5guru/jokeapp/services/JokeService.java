package rniesler.spring5guru.jokeapp.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

@Service
public class JokeService {

    private ChuckNorrisQuotes chuckNorrisQuotes = new ChuckNorrisQuotes();

    public String randomQuote() {
        return chuckNorrisQuotes.getRandomQuote();
    }
}
