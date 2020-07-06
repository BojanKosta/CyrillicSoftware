package bojankosta.cyrillic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn( name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToMany(mappedBy = "accounts")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany (mappedBy = "account",  fetch = FetchType.EAGER)
    private List<Farm> farm = new ArrayList<>();



    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Farm> getFarm() {
        return farm;
    }

    public void setFarm(List<Farm> farm) {
        this.farm = farm;
    }

    public void setUser (User user) {
        this.users.add(user);
    }


}
