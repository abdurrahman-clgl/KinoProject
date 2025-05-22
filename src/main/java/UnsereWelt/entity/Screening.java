package UnsereWelt.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Screening {
    private Movie movie;
    private LocalDate date;
    private LocalTime startTime;
    private boolean available;

    public Screening(Movie movie, LocalDate date, LocalTime startTime, boolean available) {
        this.movie = movie;
        this.date = date;
        this.startTime = startTime;
        this.available = available;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
