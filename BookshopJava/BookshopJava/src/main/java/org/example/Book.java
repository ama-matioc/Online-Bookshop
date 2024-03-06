package org.example;
public class Book extends Product implements Buyable, Lendable{

    public Book(int id, String title, String author, int year, float price) {
        super(id, title, author, year, price);
    }
    private boolean bIsSold=false;
    private boolean bIsLent=false;
    public boolean buy() {
        if (!bIsSold)
        {
            bIsSold=true;
            return true;
        }
        return false;
    }

    public boolean lend(String borrower) {
        if (bIsLent==false)
        {
            bIsLent=true;
            return true;
        }
        return false;
    }

    public boolean returnItem() {
        if (bIsLent==true){
            bIsLent=false ;
            return true;
    }
        return false;
    }
}
