package com.example.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String name;

    @Lob
    private String surname;

    private Integer age;

    @Lob
    private String email;

    @Lob
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Lob
    @Column
    private String avatar;

    @Column
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "applicant")
    List<Resume> resumes;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "author")
    List<Vacancy> vacancies;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sender")
    List<Message> messages;

    @ManyToOne
    @JoinColumn(name = "acc_type")
    private Authority role;
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("full"));
    }

    @Override
    public String getUsername() {
        return null;
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
}
