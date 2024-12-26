package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemorySongRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }


    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        if (artist == null || song == null) {
            throw new IllegalArgumentException();
        }
        if (song.getPerformers().contains(artist)) {
            return artist;
        }

        song.getPerformers().add(artist);
        songRepository.save(song);

        return artist;
    }


    @Override
    public Song findByTrackId(String trackId) {
        Optional<Song> songOptional = songRepository.findByTrackId(trackId);
        if (songOptional.isEmpty()) {
            throw new SongNotFoundException(trackId);
        }
        return songOptional.get();
    }
    @Override
    public void addNewSong(String title, String trackId, String genre, int releaseYear, Long albumId){
        if(title == null || title.isEmpty()
                || trackId == null || trackId.isEmpty()
                || genre == null || genre.isEmpty() || albumId == null) {
            throw new IllegalArgumentException();

        }
        Album album = this.albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));
        songRepository.save(new Song(trackId, title, genre, releaseYear, album));
    }

    @Override
    public Song editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId){
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));

        Song songToUpdate = songRepository
                .findById(songId)
                .orElseThrow(() -> new SongNotFoundException(String.valueOf(songId)));

        songToUpdate.setTitle(title);
        songToUpdate.setTrackId(trackId);
        songToUpdate.setGenre(genre);
        songToUpdate.setReleaseYear(releaseYear);
        songToUpdate.setAlbum(album);

        return songRepository.save(songToUpdate);

    }

    @Override
    public Song findBySongId(Long id) {
        Optional<Song> songOptional = songRepository.findById(id);
        if (songOptional.isEmpty()){
            throw new SongNotFoundException(String.valueOf(id));
        }
        return songOptional.get();
    }

    @Override
    public Song deleteSong(Long id) throws SongNotFoundException
    {
        Song deletedSong = this.songRepository
                .findById(id)
                .orElseThrow(() -> new SongNotFoundException(String.valueOf(id)));
        this.songRepository.deleteById(id);
        return deletedSong;
    }

    @Override
    public void addReviewToSong(Review review, Song song){
        song.getReviews().add(review);
        songRepository.save(song);
    }
}
