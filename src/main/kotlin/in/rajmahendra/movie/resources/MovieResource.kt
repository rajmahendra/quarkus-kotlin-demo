package `in`.rajmahendra.movie.resources

import `in`.rajmahendra.movie.entity.Movie
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path( "/book")
@Produces (MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieResource {

    val movies: ArrayList<Movie> = ArrayList();

    @POST
    fun addMovie(movie: Movie) : Movie {
         movies.add(movie);
        return movie;
    }

    @GET
    fun getAllMovie() = movies

    @GET
    @Path("/id")
    fun getMovieById(id: Long) : Movie? {
        val movie:Movie? = movies.find{ it.id == id}
        return movie
    }

    @GET
    @Path("/title")
    fun getMovieByTitle(title: String) : Movie? {
        val movie:Movie? = movies.find{ it.title == title}
        return movie
    }


}