package data.project;

import java.util.List;

public interface ProjectListMapper {

	public int getTotalCount();
	public List<ProjectDTO> getAllProjects(String category,String state,String percent,String search);
	public List<ProjectDTO> allProjects();
	public List<ProjectDTO> endProjects();
	public List<ProjectDTO> newProjects();
	public List<ProjectDTO> popProjects();
	public List<ProjectDTO> bookedProjects();
}