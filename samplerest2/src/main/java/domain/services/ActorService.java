package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;
import domain.Movie;

public class ActorService {
	private static List<Actor> db = new ArrayList<Actor>();
	private static int currentId = 0;
	
	public List<Actor> getAll() {
		return db;
	}
	
	public Actor getById(int id) {
		for (Actor actor : db) {
			if (actor.getId() == id) {
				return actor;
			}
		}
		return null;
	}
	
	public void add (Actor actor) {
		actor.setId(++currentId);
		db.add(actor);
	}
	
	public void update (Actor actor) {
		for (Actor act : db) {
			if (act.getId() == actor.getId()) {
				act.setName(actor.getName());
				act.setSurname(actor.getSurname());
			}
		}
	}
	
	public void deleteActor (Actor actor) {
		db.remove(actor);
	}
	
	public void addMovie (Actor actor, Movie movie) {
		
		MovieService dbMovies = new MovieService();
		
		for (Movie mov : dbMovies.getAll()) {
			if (mov.getId() == movie.getId()) {
				actor.getMovies().add(mov);

				if (mov.getActors() == null) {
					mov.setActors(new ArrayList<Integer>());
				}
			else	
				mov.getActors().add(actor.getId());
			}
		}
	}
}
