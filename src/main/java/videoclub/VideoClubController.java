package videoclub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class VideoClubController {

	public static final Logger logger = Logger.getLogger(VideoClubController.class);

	@Autowired
	DatabaseLoader db;

	/* Creacion de la vista inicial donde el usuario realiza el login */
	@RequestMapping("/")
	public ModelAndView index()	{
		return new ModelAndView("index");
	}

	/* El usuario envia nombre de usuario y contrasena, hay que comprobar si los datos coinciden */	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam String username, @RequestParam String password) {
		ModelAndView mv;

		// Si los datos coinciden
		if (db.comprobarUsuario(username, password)) {
			mv = new ModelAndView("redireccion_home");	//¿Con que fin se hizo esto?
		}	
		else {
			mv = new ModelAndView("index");
			mv.addObject("comprobacion", "Nombre de usuario o contraseña no válido");
		}
		return mv;		
	}


	/* Pagina principal donde el usuario puede realizar la busqueda. 
	 * En este momento el usuario ya esta loggeado */
	@RequestMapping("/home")
	public ModelAndView home()  {
		return new ModelAndView("home");
	}

	@RequestMapping("/search")
	public ModelAndView realizarBusqueda(@RequestParam String name)  {
		ModelAndView mv = new ModelAndView("search");

		List<Film> listFilm = db.buscarPelicula(name);

		if (listFilm.isEmpty())
			mv.addObject("no_result","No se encontraron coincidencias");
		else
			mv.addObject("listFilm", listFilm);

		return mv;
	}

	@RequestMapping("/ver")
	public ModelAndView verPelicula(@RequestParam long id) {

		logger.info("verPelicula()");
		if (db.comprobarPelicula(id)){
			logger.info("La pelicula esta en la BDD");
			String urlFilm = db.obtenerURL(id);
			logger.info("La URL de la pelicula es: "+urlFilm);
			return new ModelAndView("pelicula").addObject("url", urlFilm);
		}else{
			logger.info("La pelicula NO esta en la BDD");
		}
		return new ModelAndView("pelicula").addObject("url", "https://www.youtube.com/embed/ryUHqY2yx1g");
	}

	/*@RequestMapping("/ver")	//Funciona FORZANDO la URL de la pelicula
	public ModelAndView verPelicula(){
		return new ModelAndView("pelicula");
	}*/


	@RequestMapping("/logout")
	public String logout(){
		return "redirect:/";
	}

}
