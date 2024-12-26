package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);


    void addNewSong(String title,
                    String trackId,
                    String genre,
                    int releaseYear,
                    Long albumId);
    Song editSong(Long songId,
                  String title,
                  String trackId,
                  String genre,
                  int releaseYear,
                  Long albumId);
    Song findBySongId(Long id);

    Song deleteSong(Long id);

     void addReviewToSong(Review review, Song song);



}
