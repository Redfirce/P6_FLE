package std.fle._0x_controller.listManagement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fle.toolBox.ConfigurationFileReader;
import fle.toolBox.StreamListFilter;
import std.fle._00_general.SessionVariables;
import std.fle._05_slo.innerJoinSLO.ClimbingSiteCommentsSLO;
import std.fle._05_slo.innerJoinSLO.ClimbingSiteSLO;
import std.fle._05_slo.innerJoinSLO.MembersListSLO;
import std.fle._05_slo.innerJoinSLO.TopoListSLO;
import std.fle._05_slo.innerJoinSLO.TopoMineListSLO;
import std.fle._07_service.TopoListService.TopoListService;
import std.fle._07_service.climbingSiteCommentsListService.ClimbingSiteCommentsListService;
import std.fle._07_service.climbingSiteListService.ClimbingSiteListService;
import std.fle._07_service.memberListService.MemberListService;
import std.fle._0X_security.SecurityLevel;

@Service
public class ListGeneratorImplemented implements ListGenerator {

	@Autowired
	MemberListService memberListService;

	@Autowired
	ClimbingSiteListService climbingSiteListService;

	@Autowired
	ClimbingSiteCommentsListService climbingSiteCommentsListService;

	@Autowired
	TopoListService topoListService;

	private MembersListSLO members = new MembersListSLO();
	private ClimbingSiteSLO climbingSiteSLO = new ClimbingSiteSLO();
	private ClimbingSiteCommentsSLO climbingSiteCommentsSLO = new ClimbingSiteCommentsSLO();
	private TopoListSLO topoListSLO = new TopoListSLO();
	private TopoMineListSLO topoMineListSLO = new TopoMineListSLO();
	private ConfigurationFileReader config = new ConfigurationFileReader(
			"configuration/securitySettings/securitySettings.xml");
	private SessionVariables sessVar = new SessionVariables();

	@Override
	public List<MembersListSLO> membersList() {
		List<MembersListSLO> list = memberListService.getList();
		return list;
	}

	@Override
	public List<ClimbingSiteSLO> climbingSiteSLOs() {
		return climbingSiteListService.getList();
	}

	@Override
	public List<ClimbingSiteCommentsSLO> climbingSiteCommentsSLOs(Integer id) {
		return climbingSiteCommentsListService.getCommentsList(id);
	}

	@Override
	public LinkedHashMap<String, Object> getTopoSLOsLoggedOwnerExcludedList(HttpServletRequest request) {
		return map(topoSLOsLoggedOwnerExcluded(request), topoListSLO, "borrowMe");
	}

	private List<TopoListSLO> topoSLOsLoggedOwnerExcluded(HttpServletRequest request) {
		return StreamListFilter.getPredicateFilteredList(topoSLOs(), predicate(getLoggedUserPseudo(request)));
	}

	@Override
	public LinkedHashMap<String, Object> getTopoSLOList() {
		return map(topoSLOs(), topoListSLO, "borrowMe");
	}

	@Override
	public List<TopoListSLO> topoSLOs() {
		return topoListService.getAllTopoList();
	}
		

	private String getLoggedUserPseudo(HttpServletRequest request) {
		sessVar.setRequest(request);
		return sessVar.getPseudo();
	}

	private Predicate<TopoListSLO> predicate(String pseudo) {
		Predicate<TopoListSLO> predicate = o -> !(o.getOwnerPseudo().equals(pseudo));
		return predicate;
	}

	@Override
	public LinkedHashMap<String, Object> getMembersList() {
		List<MembersListSLO> list = new ArrayList<>(membersList());
		list.removeIf(o -> o.getPseudonyme().equals(config.getProperty("admin.pseudo")));
		return map(list, members, "memberDetails");
	}

	@Override
	public LinkedHashMap<String, Object> getClimbingSiteListShow() {
		return climbingSiteListGeneric("climbingSiteDetails");
	}

	@Override
	public LinkedHashMap<String, Object> getClimbingSiteListEdit() {
		return climbingSiteListGeneric("climbingSiteEdit");
	}

	private LinkedHashMap<String, Object> climbingSiteListGeneric(String editControllerURI) {
		List<ClimbingSiteSLO> list = new ArrayList<>(climbingSiteSLOs());
		return map(list, climbingSiteSLO, editControllerURI);
	}

	@Override
	public LinkedHashMap<String, Object> getclimbingSiteCommentsSLOList(Integer id, HttpServletRequest request) {
		sessVar.setRequest(request);
		String editComment = sessVar.getSecurityLevel() < SecurityLevel.USER.rank() ? "commentEdit" : "none";
		return map(climbingSiteCommentsSLOs(id), climbingSiteCommentsSLO, editComment);
	}
	
	@Override
	public LinkedHashMap<String, Object> getTopoMineSLOList(HttpServletRequest request) {		
		return map(topoMines(request), topoMineListSLO, "editTopo");
	}
	
	
	@Override
	public List<TopoMineListSLO> topoMines(HttpServletRequest request){
		return topoListService.getMineTopo(getLoggedUserAccountId(request));
	}
	
	private Integer getLoggedUserAccountId(HttpServletRequest request) {
		sessVar.setRequest(request);
		return sessVar.getAccountID();
	}

	private LinkedHashMap<String, Object> map(List<?> list, Object clazz, String editControllerURI) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("list", list);
		map.put("class", clazz);
		map.put("editControllerURI", editControllerURI);
		return map;
	}

	

}
