package com.ashish.hopscotchproject.model.movielist;

/**
 * Created by amit.chauhan on 05-Sep-16.
 */
public class Movies {
    private String id;

    private String title;

    private Abridged_cast[] abridged_cast;

    private String synopsis;

    private String runtime;

    private Links links;

    private String year;

    private Release_dates release_dates;

    private String mpaa_rating;

    private Posters posters;

    private Ratings ratings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Abridged_cast[] getAbridged_cast() {
        return abridged_cast;
    }

    public void setAbridged_cast(Abridged_cast[] abridged_cast) {
        this.abridged_cast = abridged_cast;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Release_dates getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(Release_dates release_dates) {
        this.release_dates = release_dates;
    }

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public void setMpaa_rating(String mpaa_rating) {
        this.mpaa_rating = mpaa_rating;
    }

    public Posters getPosters() {
        return posters;
    }

    public void setPosters(Posters posters) {
        this.posters = posters;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", title = " + title + ", abridged_cast = " + abridged_cast + ", synopsis = " + synopsis + ", runtime = " + runtime + ", links = " + links + ", year = " + year + ", release_dates = " + release_dates + ", mpaa_rating = " + mpaa_rating + ", posters = " + posters + ", ratings = " + ratings + "]";
    }
}