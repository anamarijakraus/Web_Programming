package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {
    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return DataHolder.songs.stream()
                .filter(song -> song.getTrackId().equals(trackId))
                .findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song){
       Optional<Song> songFound = DataHolder.songs.stream().filter(i -> i.getTrackId().equals(song.getTrackId())).findFirst();
        if (songFound.isEmpty()){
            throw new SongNotFoundException(song.getTrackId());
        }
        songFound.get().getPerformers().add(artist);
        return artist;
    }

    public Song addNewSong(String title, String trackId, String genre, int releaseYear, Album album)
    {
        Song newSong = new Song(trackId, title, genre, releaseYear, new ArrayList<>(), album);
        DataHolder.songs.add(newSong);
        return newSong;
    }
    public Optional<Song> findSongById(Long id) {
        return DataHolder.songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst();
    }

    public Song updateSong(Long songId, String title, String trackId, String genre, Integer releaseYear, Album album) throws SongNotFoundException
    {
        Song song = this.findSongById(songId).orElseThrow(() -> new SongNotFoundException(String.valueOf(songId)));
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        return song;
    }

    public Song deleteSong(Long id) throws SongNotFoundException
    {
        Song deleteSong = DataHolder.songs
                .stream()
                .filter(song -> song.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new SongNotFoundException(String.valueOf(id)));

        DataHolder.songs.remove(deleteSong);
        return deleteSong;
    }


}
