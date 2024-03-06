package org.example;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class JSONConverter {
    public static JSONObject convertToJSONObject(Book book) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", book.getId());
        jsonObject.put("title", book.getTitle());
        jsonObject.put("author", book.getAuthor());
        jsonObject.put("year", book.getYear());
        jsonObject.put("price", book.getPrice());

        return jsonObject;
    }

    public static ArrayList<JSONObject> convertToJSONObjectList (Collection<Book> productArrayList) {
        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();
        for(Book book : productArrayList) {
            jsonObjectArrayList.add(convertToJSONObject(book));
            }
        return jsonObjectArrayList;
    }

    public static Product convertJsonObjectToProduct(JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        int year = jsonObject.getInt("year");
        float price = (float) jsonObject.getFloat("price");
        Book product = new Book(id, title, author, year, price);
        return product;
    }

}
