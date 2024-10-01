package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a009_retrofit.R;
import com.example.a009_retrofit.Service.MovieDetailListener;
import com.example.a009_retrofit.Service.MovieListener;
import com.example.a009_retrofit.Service.MovieService;
import com.example.a009_retrofit.json_mapper.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciar MovieService
        movieService = new MovieService();

        // Botón para obtener películas populares
        Button btnPopularMovies = findViewById(R.id.btnPopularMovies);
        btnPopularMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopularMovies();
            }
        });

        // Botón para buscar películas
        Button btnSearchMovies = findViewById(R.id.btnSearchMovie);
        btnSearchMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovies("titanic"); // Buscar la película "Titanic"
            }
        });

        // Botón para obtener detalles de una película
        Button btnMovieDetails = findViewById(R.id.btnMovieDetails);
        btnMovieDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovieDetails(550); // Obtener detalles de la película "Fight Club" (id 550)
            }
        });
    }

    // Método para obtener las películas populares
    private void getPopularMovies() {
        MovieService.getPopularMovies("TU_API_KEY", "es-ES", 1, new MovieListener() {
            @Override
            public void onSuccess(List<Movie> movies) {
                for (Movie movie : movies) {
                    Log.d("MainActivity", "Película: " + Movie.getTitle());
                }
                Toast.makeText(MainActivity.this, "Películas populares obtenidas correctamente.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para buscar películas
    private void searchMovies(String query) {
        MovieService.searchMovies("TU_API_KEY", "es-ES", query, 1, new MovieListener() {
            @Override
            public void onSuccess(List<Movie> movies) {
                if (movies != null && !movies.isEmpty()) {
                    for (Movie movie : movies) {
                        Log.d("MainActivity", "Película encontrada: " + Movie.getTitle());
                    }
                    Toast.makeText(MainActivity.this, "Películas encontradas.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No se encontraron películas.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para obtener detalles de una película específica
    private void getMovieDetails(int movieId) {
        MovieService.getMovieDetails(movieId, "TU_API_KEY", "es-ES", new MovieDetailListener() {
            @Override
            public void onSuccess(MovieDetail movieDetail) {
                Log.d("MainActivity", "Título: " + movieDetail.getTitle());
                Log.d("MainActivity", "Descripción: " + movieDetail.getOverview());
                Toast.makeText(MainActivity.this, "Detalles de la película obtenidos.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
