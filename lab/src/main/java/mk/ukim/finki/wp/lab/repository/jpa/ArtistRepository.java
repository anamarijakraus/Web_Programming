package mk.ukim.finki.wp.lab.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.wp.lab.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
