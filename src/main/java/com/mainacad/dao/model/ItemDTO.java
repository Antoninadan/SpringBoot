package com.mainacad.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@AllArgsConstructor
public class ItemDTO {
    private Integer id;
    private String name;
    private Integer price;

//    public ItemDTO(Integer id, String name, Integer price) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
}
