package com.mainacad.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    @JsonProperty
    private Integer id;

    @JsonProperty("userId") // how in json
    private Integer userId;

    private Long creationTime;
    private String status;
    private String userLogin;
    private Integer sumItems;
}
