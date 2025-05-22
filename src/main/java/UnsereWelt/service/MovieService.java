package UnsereWelt.service;

import UnsereWelt.dto.MovieDto;
import UnsereWelt.entity.Movie;
import UnsereWelt.mapper.MovieMapper;
import UnsereWelt.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public List<MovieDto> getAllMovies() {
    return this.movieRepository.findAll().stream()
            .map(MovieMapper::toDto).toList();

    }


     public MovieDto getMovieById(Long id) {
        return MovieMapper.toDto(movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found")));
     }


    public List<MovieDto> getMoviesByGenre(String genre) {
        List<MovieDto> filtered = movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .map(MovieMapper::toDto).toList();

        if (filtered.isEmpty()) {
            throw new RuntimeException("No movies found for genre " + genre);

        }
        return filtered;

    }
    public List<MovieDto> getMoviesByTitle(String title) {
        List<MovieDto> filtered = movieRepository.findAll().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .map(MovieMapper::toDto).toList();

        if (filtered.isEmpty()) {
            throw new RuntimeException("No movies found for title " + title);
        }
        return filtered;
    }

    //Admin Method

    public MovieDto addMovie(MovieDto dto) {
        // 1. Validierung auf DTO-Ebene
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new RuntimeException("Title must not be empty");
        }

        if (dto.getReleaseDate() == null) {
            throw new RuntimeException("Release date must not be null");
        }

        boolean exists = movieRepository.findAll().stream()
                .anyMatch(m -> m.getTitle().equalsIgnoreCase(dto.getTitle()));

        if (exists) {
            throw new RuntimeException("Movie with title " + dto.getTitle() + " already exists");
        }
        Movie movieToSave = MovieMapper.toEntity(dto);
        Movie savedMovie = movieRepository.save(movieToSave);


        return MovieMapper.toDto(savedMovie);
    }

    //Admin Method
    public MovieDto updateMovie(Long id, MovieDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new RuntimeException("Title must not be empty");
        }

        if (dto.getReleaseDate() == null) {
            throw new RuntimeException("Release date must not be null");
        }

        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found"));


        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setReleaseDate(dto.getReleaseDate());
        existing.setGenre(dto.getGenre());
        existing.setLanguage(dto.getLanguage());
        existing.setImageUrl(dto.getImageUrl());
        existing.setAgeRating(dto.getAgeRating());

        Movie updated = movieRepository.save(existing);

        return MovieMapper.toDto(updated);
    }



    //Admin Method
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with ID " + id + " not found"));

        movieRepository.delete(movie);
    }




    //Business-Logik


}
