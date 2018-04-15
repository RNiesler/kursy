package rniesler.spring5guru.jokeapp.services;

import org.springframework.stereotype.Service;

@Service
public interface JokeService {
    String randomQuote();
}
