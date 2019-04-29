package com.dicoding.iqbalfirmansyah.mysubmission3.Model;

import org.json.JSONObject;

public class MovieItems {

    public int id, poster;
    public String title, overview, popularity, releaseDate;

    public MovieItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getJSONArray("results").getJSONObject(0).getString("title");
            String overview = object.getJSONArray("results").getJSONObject(0).getString("overview");
            String poster = object.getJSONArray("results").getJSONObject(0).getString("poster_path");
            this.id = id;
            this.title = title;
            this.overview = overview;
            this.poster = Integer.parseInt(poster);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
