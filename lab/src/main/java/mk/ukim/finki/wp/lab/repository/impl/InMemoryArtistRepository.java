package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Springboot da znaj deka ova e adapter so koj se zemat podatoci od nekoj storage
@Repository
public class InMemoryArtistRepository {
    public List<Artist> findAll() {
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id) {
        return DataHolder.artists.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
    //optional go vrapuva artist
    //  dokolku ne najdi nishto vrakja empty optional
    // pri povik na funckijata i smestuvanje vo promenliva za da se zemi vrednosta se korsti najubo
    // findById(Long id).orElse(new Artist())
    //ili findById(Long id).orElseThrow()
    // ili prvo da se osigurame deeka ima neshto pa posle so .get()
}
