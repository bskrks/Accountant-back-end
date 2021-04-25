package com.Expert.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})                         // The situation for both adresses are equal
@ToString
public class User {

    @Id
    @SequenceGenerator(name="seq_users", allocationSize = 1)
    @GeneratedValue(generator = "seq_users", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name="first_name")
    private String firstName;

    @Column(length = 100, name="last_name")
    private String lastName;

    @Column(unique = true, length = 150, name="email")
    private String email;

}
