package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.ReviewRepository;
import mk.ukim.finki.wp.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviewsBySongTrackId(String trackId) {
        return reviewRepository.findAllBySong_TrackId(trackId);
    }

    @Override
    public void addReview(String comment, Integer rating, Song song) {
        reviewRepository.save(new Review(comment, rating, song));
    }
}
