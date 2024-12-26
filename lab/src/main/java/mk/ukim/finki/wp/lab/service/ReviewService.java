package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;

public interface ReviewService {
 List<Review> getReviewsBySongTrackId (String trackId);
 void addReview(String comment, Integer rating, Song song);
}
