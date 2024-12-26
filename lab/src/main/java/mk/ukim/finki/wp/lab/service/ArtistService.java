package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.*;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> listArtists();

    Artist findById(Long id);
// Optional<Artist> findById(Long id);


}
