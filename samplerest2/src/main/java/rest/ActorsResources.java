package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Actor;
import domain.Movie;
import domain.services.ActorService;

@Path("/actors")
public class ActorsResources {
	
private ActorService db = new ActorService();
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Actor actor) {
		db.add(actor);
		return Response.ok(actor.getId()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAll() {
		return db.getAll();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete (@PathParam("id") int id) {
		Actor result = db.getById(id);
		if (result ==null)
			return Response.status(404).build();
		db.deleteActor(result);
		return Response.ok("skasowano: " + result.getName()+ " " +result.getSurname() ).build();
	}
	
	
	@POST
	@Path("/{id}/movies")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMovie(@PathParam("id") int actorId, Movie movie) {
		Actor result = db.getById(actorId);
		if (result == null) {
			return Response.status(404).build();
		}
		if (result.getMovies() == null) {
			result.setMovies(new ArrayList<Movie>());
		}
		
		db.addMovie(result, movie);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{actorId}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getComments(@PathParam("actorId") int actorId) {
		Actor result = db.getById(actorId);
		if (result == null) {
			return null;
		}
		if (result.getMovies() == null) {
			result.setMovies(new ArrayList<Movie>());
		}
		return result.getMovies();
	}
	
	


}