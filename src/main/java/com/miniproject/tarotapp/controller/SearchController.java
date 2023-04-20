package com.miniproject.tarotapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.tarotapp.models.Card;
import com.miniproject.tarotapp.models.Value;
import com.miniproject.tarotapp.respositories.RedisRepo;
import com.miniproject.tarotapp.services.TarotService;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin()
public class SearchController {

    @Autowired
    private TarotService tarotSvc;

    @Autowired
    private RedisRepo redisRepo;
    
    // SEARCH ALL CARDS
    @GetMapping(path="./cards/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search(@RequestParam("searchCards") String searchCards) {
        List<Card> cards = tarotSvc.search(searchCards);
        System.out.println(searchCards);

        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (int i = 0; i < cards.size(); i++) {
            jab.add(cards.get(i).toJSON());
        }
        JsonArray ja = jab.build();

        return ResponseEntity.ok(ja.toString());
    }

// GET RANDOM CARD
    @GetMapping(path="/cards/random?n=1", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin()
    public ResponseEntity<String> getRandomCard(@RequestParam String name, @RequestParam int value_int, @RequestParam int offset) {
        System.out.println("scheming through value_int to get random card");

        List<Card> card = tarotSvc.getRandomCardByValue(name, value_int, offset);
        
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Card c : card) {
            JsonObject jo = c.toJSON();

            redisRepo.setKey(String.valueOf(jo.getInt("value")), jo);

            jab.add(jo);
        }
        JsonArray ja = jab.build();
        
        System.out.println("returning a random card");

        return ResponseEntity.ok(ja.toString());
    }

    
}
