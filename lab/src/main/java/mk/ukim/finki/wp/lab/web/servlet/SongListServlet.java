package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import mk.ukim.finki.wp.lab.model.Song;


import java.io.IOException;
import java.util.List;


@WebServlet(name = "SongListServlet", urlPatterns = "/listSongs")
public class SongListServlet extends HttpServlet {
    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;

    public SongListServlet(SongService songService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        List<Song> songs = songService.listSongs();
        context.setVariable("songs", songs);



        springTemplateEngine.process("listSongs.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String trackId = req.getParameter("trackId");

        if (trackId == null || trackId.isEmpty()) {
            resp.sendRedirect("/listSongs");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("trackId", trackId);

        resp.sendRedirect("/artist");
    }
}
