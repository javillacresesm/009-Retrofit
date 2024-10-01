package com.example.a009_retrofit.Service;

import com.example.a009_retrofit.json_mapper.MovieDetail;

public interface MovieDetailListener {
    void onSuccess(MovieDetail movieDetail);
    void onFailure(String errorMessage);
}
