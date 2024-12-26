package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ReviewService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/song-details")
public class SongDetailsController {

    private final SongService songService;
    private final ReviewService reviewService;

    public SongDetailsController(SongService songService, ReviewService reviewService) {
        this.songService = songService;
        this.reviewService= reviewService;
    }

    @GetMapping
    public String getSongDetailsPage(@RequestParam String trackId, Model model) {

        Song song = songService.findByTrackId(trackId);
        List<Review> reviews = reviewService.getReviewsBySongTrackId(trackId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("songId", song.getId());
        model.addAttribute("song", song);
        return "songDetails";
    }


}
