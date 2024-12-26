package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import mk.ukim.finki.wp.lab.service.SongService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    private final AlbumService albumService;


    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }


    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("songs", songService.listSongs());
        model.addAttribute("error", error);
        return "listSongs";
    }


    @PostMapping("/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam Long albumId) {

        try {
            songService.editSong(songId, title, trackId, genre, Integer.parseInt(releaseYear), albumId);
        }
        catch (Exception e){
            return "add-song";
        }
        return "redirect:/songs";
    }
    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        List<Album> allAlbums = this.albumService.findAll();
        Song song = this.songService.findBySongId(id);
        model.addAttribute("editSong", song);
        model.addAttribute("allAlbums", allAlbums);

        return "add-song";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model) {
        model.addAttribute("allAlbums", albumService.findAll());
        return "add-song";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(@RequestParam() String title,
                           @RequestParam() String trackId,
                           @RequestParam() String genre,
                           @RequestParam() Integer releaseYear,
                           @RequestParam() Long albumId) {

        try {
            songService.addNewSong(title, trackId, genre, releaseYear, albumId);
        } catch (Exception e) {
            return "redirect:/add";
        }
        return "redirect:/songs";
    }



    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id) {
        if (id==null){
            return "redirect:/songs";
        }
        Song deletedSong= songService.deleteSong(id);
        return "redirect:/songs";
    }
}





