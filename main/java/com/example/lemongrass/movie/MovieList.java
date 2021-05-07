package com.example.lemongrass.movie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

enum statisticsType {
    // start from int 0
    CALENDAR,
    GENRE,
    RATING
}

public class MovieList {
    // Calender key should be in format yyyy-mm
    // movieList: upper limit maybe 1024?
    private HashMap<Calendar, List<Movie>> movieList;
    // private HashMap<Genre, List<Movie>> movieList_by_genre;
    // private HashMap<Rating, List<Movie>> movieList_by_rating;

    public void insertMovie(Movie _movie) {
        Calendar calendarKey = Calendar.getInstance();
        calendarKey.set(_movie.watchDate.YEAR, _movie.watchDate.MONTH, 0);

        if (!movieList.containsKey(calendarKey)) {
            movieList.put(calendarKey, new ArrayList<>());
        }

        movieList.get(calendarKey).add(_movie);
        movieList.get(calendarKey).sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie movie_1, Movie movie_2) {
                return (movie_1.watchDate.compareTo((movie_2.watchDate)));
            }
        });
    }

    /** returns the required statistics in a List **/
    public List<Float> getStatistics(Calendar startMonth, Calendar endMonth, int statisticsType) {
        switch (statisticsType) {
            case 0:  // CALENDAR
                return calendarStatistics(startMonth, endMonth);
            case 1: // GENRE
                return genreStatistics(startMonth, endMonth);
            case 2: // RATING
                return ratingStatistics(startMonth, endMonth);
            }

        return new ArrayList<>();
    }

    private List<Float> calendarStatistics(Calendar startMonth, Calendar endMonth) {
        List<Float> statisticsRes = new ArrayList<Float>();

        while (startMonth.compareTo(endMonth) > 0) {

        }

        return statisticsRes;
    }

    private List<Float> genreStatistics(Calendar startMonth, Calendar endMonth) {
        List<Float> statisticsRes = new ArrayList<Float>();



        return statisticsRes;
    }

    private List<Float> ratingStatistics(Calendar startMonth, Calendar endMonth) {
        List<Float> statisticsRes = new ArrayList<Float>();



        return statisticsRes;
    }
}
