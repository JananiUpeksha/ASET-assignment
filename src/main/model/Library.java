package com.example.library.model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

@MvcComponent(role = "Model")
public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<String> borrowRecords = new ArrayList<>(); // Simple record: "memberId:isbn"

    public void addBook(Book book) { books.add(book); }
    public void addMember(Member member) { members.add(member); }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(b -> !b.isBorrowed()).collect(Collectors.toList());
    }

    public List<Book> getAllBooks() { return List.copyOf(books); }
    public List<Member> getAllMembers() { return List.copyOf(members); }

    // Functional search using Predicate
    public List<Book> searchBooks(Predicate<Book> criteria) {
        return books.stream().filter(criteria).collect(Collectors.toList());
    }

    public boolean borrowBook(String memberId, String isbn) {
        Book book = books.stream().filter(b -> b.getIsbn().equals(isbn) && !b.isBorrowed()).findFirst().orElse(null);
        Member member = members.stream().filter(m -> m.getId().equals(memberId)).findFirst().orElse(null);
        if (book != null && member != null) {
            book.borrow();
            borrowRecords.add(memberId + ":" + isbn);
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        Book book = books.stream().filter(b -> b.getIsbn().equals(isbn) && b.isBorrowed()).findFirst().orElse(null);
        if (book != null) {
            book.returnBook();
            borrowRecords.removeIf(rec -> rec.endsWith(":" + isbn));
            return true;
        }
        return false;
    }
}