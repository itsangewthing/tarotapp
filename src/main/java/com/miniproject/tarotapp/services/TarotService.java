package com.miniproject.tarotapp.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.miniproject.tarotapp.models.Card;

import org.springframework.web.client.RestTemplate;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.StringReader;

@Service
public class TarotService {

    // inject into service the private  key
    @Value("${API_KEY}")
    private String apiKey;

    final static String BASE_URL = "https://tarot-api.onrender.com/api/v1";

    private static final String GET_ALL_CARDS_URL = "https://tarot-api.onrender.com/api/v1/cards";
    private static final String GET_A_RANDOM_CARD_URL = "https://tarot-api.onrender.com/api/v1/cards/random?n=1";
    
/* GET ALL CARDS */    
    public List<Card> search(String searchCards) {
        String uri = UriComponentsBuilder.fromUriString(BASE_URL)
                            .queryParam("q", searchCards)
                            .queryParam("suit", "wands")
                            .queryParam("value", "5")
                            .queryParam("type", "major","minor")
                            .toUriString();

        System.out.println(searchCards);
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("X-RapidAPI-Key", "ddf652f3-9cdc-41d8-a1ae-4fd741185668");
        headers.add("X-RapidAPI-Host", "https://tarot-api.onrender.com/api/v1");

        HttpEntity<?> entity = new HttpEntity<Object>(headers);
        HttpEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        String payload = resp.getBody();

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject result = reader.readObject();
        JsonArray data = result.getJsonObject("info").getJsonArray("cards");
        
        List<Card> cList = new LinkedList<>();
        for (Integer i = 0; i < data.size(); i++) {
            cList.add(Card.create(data.getJsonObject(i)));
        }

        return cList;
     }


/* GET 1 RANDOM CARD */
    
    public Card getARandomCard(String name) {
        
        Long ts = System.currentTimeMillis();
        String signature = "%d%s%s".formatted(ts, apiKey);
        String hash = "";
 /*  GET_A_RANDOM_CARD_URL = "https://tarot-api.onrender.com/api/v1/cards/random?n=1";
        returns 1 number of unique random cards, else returns all cards in random order
        ?n = 1  

         */

         String searchUrl = UriComponentsBuilder.fromUriString(GET_A_RANDOM_CARD_URL)
            .path(name)
            .queryParam("ts", ts)
            .queryParam("apikey", apiKey)
            .queryParam("hash", hash)
            .toUriString();

            if (searchUrl.contains("%7D")) {
                searchUrl = searchUrl.replace("%7D", "");
            }
            System.out.println("url > " + searchUrl);
     
            RequestEntity<Void> req = RequestEntity.get(searchUrl).accept(MediaType.APPLICATION_JSON).build();
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp = template.exchange(req, String.class);
            String payload = resp.getBody();
            
            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonObject result = reader.readObject();
            JsonArray data = result.getJsonObject("data").getJsonArray("results");

            Card c = Card.create(data.getJsonObject(0));
                return c;
        }
    
    }


    

