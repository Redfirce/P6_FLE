package std.fle._0x_controller.controllerClass.listController;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import fle.toolBox.controllerTools.listManagement.SetListManagementController;
import fle.toolBox.dataListDisplayerTools.displayer.DataListDisplay;

@Controller
public class DisplayListController implements SetListManagementController {

	

	

	@Autowired
	private DataListDisplay dataListDisplay;
	
	

	//@Autowired
	//DAOListGeneric dao;
	
//	@Autowired
//	AccesGranting granting;

	@Override
	public DataListDisplay controllerConfig() {
		return dataListDisplay;
	}

	
	private List<?> list;
	private Object clazz = null;
	private String editControllerURI = null;
	
	

    @GetMapping(value = "/04_listPage/listPage")
	@Override
	public ModelAndView initiatePage(ModelAndView model,HttpServletRequest request) {
		
		//List<?>list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) request.getAttribute("map");
		ListComponents components = new ListComponents((List<?>) map.get("list"), map.get("class"), (String) map.get("editControllerURI"));
		list = (List<?>) map.get("list");
		clazz = map.get("class");
		editControllerURI = (String) map.get("editControllerURI");		
		return controllerConfig().initiatePage((List<?>) map.get("list"),map.get("class"), "frontViewAddObject", "label"); //model; //list, clazz
	}
	
/*	@GetMapping(value = "/04_listPage/reload")
	public ModelAndView listReload(ModelAndView model) {
		list = (List<?>) model.getModelMap().get("list");
		return controllerConfig().initiatePage(list, clazz, "frontViewAddObject", "label");
	}
	
	
	
*/	

   
    
    
    
	@GetMapping(value = "04_listPage/frontViewAddObject")
	@Override
	public ModelAndView frontViewAddObject() {
		return controllerConfig().frontViewAddObject("04_listPage", "listPage", "sortList",
				"listRowsDisplayer", "selectedPage", "orderedPage", editControllerURI);
	}

	@GetMapping(value = "04_listPage/setDataToDisplay") // getMappingFolderName + setDataToDisplayHandlerName
	@Override
	public ModelAndView setDataToDisplay() {
		return controllerConfig().setDataToDisplay("frontViewAddObject");
	}

	@GetMapping(value = "04_listPage/sortList") // getMappingFolderName + sortListHandlerName
	@Override
	public ModelAndView sortList() {
		return controllerConfig().sortList("setDataToDisplay", "listPage");// "listPage"=jspname
	}

	@GetMapping(value = "04_listPage/listRowsDisplayer") // getMappingFolderName + rowsDisplayedHandlerName
	@Override
	public ModelAndView listRowsDisplayed() {
		return controllerConfig().listRowsDisplayed("setDataToDisplay", "frontViewAddObject");
	}

	@GetMapping(value = "04_listPage/selectedPage") // getMappingFolderName + selectPageHandlerName
	@Override
	public ModelAndView selectedPage() {
		return controllerConfig().selectedPage("setDataToDisplay");
	}

	@GetMapping(value = "04_listPage/orderedPage") // getMappingFolderName + orderListHandlerName
	@Override
	public ModelAndView orderedPage() {
		return controllerConfig().orderedPage("setDataToDisplay");
	}

}
