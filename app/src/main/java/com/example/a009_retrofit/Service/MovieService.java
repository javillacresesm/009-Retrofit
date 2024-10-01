package com.example.a009_retrofit.Service;

import android.util.Log;

import com.example.a009_retrofit.Retrofit.RetrofitClient;
import com.example.a009_retrofit.RetrofitApi.RetrofitApi;
import com.example.a009_retrofit.json_mapper.Movie;
import com.example.a009_retrofit.json_mapper.MovieDetail;
import com.example.a009_retrofit.json_mapper.MovieResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService {
    private RetrofitApi apiService;

    public MovieService() {
        // Crear una instancia de Retrofit y ApiService
        apiService = RetrofitClient.getRetrofitClient().create(RetrofitApi.class);
    }

    // Método para buscar películas
    public void searchMovies(String apiKey, String language, String query, int page, final MovieListener listener) {
        Call<MovieResponse> call = apiService.searchMovies(apiKey, language, query, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    listener.onSuccess(movies);
                } else {
                    listener.onFailure("Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.onFailure("Error en la llamada: " + t.getMessage());
                Log.e("MovieService", "Error: ", t);
            }
        });
    }

    // Método para obtener detalles de una película
    public void getMovieDetails(int movieId, String apiKey, String language, final MovieDetailListener listener) {
        Call<MovieDetail> call = apiService.getMovieDetails(movieId, apiKey, language);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieDetail movieDetail = response.body();
                    listener.onSuccess(movieDetail);
                } else {
                    listener.onFailure("Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                listener.onFailure("Error en la llamada: " + t.getMessage());
                Log.e("MovieService", "Error: ", t);
            }
        });
    }

    // Método para obtener películas populares
    public void getPopularMovies(String apiKey, String language, int page, final MovieListener listener) {
        Call<MovieResponse> call = apiService.getPopularMovies(apiKey, language, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    listener.onSuccess(movies);
                } else {
                    listener.onFailure("Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.onFailure("Error en la llamada: " + t.getMessage());
                Log.e("MovieService", "Error: ", t);
            }
        });
    }
}
