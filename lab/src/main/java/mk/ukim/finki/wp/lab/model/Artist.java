package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data //Getters, Setters, toString, hashcode, equals, RequiredArgsConstructor
@AllArgsConstructor // bidejki so Data se kreira konstruktor so no-arguments ili so tolku argumenti kolku shto ima final polinja
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;
    String bio;

    public Artist(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public Artist() {
    }

}
