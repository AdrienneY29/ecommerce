package com.tts.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

// this import conflicts with the one from javax.persistence
//import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.Collection;
import java.util.Map;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "Please provide a username")
/*    @Length(min = 3, message = "Your username must have at least 3 characters")
    @Length(max = 15, message = "Your username cannot have more than 15 characters")*/
    @Pattern(regexp = "[^\\s]+", message = "Your username cannot contain spaces")
    private String username;

    @Length(min = 8, message = "Your password must have at least 8 characters")
    @NotEmpty(message = "Please provide a password")
    @Pattern(regexp = "(?=^.{6,255}$)((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))^.*", message = "Your password should have 1 uppercase, 1 lowercase, 1 number, 1 special character and be 8 characters long.")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    //from twitter this is to show whether user is enabled value of 1= active
//    private int active;


    //Map
    @ElementCollection
    private Map<Product, Integer> cart;

//    public void setCart (Map<Product, Integer> cart){
//            User user = getLoggedInUser();
//            saveExisting(user);
//    }
    //UserDetails requires these, these are technically getters overridden by lombok
    //Transient makes it so these aren't persisted in the database, as they are hard coded
//
//    @Transient
//    private boolean accountNonExpired = true;
//
//    @Transient
//    private boolean accountNonLocked = true;
//
//    @Transient
//    private boolean credentialsNonExpired = true;
//
//    @Transient
//    private boolean enabled = true;
//
//    @Transient
//    private Collection<GrantedAuthority> authorities = null;


        @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
//
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    //this is new created from userService
//    public void setCart(Map<Product, Integer> cart) {
//        this.cart = cart;
//    }
//
//    public Map<Product, Integer> getCart() {
//        return cart;
//    }

//    public void setPassword(String password) {
//        this.password = password;
//    }


    //

}