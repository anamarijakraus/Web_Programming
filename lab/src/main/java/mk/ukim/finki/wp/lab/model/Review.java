package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Integer rating;

    @ManyToOne
    private Song song;

    public Review(String comment, Integer rating, Song song) {
        this.comment = comment;
        this.rating = rating;
        this.song = song;
    }

    public Review(String comment, Integer rating) {
        this.comment = comment;
        this.rating = rating;
    }
}
