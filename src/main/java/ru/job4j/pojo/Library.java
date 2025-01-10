package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Lord of the rings", 331);
        Book book2 = new Book("Clean code", 255);
        Book book3 = new Book("The Little Prince", 100);
        Book book4 = new Book("Crime and Punishment", 500);
        Book[] books = new Book[4];
        books[0] = book1;
        books[1] = book2;
        books[2] = book3;
        books[3] = book4;
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println(book.getName() + ", " + book.getCount());
        }
        System.out.println();
        System.out.println("swap 0 and 3");
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            System.out.println(book.getName() + ", " + book.getCount());
        }
        System.out.println();
        System.out.println("Shown only book.name = Clean code");
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            if (book.getName() == "Clean code") {
                System.out.println(book.getName() + ", " + book.getCount());
            }
        }
    }
}
