package com.example.library;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

@MvcComponent(role = "View")
public class LibraryView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void displayMembers(List<Member> members) {
        members.forEach(System.out::println);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getMenuChoice() {
        System.out.println("\n=== Library Menu ===");
        System.out.println("1. List all books");
        System.out.println("2. Search books by title");
        System.out.println("3. Borrow book");
        System.out.println("4. Return book");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
        return Integer.parseInt(scanner.nextLine());
    }
}