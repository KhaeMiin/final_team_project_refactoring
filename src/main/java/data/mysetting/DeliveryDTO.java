package data.mysetting;

import lombok.Getter;
import lombok.Setter;

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
