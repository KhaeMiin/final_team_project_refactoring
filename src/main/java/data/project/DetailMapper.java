package data.project;

import java.util.HashMap;
import java.util.List;


import data.dto.MemberDTO;
import data.mysetting.DeliveryDTO;

public interface DetailMapper {
	public ProjectDTO getData(int idx);
	public String getPaymentDate(int idx);
	
	public MemberDTO getCreateFile(String id); //프로필사진, introdoce
	
	public DeliveryDTO getAddr(String id);
	public void setHp(MemberDTO dto);
	public void setEmail(MemberDTO dto);
	public void insertDelivery(DeliveryDTO ddto);
	public int getLikeCheck(HashMap<String, Object> map);
	public void insertLikeProject(HashMap<String, Object> map);
	public void deleteLikeProject(HashMap<String, Object> map);
	public List<PresentDTO> getPresentData(int idx);
	public int getSupportCheck(HashMap<String, Object> map);
}
