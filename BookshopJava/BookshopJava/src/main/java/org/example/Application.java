package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.json.JSONObject;

public class Application {

    InputDevice inputDevice;
    OutputDevice outputDevice;

    public Application(InputDevice inputDevice, OutputDevice outputDevice) {
        this.inputDevice = inputDevice;
        this.outputDevice = outputDevice;
    }
    private Bookshelf bookCollection = new Bookshelf();

    public void run(boolean bIsAdmin) {

        shelfLoad(bookCollection);

        Book book= bookCollection.getBookByName("Persuasion");
        book.addReview(new Review("Adam", "Highly recommend it!", 5));
        book.addReview(new Review("John", "Could have been better!", 3));

        boolean bIsRunning = true;
        while(bIsRunning) {
            if(bIsAdmin) {
              //ADMIN
                outputDevice.display("Choose a number between 0-6\n" +
                        "0. Exit\n" +
                        "1. Display books\n" +
                        "2. Display books ordered by price\n" +
                        "3. Add book\n" +
                        "4. Delete book\n" +
                        "5. Search for a book by title\n" +
                        "6. Search for a book by id\n");
                int choice = inputDevice.getIntInput();
                inputDevice.getStringInput();
                switch (choice) {
                    case 0:
                        outputDevice.writeJsonObjectsToFile(JSONConverter.convertToJSONObjectList(bookCollection.getBookCollection()), "books.json");
                        bIsRunning = false;
                        break;
                    case 1: //display book
                        displayInventory(bookCollection);
                        break;
                    case 2:  // Sorted by price
                        bookCollection.sortBookCollection();
                        displayInventory(bookCollection);
                        break;
                    case 3: //add book
                        int id;
                        outputDevice.display("Enter the book ID: ");
                        id = inputDevice.getIntInput();
                        inputDevice.getStringInput();
                        outputDevice.display("Enter book title: ");
                        String title=inputDevice.getStringInput();
                        outputDevice.display("Enter book author: ");
                        String author=inputDevice.getStringInput();
                        outputDevice.display("Enter book publication year: ");
                        int year=inputDevice.getIntInput();
                        inputDevice.getStringInput();
                        outputDevice.display("Enter book price: ");
                        float price=inputDevice.getFloatInput();
                        inputDevice.getStringInput();
                        Book newbook= new Book(id,title,author,year,price);
                        bookCollection.addBook(newbook);
                        displayBookInfo(newbook);
                        break;

                    case 4: //delete book
                        outputDevice.display("Enter book name: ");
                        String title2 = inputDevice.getStringInput();
                        Book nbook=bookCollection.getBookByName(title2);
                        bookCollection.deleteBook(nbook);
                        break;
                    case 5: //search by title
                        outputDevice.display("Enter book title: ");
                        String title1 = inputDevice.getStringInput();
                        displayBookInfo(bookCollection.getBookByName(title1));
                        break;
                    case 6: //search by id
                        outputDevice.display("Enter book ID: ");
                        int id1=inputDevice.getIntInput();
                        inputDevice.getStringInput();
                        displayBookInfo(bookCollection.getBookByID(id1));
                        break;
                    default:
                        outputDevice.display("Invalid choice. Please provide a number between 0-6 as program argument.");

                }
            } else {
                //USER
                outputDevice.display("Choose a number between 0-8 \n" +
                        "0. Exit\n" +
                        "1. Display books\n" +
                        "2. Display books ordered by price\n" +
                        "3. Display reviews for a book\n" +
                        "4. Search for a book by title \n" +
                        "5. Search for a book by author\n"+
                        "6. Add review for a book\n"+
                        "7. Buy a book\n"+
                        "8. Borrow a book\n");
                int choice= inputDevice.getIntInput();
                inputDevice.getStringInput();
                switch (choice) {
                    case 0: //exit
                        bIsRunning = false;
                        break;
                    case 1: //display books
                        displayInventory(bookCollection);
                        break;
                    case 2: //sort by price
                        bookCollection.sortBookCollection();
                        displayInventory(bookCollection);
                        break;
                    case 3: //reviews for a book
                        outputDevice.display("Enter book title: ");
                        String title = inputDevice.getStringInput();
                        displayBookReviews(title);
                        break;
                    case 4: //search by title
                        outputDevice.display("Enter book title: ");
                        String title1 = inputDevice.getStringInput();
                        displayBookInfo(bookCollection.getBookByName(title1));
                        break;
                    case 5: // search by author
                        outputDevice.display("Enter author's name: ");
                        String author = inputDevice.getStringInput();
                        ArrayList<Book> allBooksByAuthor=bookCollection.getAllBooksByAuthor(author);
                        if (allBooksByAuthor!=null && !allBooksByAuthor.isEmpty())
                            displayInventory(allBooksByAuthor);
                        else outputDevice.display("No books found for author: "+author);
                        break;
                    case 6: //add review
                        outputDevice.display("Enter book title: ");
                        String title2 = inputDevice.getStringInput();
                        if (bookCollection.getBookByName(title2) == null)
                            outputDevice.display("Book not found!\n\n");
                        else {
                            Product book1 =bookCollection.getBookByName(title2);
                            outputDevice.display("Enter reviewer's name: ");
                            String reviewer = inputDevice.getStringInput();
                            outputDevice.display("Enter the comment: ");
                            String comment = inputDevice.getStringInput();
                            outputDevice.display("Enter a rating between 1-5: ");
                            int rating = inputDevice.getIntInput();
                            inputDevice.getStringInput();
                            Review review = new Review(reviewer, comment, rating);
                            book1.addReview(review);
                        }
                        break;
                    case 7: //buy book
                        outputDevice.display("Enter book title: ");
                        Book buyItem= bookCollection.getBookByName(inputDevice.getStringInput());
                        displayBookInfo(buyItem);
                        if (buyItem.buy())
                            outputDevice.display("Book purchased successfully!\n");
                        else outputDevice.display("Book is sold or lent!\n");
                        break;
                    case 8: //borrow book
                        outputDevice.display("Enter book title: ");
                        Book bookborrow= bookCollection.getBookByName(inputDevice.getStringInput());
                        displayBookInfo(bookborrow);
                        outputDevice.display("Enter your name: ");
                        String name= inputDevice.getStringInput();
                        if (bookborrow.lend(name))
                            outputDevice.display("You borrowed the book succesfully!\n");
                        else outputDevice.display("Book is already lent!\n");
                        break;
                    default:
                        outputDevice.display("Invalid choice. Please provide a number between 0-5 as program argument.");
                }

            }
        }
    }
    public void displayBookInfo(Book book)
    {
        if (book!=null) {
            outputDevice.display("Book ID: "+ book.getId()+'\n');
            outputDevice.display("Book title: " + book.getTitle()+'\n');
            outputDevice.display("Book author: " + book.getAuthor()+'\n');
            outputDevice.display("Year: " + book.getYear()+'\n');
            outputDevice.display("Price: " + book.getPrice()+'\n'+'\n');
    } else outputDevice.display("Book not found! \n\n");
    }

    public void displayBookReviews(String title)
    {
        Book book = bookCollection.getBookByName(title);
        if (book!=null) {
            outputDevice.display('\n' + "Reviews for the book: " + book.getTitle() + '\n' + '\n');
            Collection<Review> reviewList = new ArrayList<Review>(book.getReviewCollection());
            for (Review review : reviewList) {
                outputDevice.display("Reviewer's name: " + review.getReviewer() + '\n');
                outputDevice.display("Reviewer's comment: " + review.getComment() + '\n');
                outputDevice.display("Reviewer's rating: " + review.getRating() + '\n' + '\n');
            }
        } else outputDevice.display("Book not found!\n\n");
    }


    public void displayInventory(Bookshelf bookCollection)
    {

        for (Book book: bookCollection.getBookCollection())
            this.displayBookInfo(book);
    }
    public void displayInventory(ArrayList<Book> bookCollection)
    {

        for (Book book: bookCollection)
            this.displayBookInfo(book);
    }
    private void shelfLoad(Bookshelf bookCollection) {
        for(JSONObject jsonObject : InputDevice.readJSONObjectFromFile("books.json")) {
            bookCollection.addBook(((Book) JSONConverter.convertJsonObjectToProduct(jsonObject)));
        }
    }
}
