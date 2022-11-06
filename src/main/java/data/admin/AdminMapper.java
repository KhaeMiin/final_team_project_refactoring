package data.admin;

import java.util.List;


import data.dto.MemberDTO1;
import data.project.ProjectDTO;

public interface AdminMapper {
	
	public List<ProjectDTO> getProjectList(int start, int perpage);
	public int getTotalCount();
	public void updateAuditAprvl(ProjectDTO pdto);
	public void updateAuditRefusal(ProjectDTO pdto);
	public String getAuditCount();
	
	public int getTotalMemberCount();
	public List<MemberDTO1> getMemberList(int start, int perpage);
	public void deleteMember(String num);
	

}
