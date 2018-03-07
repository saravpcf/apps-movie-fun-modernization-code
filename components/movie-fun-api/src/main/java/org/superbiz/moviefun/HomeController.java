package org.superbiz.moviefun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.albumsapi.AlbumFixtures;
import org.superbiz.moviefun.albumsapi.AlbumInfo;
import org.superbiz.moviefun.albumsapi.AlbumsClient;
import org.superbiz.moviefun.moviesapi.MovieFixtures;
import org.superbiz.moviefun.moviesapi.MovieInfo;
import org.superbiz.moviefun.moviesapi.MoviesClient;

import java.util.Map;

@Controller
public class HomeController {

    private final AlbumsClient albumsClient;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;
    private final MoviesClient moviesClient;
    private Logger logger = LoggerFactory.getLogger(HomeController.class);


    public HomeController(AlbumsClient albumsClient, MovieFixtures movieFixtures, AlbumFixtures albumFixtures, MoviesClient moviesClient) {
        this.albumsClient = albumsClient;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
        this.moviesClient = moviesClient;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesClient.addMovie(movie);
        }

        for (AlbumInfo album : albumFixtures.load()) {
            logger.info("ALBUM INFO : " + album.getArtist());
            albumsClient.addAlbum(album);
        }

        model.put("movies", moviesClient.getMovies());
        model.put("albums", albumsClient.getAlbums());

        return "setup";
    }
}
