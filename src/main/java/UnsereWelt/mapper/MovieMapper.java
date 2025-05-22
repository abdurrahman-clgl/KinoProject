package UnsereWelt.mapper;

import UnsereWelt.dto.MovieDto;
import UnsereWelt.entity.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieDto dto) {
       Movie movie = new Movie();
       movie.setTitle(dto.getTitle());
       movie.setDescription(dto.getDescription());
       movie.setGenre(dto.getGenre());
       movie.setReleaseDate(dto.getReleaseDate());
       movie.setAgeRating(dto.getAgeRating());
       movie.setLanguage(dto.getLanguage());
       movie.setImageUrl(dto.getImageUrl());
       return movie;
    }


    public static MovieDto toDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setGenre(movie.getGenre());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setAgeRating(movie.getAgeRating());
        dto.setLanguage(movie.getLanguage());
        dto.setImageUrl(movie.getImageUrl());
        return dto;
    }

}
