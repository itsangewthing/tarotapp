package com.miniproject.tarotapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class CardList {
    private Integer nhits;
    private String name_short;
    private String name;
    private String value;
    private Integer value_int;
    private String meaning_up;
    private String meaning_reverse;
    private String desc;

   
    public Integer getNhits() {
        return this.nhits;
    }

    public void setNhits(Integer nhits) {
        this.nhits = nhits;
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

    public TypeEnum getType() {
        return this.type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

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
    private List<Card> cards;

    //////////////////

                @Valid
                public List<Card> getCards() {
                List<Card> cards;
                return cards;
            }

            public void setCards(List<Card> cards) {
                this.cards = cards;
            }

                public List<Card> cards(List<Card> cards) {
                    List<Card> cards;
                    return cards;
                
                }
                
                public CardList addCardsItem(Card cardsItem) {
                    if (this.cards == null) {
                    this.cards = new ArrayList<Card>();
                    }
                    this.cards.add(cardsItem);
                    return this;
                }


            @Override
            public boolean equals(java.lang.Object o) {
                if (this == o) {
                return true;
                }
                if (o == null || getClass() != o.getClass()) {
                return false;
                }
                CardList cardList = (CardList) o;
                return Objects.equals(this.nhits, cardList.nhits) &&
                    Objects.equals(this.cards, cardList.cards);
            }

            @Override
            public int hashCode() {
                return Objects.hash(nhits, cards);
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("class CardList {\n");
                
                sb.append("    nhits: ").append(toIndentedString(nhits)).append("\n");
                sb.append("    cards: ").append(toIndentedString(cards)).append("\n");
                sb.append("}");
                return sb.toString();
            }

            /**
             * Convert the given object to string with each line indented by 4 spaces
             * (except the first line).
             */
            private String toIndentedString(java.lang.Object o) {
                if (o == null) {
                return "null";
                }
                return o.toString().replace("\n", "\n    ");
            }
}
    
