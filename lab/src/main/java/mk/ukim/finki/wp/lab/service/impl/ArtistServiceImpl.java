package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    //Constructor based dependency injection,
    // sekogash koga se kreira instanca da se kreira dependency so repository za pristap do podatocite vo bazata

    public ArtistServiceImpl(ArtistRepository artistRepository) {

        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        if (artistOptional.isEmpty()) {
            throw new ArtistNotFoundException(id);
        }
        return artistOptional.get();
    }


}
