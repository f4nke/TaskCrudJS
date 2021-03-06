package crud.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade=CascadeType.ALL)
    @JoinTable(name = "users_role")
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Integer id, String name, String username, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getRolesToString(){
        StringBuilder sb = new StringBuilder();
        for (Role i : roles){
            sb.append(i.getRole() + " ");
        }
        return String.valueOf(sb);
    }
}
