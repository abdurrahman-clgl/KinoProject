package UnsereWelt.controller;


import UnsereWelt.dto.MovieDto;
import UnsereWelt.entity.Movie;
import UnsereWelt.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }




   /*

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/search/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        return movieService.getMoviesByTitle(title);
    }

    @GetMapping("/search/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }


   // @PostMapping
   // public Movie add

    */
//Mapper
    //MapStruct
    //ModellMapper


}
