package com.example.lemongrass.movie;

import com.example.lemongrass.csvProcess.CsvRow;

import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Movie {
    String title;

    List<String> directors;
    List<String> actors;
    List<String> genre;
    List<String> keywords;

    int durationInMinute;

    Calendar publishDate;
    Calendar watchDate;

    float generalRate;
    float yourRate;

    URL imdbURL;

    // Poster poster; // reserved

    public Movie () {
    }

    public Movie (List<String> movieInfo) {

    }

    public Movie (CsvRow csvRow) {
        List<String> movieInfo = csvRow.getFields();

        /* field assign */
        // String
        this.title = movieInfo.get(1);

        // string List
        this.genre = Arrays.asList(movieInfo.get(3).split("[,]"));
        this.keywords  = Arrays.asList(movieInfo.get(4).split("[,]"));
        this.directors = Arrays.asList(movieInfo.get(5).split("[,]"));
        this.actors = Arrays.asList(movieInfo.get(6).split("[,]"));

        // URL
        try {
            this.imdbURL = new URL(movieInfo.get(8));
        }
        catch(Exception ex) {
            this.imdbURL = null;
        }

        // duration
        String[] durationDetail = movieInfo.get(2).split("[:]");
        this.durationInMinute = Integer.parseInt(durationDetail[0]) * 60 + Integer.parseInt(durationDetail[1]);

        // calendars
        this.watchDate = Calendar.getInstance();
        watchDate.setTime(new SimpleDateFormat("yyyy/MM/ddTHH:mm:ss").parse(movieInfo.get(0), new ParsePosition(0)));

        this.publishDate = Calendar.getInstance();
        publishDate.setTime(new SimpleDateFormat("yyyy/MM/dd").parse(movieInfo.get(7), new ParsePosition(0)));

        // rating (random for now)
        int max = 5;
        int min = 0;
        this.generalRate = (float)Math.floor(Math.random() * (max - min + 1) + min);
        this.yourRate = (float)Math.floor(Math.random() * (max - min + 1) + min);
    }
}
