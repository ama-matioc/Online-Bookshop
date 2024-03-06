package org.example;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Product implements Comparable<Product>{
    private final int id;
    private String title;
    private String author;
    private int year;
    private float price;
    private Collection<Review> reviewCollection = new ArrayList<Review>();

    //Constructor
    public Product(int  id, String title, String author,int year,float price)
    {
        this.id=id;
        this.title=title;
        this.author=author;
        this.year=year;
        this.price=price;
    }
    public int getId() { return id; }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public int getYear()
    {
        return year;
    }
    public float getPrice()
    {
        return price;
    }

    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void addReview(Review review) {
        reviewCollection.add(review);
    }

    public int compareTo(Product product) {
        if (price > product.price)
            return 1;
        else if (price<product.price)
            return -1;
        return 0;
    }
}
