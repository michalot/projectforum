package com.sda.demo.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "user")
@SecondaryTable(name = "userDetails", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String login;
    @Column(length = 60)
    @Length(min = 5, message = "Your password must have at least 5 characters")
    private String password;
    private String role;
    @Column(name = "lock_date")
    private Date lockDate;

    @Column(name = "unlock_date")
    private Date unlockDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    public User() {
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public Date getUnlockDateDate() {
        return unlockDate;
    }

    public void setUnlockDate(Date joinDate) {
        this.unlockDate = joinDate;
    }

}
