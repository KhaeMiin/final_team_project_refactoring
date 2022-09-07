package data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Present {

    @Id @GeneratedValue
    @Column(name = "present_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product; //하나의 상품에는 여러 선물을 가질 수 있다.

    private String present_name; //선물 이름
    private String present_option; //선물 옵션
    private int price; //가격

    protected Present(Product product, String present_name, String present_option, int price) {
        this.product = product;
        this.present_name = present_name;
        this.present_option = present_option;
        this.price = price;
    }


}
