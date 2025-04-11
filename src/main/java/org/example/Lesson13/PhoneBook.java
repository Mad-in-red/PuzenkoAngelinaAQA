package org.example.Lesson13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private Map<String, List<String>> book;

    public PhoneBook(){
        book = new HashMap<>();
    }

    public void addNumber(String surname, String phoneNumber){
         book.putIfAbsent(surname, new ArrayList<>());
         book.get(surname).add(phoneNumber);
    }

    public List<String> getNumber(String surname){
        return book.getOrDefault(surname, new ArrayList<>());
    }
}
