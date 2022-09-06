package data.project;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import data.member.MemberMapper;

@Controller
@RequiredArgsConstructor
public class ProjectController {


	private final ProjectService service;
	private final ProjectMapper mapper;
	private final MemberMapper memberMapper;



	@GetMapping("/project/start")//프로젝트 올리기
	public String start (HttpSession session) {
		//로그인 상태 확인
		String loginok = (String)session.getAttribute("loginok"); //인터셉터를 이용해서 애플리케이션 전체에 수행될 로직 짜기
		if(loginok == null) {//로그인이 아닌 경우 로그인페이지로 이동
			return "redirect:/login/main";
		}
		return "/project_create/projectStart";

	}


	@PostMapping("/project/insert")
	public String inert(@ModelAttribute ProjectDTO dto,HttpSession session) {
		String id = (String)session.getAttribute("id");
		String loginok = (String) session.getAttribute("loginok");
		if(loginok == null) {
			return "redirect:/login/main";
		}
		String name = memberMapper.getName(id);
		dto.setName(name);
		dto.setId(id);
		service.insertCategory(dto);
		return "redirect:editor?idx=" + service.getMAxIdx();
	}

	@GetMapping("/project/editor")
	public ModelAndView editor(@RequestParam String idx) {
		ModelAndView mview = new ModelAndView();
		ProjectDTO dto = service.getData(idx);
		mview.addObject("dto", dto);
		mview.addObject("idx", idx);
		mview.setViewName("/project_create/editor");

		return mview;
	}

	@GetMapping("/project/editor2")
	public ModelAndView editor2(@RequestParam String idx, @RequestParam String key) {
		ModelAndView mview = new ModelAndView();
		ProjectDTO dto = service.getData(idx);
		mview.addObject("dto", dto);
		mview.addObject("idx", idx);
		mview.addObject("key", key);
		mview.setViewName("/project_create/editor2");

		return mview;
	}

	@ResponseBody
	@PostMapping("/project/storyUpdate")
	public void storyUpdate(@ModelAttribute ProjectDTO dto,
							@RequestParam Long idx,
							@RequestParam String project_goal,
							@RequestParam String project_budget,
							@RequestParam String project_schedule,
							@RequestParam String project_team_intro,
							@RequestParam String project_present_intro) {

		dto.setIdx(idx);
		dto.setProject_goal(project_goal);
		dto.setProject_budget(project_budget);
		dto.setProject_schedule(project_schedule);
		dto.setProject_team_intro(project_team_intro);
		dto.setProject_present_intro(project_present_intro);
		service.storyUpdate(dto);

	}




	@PostMapping("/project/defaultUpdate")
	public String defaultUpdate(@ModelAttribute ProjectDTO dto,HttpServletRequest request) {

		//썸네일 이미지 등록 시

		//웹 서버 내에 업로드될 폴더를 만들어 업로드하기 위해 필요한 상대경로
		String path = request.getSession().getServletContext().getRealPath("/thumbnail_image");
		//getServletContext() : 웹 어플리케이션이 설치되어 있는 경로를 리턴해줌
		//getRealPath() : ServletContext의 getRealPath는 웹어플리케이션이 실행된 곳. 즉 설치된 곳의 경로를 찾음

		//String path = "/home/ec2-user/backup/thumbnail_image";

		//저장시 저장된 날짜와 시간,분,초를 파일명뒤에 확인용으로 넣기 위해
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");


		if (dto.getUpload().getOriginalFilename().equals("")) {//만약 이미지를 아무것도 업로드 하지 않은 상태면
			dto.setThumbnail(null);
		} else {//이미지가 업로드 된 상태라면
			String uploadfile = service.getData(Long.toString(dto.getIdx())).getThumbnail();
			//file객체 생성
			File file1 = new File(path + "/" + uploadfile);
			file1.delete();//기존 이미지는 삭제하기

			//저장될 이미지명
			String thumbnail = sdf.format(new Date()) + "_" + dto.getUpload().getOriginalFilename();
			dto.setThumbnail(thumbnail);

			//업로드
			try {
				dto.getUpload().transferTo(new File(path + "/" + thumbnail));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		service.defaultUpdate(dto);
		return "redirect:editor?idx=" + dto.getIdx();
	}


	@ResponseBody
	@PostMapping("/project/fundingUpdate")
	public void fundingUpdate(@ModelAttribute ProjectDTO dto,
							  @RequestParam Long idx,
							  @RequestParam int target_amount,
							  @RequestParam java.sql.Date start_date,
							  @RequestParam String time_start,
							  @RequestParam java.sql.Date end_date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date today = java.sql.Date.valueOf(sdf.format(start_date));
		java.sql.Date end_date2 = java.sql.Date.valueOf(sdf.format(end_date));

		dto.setIdx(idx);
		dto.setTarget_amount(target_amount);
		dto.setStart_date(today);
		dto.setEnd_date(end_date2);
		dto.setTime_start(time_start);
		service.fundingUpdate(dto);
	}

	@ResponseBody
	@PostMapping("/project/policyUpdate")
	public void policyUpdate(@ModelAttribute ProjectDTO dto,
							 @RequestParam int idx,
							 @RequestParam String anticipated_problem) {
		dto.setAnticipated_problem(anticipated_problem);
		service.policyUpdate(dto);
	}

	@ResponseBody
	@PostMapping("/project/rewardUpdate")
	public int insertPresent(@ModelAttribute PresentDTO pstdto,
							 @RequestParam int idx,
							 @RequestParam String present_name,
							 @RequestParam String option,
							 @RequestParam String price
	) {
		pstdto.setIdx(idx);
		pstdto.setPrice(price);
		if(option == "") {
			pstdto.setPresent_option(null);
		}else if(option.endsWith(",") == true){
			option = option.replaceAll(",$", "");
			pstdto.setPresent_option(option);
		}else {
			pstdto.setPresent_option(option);
		}
		service.insertPresent(pstdto);
		int number = service.getMaxNum();
		return number;
	}

	@ResponseBody
	@PostMapping("/project/presentDelect")
	public void delectPresent(@RequestParam int num) {
		service.deletePresent(num);
	}

	@ResponseBody
	@GetMapping("/project/presentList")
	public List<PresentDTO> getPresentData(int idx) {
		return service.getPresentData(idx);
	}

	@ResponseBody
	@GetMapping("/project/getData")
	public ProjectDTO getData(String idx) {
		return service.getData(idx);
	}

	@ResponseBody
	@PostMapping("/project/progressUpdata")
	public void progressUpdata(ProjectDTO dto, @RequestParam Long idx, @RequestParam String audit) {
		dto.setIdx(idx);
		dto.setAudit(audit);
		service.progressUpdata(dto);
	}
}