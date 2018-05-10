package it.polito.ai.lab03.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class User {

    @Id
    @Indexed(unique = true)
    private String username;

    private String password;
    private long lastAccess;
    private Role role;
    private List<Position> allPositions;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // getters and setters

    public long getLastAccess() { return lastAccess; }

    public void setLastAccess(long lastAccess) { this.lastAccess = lastAccess; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Position> getAllPositions() {
        return allPositions;
    }

    public void addPosition(Position position) {
        this.allPositions.add(position);
    }

    public void addPositions(List<Position> positions) {
        this.allPositions.addAll(positions);
    }

    @Override
    public String toString() {
        return this.username + " => " + this.password + " => " + this.role;
    }

    public enum Role {
        ADMIN, USER, CUSTOMER
    }

}
