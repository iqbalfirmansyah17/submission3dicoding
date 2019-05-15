package com.dicoding.iqbalfirmansyah.mysubmission3.Model;

import org.json.JSONArray;
import org.json.JSONObject;

public class MovieItems {

    public int id;
    public String title, overview, poster, popularity, releaseDate;

    public MovieItems(JSONObject object) {
//        try {
//            JSONArray jsonArray = object.getJSONArray("results");
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
//            String title = jsonObject.getString("title");
//            String overview = jsonObject.getString("overview");
//            String poster = jsonObject.getString("poster_path");
//            this.title = title;
//            this.overview = overview;
//            this.poster = poster;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
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
