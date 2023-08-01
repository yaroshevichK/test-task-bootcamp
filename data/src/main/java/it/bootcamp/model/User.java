package it.bootcamp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.bootcamp.Constants.DB_FIRST_NAME;
import static it.bootcamp.Constants.DB_LAST_NAME;
import static it.bootcamp.Constants.DB_MIDDLE_NAME;
import static it.bootcamp.Constants.DB_ROLE_ID;
import static it.bootcamp.Constants.DB_USERS;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DB_USERS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DB_FIRST_NAME)
    private String firstName;

    @Column(name = DB_LAST_NAME)
    private String lastName;

    @Column(name = DB_MIDDLE_NAME)
    private String middleName;

    @Column
    private String email;

    @OneToOne
    @JoinColumn(name = DB_ROLE_ID)
    private Role role;
}
