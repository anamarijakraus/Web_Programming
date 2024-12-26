package mk.ukim.finki.wp.lab.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;

import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//bidejki ne koristime baza, ovde gi chuvame podatocite prvichni, sho gi imame pri sekoe startuvanje na app
@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;
    public static List<Album> albums=null;

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    //private final ArtistService artistService;

    public DataHolder(AlbumRepository albumRepository, ArtistRepository artistRepository, SongRepository songRepository, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
       // this.artistService = artistService;
    }

    // za da se povika init metodot ni treba anotacijata
    // ke se izvrshi pri startuvanje na aplikacijata za da se inicijalizira listata, sho ke se prikazhi od pochetok na stranata
    @PostConstruct
    public void init() {
        artists = new ArrayList<>();
        artists.add(new Artist("Dusty", "Springfield", "Spooky"));
        artists.add(new Artist("Whitney", "Houston", "The Greatest Love of All"));
        artists.add(new Artist("Nina", "Simone", "I put a spell on you"));
        artists.add(new Artist("Janis", "Joplin", "Cry Baby"));
        artists.add(new Artist("Michael", "Jackson", "Dirty Diana"));
        artistRepository.saveAll(artists);

//        List<Artist> songArtist1 = new ArrayList<>();
//        songArtist1.add(artists.get(0));
//        List<Artist> songArtist2 = new ArrayList<>();
//        songArtist2.add(artists.get(1));
//        List<Artist> songArtist3 = new ArrayList<>();
//        songArtist3.add(artists.get(2));
//        List<Artist> songArtist4 = new ArrayList<>();
//        songArtist4.add(artists.get(3));
//        List<Artist> songArtist5 = new ArrayList<>();
//        songArtist5.add(artists.get(4));

        albums = new ArrayList<>();
        albums.add(new Album("Whatever", "Soul", "1991"));
        albums.add(new Album("Whenever", "Pop", "1996"));
        albums.add(new Album("However", "Gospel Soul", "1962"));
        albums.add(new Album("Whoever", "Rock", "1969"));
        albums.add(new Album( "Ever", "Pop", "1982"));
        albumRepository.saveAll(albums);

        songs = new ArrayList<>();
        songs.add(new Song("1", "In private", "Soul", 1990, artists.subList(0, 1), albums.get(0)));
        songs.add(new Song("2", "Exhale", "Pop", 1995, artists.subList(1, 2), albums.get(1)));
        songs.add(new Song("3", "Sinner man", "Gospel", 1962,artists.subList (2, 3), albums.get(2)));
        songs.add(new Song("4", "Maybe", "Psychedelic rock", 1969, artists.subList(3, 4), albums.get(3)));
        songs.add(new Song("5", "Thriller", "Pop", 1982, artists.subList(4, 5), albums.get(4)));
        songRepository.saveAll(songs);

    }
}
