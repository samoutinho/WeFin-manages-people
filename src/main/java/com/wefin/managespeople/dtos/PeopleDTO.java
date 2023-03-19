package com.wefin.managespeople.dtos;

import lombok.Data;

@Data
public class PeopleDTO {
    private Long id;
    private String name;
    private String identifier;
    private String identifierType;
}
