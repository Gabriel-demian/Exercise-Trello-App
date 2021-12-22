package com.proy.excercise.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Label {

    private String type;
    private String tittle;
    private String description;
    private String category;

}
