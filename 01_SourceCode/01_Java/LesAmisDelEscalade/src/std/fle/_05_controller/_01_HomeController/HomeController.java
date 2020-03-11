package std.fle._05_controller._01_HomeController;

import java.util.LinkedHashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fle.toolBox.filesManagement.PropertiesToHashMap;
import fle.toolBox.filesManagement.PropertyFile;
import fle.toolBox.logger.Log4J2;
import std.fle.Config;

@Controller
public class HomeController {
	
	private Log4J2<HomeController> log = new Log4J2<HomeController>(this);
	
	@GetMapping(value ="/index")
	public ModelAndView indexDisplay(ModelAndView model) {
		//TODO 2-Check Log 
		log.log().info("in homeController");
		model.setViewName("01_home/index");				
		model.addObject("connexionName", "menuNavBarConnexion.nameToManagInFilterhandler");
		model.addObject("iFrameSource", "'test/iframeTest.html'");
		model.addObject("adminOptionHide", "adminOption.hide");
		model.addObject("adminHide", "'adminOnly'");
		System.out.println("homeControllerIndex");
		return model;

	}
}
