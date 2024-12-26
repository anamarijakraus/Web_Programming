package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException{
    public AlbumNotFoundException(Long albumId) {
        super(String.format("Album with id: %d was not found", albumId));
    }
}