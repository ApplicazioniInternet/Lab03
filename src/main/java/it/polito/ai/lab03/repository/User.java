package it.polito.ai.lab03.repository;

import org.apache.tomcat.jni.Time;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private ObjectId id;

    private Collection<GrantedAuthority> authorities = new ArrayList<>();
    private String username;
    private String password;
    private long lastAccess;
    private Role role;
    private List<Position> allPositions;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.lastAccess = Time.now();
    }

    // getters and setters

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public long getLastAccess() { return lastAccess; }

    public void setLastAccess(long lastAccess) { this.lastAccess = lastAccess; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
                Objects.equals(id, user.id) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                getRole() == user.getRole() &&
                Objects.equals(getAllPositions(), user.getAllPositions());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, getUsername(), getPassword(), getLastAccess(), getRole(), getAllPositions());
    }

    public enum Role {
        ADMIN, USER, CUSTOMER
    }
}
