package videoclub;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class DatabaseLoader {

	public static final Logger logger = Logger.getLogger(DatabaseLoader.class);

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private UserRepository userRepository;


	/* Buscar peliculas que contenga el string pasado como parametro */
	public List<Film> buscarPelicula (String patron) {
		return filmRepository.findByNameContaining(patron);
	}


	public boolean comprobarPelicula (long id){
		
		Iterable<Film> flm = filmRepository.findAll();
		for(Film film : flm){
			if(film.getId() == id)
				return true;
		}
		
		return false;
		
		/*if (filmRepository.findById(id)){
			logger.info("comprobarPelicula() TRUE");
			return true;
		}else{
			logger.info("comprobarPelicula() FALSE");
			return false;
		}*/
	}

	public String obtenerURL(long id){

		String f="";
		Iterable<Film> flm = filmRepository.findAll();
		for (Film film : flm) {
			if(film.getId()==id)
				return film.getUrlcontent();
		}
		return f;
	}


	public Iterable<User> findAllUser () {
		return userRepository.findAll();
	}

	public boolean comprobarUsuario (String username, String password) {		

		Iterable<User> all = userRepository.findAll();
		for (User user : all) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return true;
		}
		return false;
	}

	public boolean crearUsuario (String username, String correo, String password) {

		User us = new User(username,correo,password);
		boolean ret = false;

		if (userRepository.findByUsername(username).isEmpty()) {
			userRepository.save(us);
			ret = true;
		}

		return ret;
	}

	public String borrarUsuario (long id) {
		List<User> ret = userRepository.findById(id);

		userRepository.delete(id);
		return ret.iterator().next().getUsername();
	}

	public void modificarUsuario() {

	}

	@PostConstruct
	private void initDatabase() {

		/*
		 * Usuarios
		 */
		userRepository.save(new User("admin","admin@gmail.com","admin"));
		userRepository.save(new User("admin2","admin@gmail.com","admin"));

		Iterable<User> all = userRepository.findAll();
		for (User user : all) {
			System.out.println(user.toString());
		}

		/*
		 * Peliculas
		 */
		filmRepository.save(new Film("Enigma","https://www.youtube.com/embed/TNh-SR_l3-8","Enigma...","2001",
				"Michael Apted","Dougray Scott, Kate Winslet,...", "http://es.web.img3.acsta.net/pictures/14/12/30/13/20/432349.jpg", "4"));
		filmRepository.save(new Film("Piratas de Silicon Valley","https://www.youtube.com/embed/Ue5_zdt3zes","Piratas de Silicon Valley","1999",
				"Maraton Burocráta","Noah Wyle, Anthony Michael Hall,...", "http://4.bp.blogspot.com/-wwrd3LpfksA/T3CIP9L5IUI/AAAAAAAAAAg/AQK8UFDSZZM/s1600/piratas-silicon-valley.jpg", "4"));
		filmRepository.save(new Film("hossssssssssssal","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));
		filmRepository.save(new Film("a a","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));
		filmRepository.save(new Film("la vida","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));

		Iterable<Film> flm = filmRepository.findAll();
		for (Film film : flm) {
			System.out.println(film.toString());
		}

	}

	/*	@PostConstruct
	private void initDatabase() {
		filmRepository.save(new Film("hoal","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));
		filmRepository.save(new Film("hosvvvl","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));
		filmRepository.save(new Film("hossssssssssssal","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));
		filmRepository.save(new Film("a a","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));
		filmRepository.save(new Film("la vida","urlcontent","description","year",
				"director","actors", "urlcover", "valoration"));
	}
	 */	
}
