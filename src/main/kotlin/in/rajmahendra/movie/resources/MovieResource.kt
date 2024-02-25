package `in`.rajmahendra.movie.resources

import `in`.rajmahendra.movie.entity.Movie
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.logging.Logger

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieResource @Inject constructor (val log: Logger) {

 //   val log: Logger = Logger.getLogger(MovieResource::class.java)

    final val movies: ArrayList<Movie> = ArrayList();

    init {
        movies.add(Movie(id = 1, title = "Ponnien Selvan", released = 2022))
        movies.add(Movie(id = 2, title = "Ponnien Selvan 2", released = 2023))
    }

    @POST
    fun addMovie(movie: Movie): Movie {
        log.info("addMovie called")
        movies.add(movie);
        return movie;
    }

    @GET
    fun getAllMovie(): ArrayList<Movie> {
        log.info("getAllMovie Called")
        log.info("Movie list size : " + movies.size)
        return movies;
    }

    @GET
    @Path("/id/{id}")
    fun getMovieById(id: Long): Movie? {
        log.info("getMovieById called")
        val movie: Movie? = movies.find { it.id == id }
        log.info("Movie found in ID : $movie")
        return movie
    }

    @GET
    @Path("/title/{title}")
    fun getMovieByTitle(title: String): Movie? {
        log.info("getMovieByTitle called")
        val movie: Movie? = movies.find { it.title == title }
        return movie
    }


}