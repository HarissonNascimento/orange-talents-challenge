package com.orangetalents.orangetalentschallenge.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(unique = true, nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false)
    private LocalDate birthday;
}

