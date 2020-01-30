package com.mainacad.dao.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Integer id;
	private Integer itemId;
    private Integer cartId;
    private String itemName;
    private Integer itemPrice;
    private Integer amount;
}
