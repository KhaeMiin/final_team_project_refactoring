package data.project;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {


	private final ProjectMapper mapper;

	@Transactional
	public void insertCategory(ProjectDTO dto) {
		mapper.insertCategory(dto);
	}
	
	public int getMAxIdx() {
		return mapper.getMAxIdx();
	}

	@Transactional
	public void storyUpdate(ProjectDTO dto) {
		mapper.storyUpdate(dto);
	}

	@Transactional
	public void defaultUpdate(ProjectDTO dto) {
		mapper.defaultUpdate(dto);
	}

	@Transactional
	public void fundingUpdate(ProjectDTO dto) {
		mapper.fundingUpdate(dto);
	}

	@Transactional
	public void policyUpdate(ProjectDTO dto) {
		mapper.policyUpdate(dto);
	}
	
	public ProjectDTO getData(String idx) {
		return mapper.getData(idx);
	}

	@Transactional
	public void insertPresent(PresentDTO pstdto) {
		mapper.insertPresent(pstdto);
	}
	
	public int getMaxNum() {
		return mapper.getMaxNum();
	}

	@Transactional
	public void deletePresent(int num) {
		mapper.deletePresent(num);
	}
	
	public List<PresentDTO> getPresentData(int idx){
		return mapper.getPresentData(idx);
	}

	@Transactional
	public void progressUpdata(ProjectDTO dto) {
		mapper.progressUpdata(dto);
	}
	
}
