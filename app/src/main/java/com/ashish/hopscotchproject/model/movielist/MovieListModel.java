/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

package com.ashish.hopscotchproject.model.movielist;

public class MovieListModel {
    private String total;

    private Movies[] movies;

    private Links links;

    private String link_template;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Movies[] getMovies() {
        return movies;
    }

    public void setMovies(Movies[] movies) {
        this.movies = movies;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getLink_template() {
        return link_template;
    }

    public void setLink_template(String link_template) {
        this.link_template = link_template;
    }

    @Override
    public String toString() {
        return "ClassPojo [total = " + total + ", movies = " + movies + ", links = " + links + ", link_template = " + link_template + "]";
    }

}