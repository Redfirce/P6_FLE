package std.fle._02_dto.modelsDTO.climbingSiteDTO;

import fle.toolBox.classType.DTO;

public class ClimbingSiteCommentsEditDTO extends DTO{

	private Integer id;

	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
