package com.miniproject.tarotapp.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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


    }

