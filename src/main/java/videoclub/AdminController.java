package videoclub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminController {

	@Autowired
	DatabaseLoader db;
	
	@RequestMapping("/admin") 
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("administracion_user_v2");
		mv.addObject("userList", db.findAllUser());

		return mv;
	}
	
	@RequestMapping("/admin/crear-usuario")
	public String crearUsuario (@RequestParam String username, @RequestParam String correo, 
			@RequestParam String password, @RequestParam String roles, RedirectAttributes redirectAttributes) {
		
		/*ModelAndView mv = new ModelAndView("administracion_user_v2");
		
		db.crearUsuario(username, correo, password);		
		mv.addObject("userList", db.findAllUser());
		
		return mv;*/
		if (db.crearUsuario(username, correo, password))
			redirectAttributes.addFlashAttribute("biencreacion",true);
		else redirectAttributes.addFlashAttribute("errorcreacion",true);

		
		return "redirect:/admin";
	}

	
	@RequestMapping("/admin/borrar-usuario")
	public String borrarUsuario (@RequestParam long id, RedirectAttributes redirectAttributes) {
	/*	ModelAndView mv = new ModelAndView("administracion_user_v2");
		db.borrarUsuario(id);
		
		mv.addObject("userList", db.findAllUser());
		mv.addObject("deletesuccess",true);
		
		return mv;*/	
		String usuarioBorrado = db.borrarUsuario(id);
		redirectAttributes.addFlashAttribute("deletesuccess",true);
		redirectAttributes.addFlashAttribute("usuarioborrado", usuarioBorrado);
		
		return "redirect:/admin";
	}
	
	@RequestMapping("/admin/borrar-usuarios-seleccionados")
	public String borrarUsuarioSeleccionados (@RequestParam long [] id, RedirectAttributes redirectAttributes) {
		
		for (int i=0; i<id.length;i++)
			db.borrarUsuario(id[i]);
		
		return "redirect:/admin";
	}
	
	@RequestMapping("/admin/modificar-usuario")
	public String modificarUsuario (@RequestParam User user) {

		db.modificarUsuario();
		
		return "redirect:/admin";
	}
}
