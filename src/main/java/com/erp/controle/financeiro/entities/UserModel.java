//package com.erp.controle.financeiro.entities;
//
//import com.erp.controle.financeiro.enums.RoleName;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Table(name = "TB_USER")
//public class UserModel implements UserDetails, Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "USU_ID")
//    private Long id;
//    @Column(nullable = false, unique = true)
//    private String username;
//    @Column(nullable = false)
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private RoleName role;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    //getter e setter padrao das classes
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public RoleName getRole() {
//        return role;
//    }
//
//    public void setRole(RoleName role) {
//        this.role = role;
//    }
//}
