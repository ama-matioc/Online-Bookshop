package org.example;
public class Review {
    private String reviewer;
    private String comment;
    private int rating;

    //Constructor
    public Review(String reviewer,String comment,int rating)
    {
        this.reviewer=reviewer;
        this.comment=comment;
        this.rating=rating;

    }

    public String getReviewer()
    {
        return reviewer;
    }
    public String getComment()
    {
        return comment;
    }
    public int getRating()
    {
        return rating;
    }
}
