package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/artist-form")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService)
    {
        this.artistService = artistService;
        this.songService = songService;
    }

    @PostMapping
    public String getAddArtistPage(@RequestParam (required = false) String trackId, Model model) {
        if (trackId == null || trackId.isEmpty())
        {
            return "listSongs";
        }
        List<Artist> artists = this.artistService.listArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("trackId", trackId);

        return "artistsList";
    }

    @PostMapping("/add/artist")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String addArtist(@RequestParam String trackId, @RequestParam Long artistId) {
        if (trackId == null || trackId.isEmpty())
        {
            return "redirect: /songs";
        }

        if (artistId == null)
        {
            return "redirect:/artist-form";
        }

        Song song = songService.findByTrackId(trackId);
        Artist artist = artistService.findById(artistId);

        songService.addArtistToSong(artist, song);

        return String.format("redirect:/song-details?trackId=%s", trackId);
    }



}
