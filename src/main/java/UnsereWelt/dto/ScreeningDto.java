package UnsereWelt.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningDto {
    private String movieTitle;
    private LocalDate date;
    private LocalTime startTime;
    private boolean available;

    public ScreeningDto(String movieTitle, LocalDate date, LocalTime startTime, boolean available) {
        this.movieTitle = movieTitle;
        this.date = date;
        this.startTime = startTime;
        this.available = available;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
