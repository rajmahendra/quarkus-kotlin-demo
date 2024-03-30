package kotlinchennai.movie.resources

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/api/hello")
class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello RESTEasy"
}