package it.polito.ai.lab03.repository;

import org.apache.tomcat.jni.Time;
import org.springframework.data.annotation.Id;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private int userId;
    private UserCredentials userCredentials;
    private long lastAccess;
    private Role role;
    private List<Position> allPositions;

    public User() {

    }

    public User(String username, String password, Role role) {
        this.userCredentials = new UserCredentials(username, password);
        this.role = role;
        this.lastAccess = Time.now();
        userId = this.hashCode();

    }

    public int getUserId() {
        return userId;
    }

    // getters and setters

    public long getLastAccess() { return lastAccess; }

    public void setLastAccess(long lastAccess) { this.lastAccess = lastAccess; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public String getUsername() {
        return userCredentials.getUsername();
    }

    public String getPassword() {
        return userCredentials.getPassword();
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
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getLastAccess() == user.getLastAccess() &&
                Objects.equals(userCredentials, user.userCredentials) &&
                getRole() == user.getRole() &&
                Objects.equals(getAllPositions(), user.getAllPositions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCredentials, getLastAccess(), getRole(), getAllPositions());
    }

    public enum Role {
        ADMIN, USER, CUSTOMER
    }
}
