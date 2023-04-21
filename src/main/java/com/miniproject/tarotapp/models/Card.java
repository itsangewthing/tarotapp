package com.miniproject.tarotapp.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;



public class Card {
    private String name_short;
    private String name;
    private String value;
    private Integer value_int;
    private String meaning_up;
    private String meaning_reverse;
    private String desc;

        // convert enum to List of String
        public enum TypeEnum {
            MAJOR("major"),
            MINOR("minor");
            private String value;
        
            TypeEnum(String value) {
              this.value = value;
            }
    
            @Override
            @JsonValue
            public String toString() {
              return String.valueOf(value);
            }
        
            @JsonCreator
            public static TypeEnum fromValue(String text) {
              for (TypeEnum b : TypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                  return b;
                }
              }
              return null;
            }
          }
    private TypeEnum type;

    @Override
    public String toString() {
        return "{" +
            " name_short='" + getName_short() + "'" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", value_int='" + getValue_int() + "'" +
            ", meaning_up='" + getMeaning_up() + "'" +
            ", meaning_reverse='" + getMeaning_reverse() + "'" +
            ", desc='" + getDesc() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
    
    public String getType() {
        return type.toString();
    }

    public void setType(TypeEnum string) {
        this.type = string;
    }


    public String getName_short() {
        return this.name_short;
    }

    public void setName_short(String name_short) {
        this.name_short = name_short;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getValue_int() {
        return this.value_int;
    }

    public void setValue_int(Integer value_int) {
        this.value_int = value_int;
    }

    public String getMeaning_up() {
        return this.meaning_up;
    }

    public void setMeaning_up(String meaning_up) {
        this.meaning_up = meaning_up;
    }

    public String getMeaning_reverse() {
        return this.meaning_reverse;
    }

    public void setMeaning_reverse(String meaning_reverse) {
        this.meaning_reverse = meaning_reverse;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    




    public static Card create(JsonObject obj) {
        final Card c = new Card();
        // JsonArray cards = obj.getJsonArray("cards");
        // JsonObject obj = card.getJsonObject(0);
        c.setName_short(obj.getString("name_short"));
        c.setName(obj.getString("name"));
        c.setValue(obj.getString("value"));
        c.setValue_int(obj.getInt("value_int"));
        c.setMeaning_up(obj.getString("meaning_up"));
        c.setMeaning_reverse(obj.getString("meaning_reverse"));
        c.setDesc(obj.getString("desc"));
        c.setType(obj.getString("type"));
        
        return c;
    }
 

    private void setType(String string) {
    }

    public static Card fromCache(JsonObject j) {
        final Card c = new Card();

        c.setName_short(j.getString("name_short"));
        c.setName(j.getString("name"));
        c.setValue(j.getString("value"));
        c.setValue_int(j.getInt("value_int"));
        c.setMeaning_up(j.getString("meaning_up"));
        c.setMeaning_reverse(j.getString("meaning_reverse"));
        c.setDesc(j.getString("desc"));
        c.setType(j.getString("type"));



        return c;
    }

    public JsonObject toJSON() {
        List<String> ty = getType().toString();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (int i = 0; i < ty.size(); i++) {
            jab.add(ty.get(i));
        }
        JsonArray tyArray = jab.build();

        return Json.createObjectBuilder()
                .add("name_short", getName_short())
                .add("name", getName())
                .add("value", getValue())
                .add("value_int", getValue_int())
                .add("meaning_up", getMeaning_up())
                .add("meaning_reverse", getMeaning_reverse())
                .add("type", getType())
                .build();
                
    }






 
    
    
}

