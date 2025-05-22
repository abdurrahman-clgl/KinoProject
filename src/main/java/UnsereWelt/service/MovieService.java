package UnsereWelt.service;

import UnsereWelt.entity.Movie;
import UnsereWelt.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found"));
    }


    public List<Movie> getMoviesByGenre(String genre) {
        List<Movie> filtered = movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre)).toList();
        if (filtered.isEmpty()) {
            throw new RuntimeException("No movies found for genre " + genre);

        }
        return filtered;

    }
    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> filtered = movieRepository.findAll().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
        if (filtered.isEmpty()) {
            throw new RuntimeException("No movies found for title " + title);
        }
        return filtered;
    }

    //Admin Method

    public Movie addMovie(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new RuntimeException("Title must not be empty");
        }
        if (movie.getReleaseDate() == null) {
            throw new RuntimeException("Release date must not be null");
        }
        boolean exists = movieRepository.findAll().stream().anyMatch(existingMovie -> existingMovie.getTitle().equals(movie.getTitle()));
        if (exists) {
            throw new RuntimeException("Movie with title " + movie.getTitle() + " already exists");
        }
        return movieRepository.save(movie);
    }



    //Admin Method
    public Movie updateMovie(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new RuntimeException("Title must not be empty");
        }
        if (movie.getReleaseDate() == null) {
            throw new RuntimeException("Release date must not be null");
        }
        Movie existing = movieRepository.findById(movie.getId()).orElseThrow(()
                -> new RuntimeException("Movie with ID " + movie.getId() + " not found"));

        existing.setTitle(movie.getTitle());
        existing.setDescription(movie.getDescription());
        existing.setReleaseDate(movie.getReleaseDate());
        existing.setGenre(movie.getGenre());
        existing.setLanguage(movie.getLanguage());
        existing.setImageUrl(movie.getImageUrl());
        existing.setAgeRating(movie.getAgeRating());


        return movieRepository.save(existing);
    }


    //Admin Method
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found"));

        movieRepository.delete(movie);
    }




    //Business-Logik


}
