package com.mainacad.dao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
