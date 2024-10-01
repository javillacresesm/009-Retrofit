package com.example.a009_retrofit.Service;

import com.example.a009_retrofit.json_mapper.Movie;

import java.util.List;

public interface MovieListener {
    void onSuccess(List<Movie> movies);
    void onFailure(String errorMessage);
}
