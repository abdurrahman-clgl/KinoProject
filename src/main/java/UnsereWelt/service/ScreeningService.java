package UnsereWelt.service;

import UnsereWelt.dto.movie.ScreeningDto;
import UnsereWelt.entity.Movie;
import UnsereWelt.entity.Screening;
import UnsereWelt.mapper.ScreeningMapper;
import UnsereWelt.repository.MovieRepository;
import UnsereWelt.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;

    public ScreeningService(ScreeningRepository screeningRepository, MovieRepository movieRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
    }

    public List<ScreeningDto> getAllScreenings() {
        return screeningRepository.findAll().stream()
                .map(ScreeningMapper::toDto).toList();
    }

    public ScreeningDto getScreeningById(Long id) {
        return ScreeningMapper.toDto(screeningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screening with ID " + id + " not found")));
    }

    public ScreeningDto addScreening(ScreeningDto dto) {
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie with ID " + dto.getMovieId() + " not found"));
        Screening screening = ScreeningMapper.toEntity(dto);
        screening.setMovie(movie);
        Screening saved = screeningRepository.save(screening);
        return ScreeningMapper.toDto(saved);
    }

    public ScreeningDto updateScreening(Long id, ScreeningDto dto) {

        Screening existing = screeningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screening with ID " + id + " not found"));
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie with ID " + dto.getMovieId() + " not found"));

        existing.setMovie(movie);
        existing.setDate(dto.getDate());
        existing.setStartTime(dto.getStartTime());
        existing.setAvailable(dto.isAvailable());
        Screening updated = screeningRepository.save(existing);

        return ScreeningMapper.toDto(updated);
    }

    public void deleteScreening(Long id) {
        Screening screening = screeningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screening with ID " + id + " not found"));

        screeningRepository.delete(screening);
    }


}
