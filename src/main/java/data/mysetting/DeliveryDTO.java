package data.mysetting;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("ddto")
@Getter @Setter
public class DeliveryDTO {
	private int num;
	private String id; //userId로 변경해야함1
	private String name;
	private String addr;
	private String addr2;
	private String hp;
	private int pin;
}
