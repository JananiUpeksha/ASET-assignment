package com.example.library.model;
@PersistentEntity(description = "Represents a book in the library")
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean borrowed;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    // Immutable getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return borrowed; }

    public void borrow() { this.borrowed = true; }
    public void returnBook() { this.borrowed = false; }

    @Override
    public String toString() {
        return String.format("Book[ISBN=%s, Title='%s', Author='%s', Borrowed=%b]",
                isbn, title, author, borrowed);
    }
}