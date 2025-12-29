package com.example.library;
@PersistentEntity(description = "Represents a library member")
public class Member {
    private final String id;
    private final String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("Member[ID=%s, Name='%s']", id, name);
    }
}