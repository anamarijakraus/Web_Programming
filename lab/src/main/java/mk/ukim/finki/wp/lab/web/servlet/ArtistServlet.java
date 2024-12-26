package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.SongNotFoundException;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {

    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;

    public ArtistServlet(ArtistService artistService, SongService songService, SpringTemplateEngine springTemplateEngine) {

        this.artistService = artistService;
        this.songService = songService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        HttpSession session = req.getSession();
        Object trackId = session.getAttribute("trackId");

        if (trackId == null || trackId.toString().isEmpty()) {
            resp.sendRedirect("/listSongs");
            return;
        }


        List<Artist> artists = artistService.listArtists();
        context.setVariable("artists", artists);
        context.setVariable("trackId", trackId);

        springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String artistIdString = req.getParameter("artistId");
        if (artistIdString == null || artistIdString.isEmpty()){
            resp.sendRedirect("/artist");
            return;
        }

        long artistId = Long.parseLong(artistIdString);
        Artist artist;

        try {
            artist = artistService.findById(artistId);
        } catch (ArtistNotFoundException | IllegalArgumentException e) {
            artist = null;
        }

        if (artist == null) {
            resp.sendRedirect("/artist");
            return;
        }

        String trackId = (String) req.getSession().getAttribute("trackId");

        Song song;
        try {
            song = songService.findByTrackId(trackId);
        } catch (SongNotFoundException e) {
            song = null;
        }

        if (song == null) {
            resp.sendRedirect("/listSongs");
            return;
        }

        songService.addArtistToSong(artist, song);

        resp.sendRedirect(String.format("/songDetails?trackId=%s", trackId));
    }
}


