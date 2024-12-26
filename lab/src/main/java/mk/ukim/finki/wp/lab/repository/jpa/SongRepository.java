package mk.ukim.finki.wp.lab.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository <Song, Long> {
    List<Song> findAllByAlbum_Id(Long albumId);

   Optional<Song> findByTrackId (String trackId);
}
