package com.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Document(value = "road_detail")
public class RoadDetail {


    private String uuid;
    private String name;
    private String description;
    private String lifeCycle;
    private String lastUpdateDate;
    private String qn;
    private int pkStart;
    private int pkStop;
    private List<String> othersChildren;
    private List<Direction> directions;


}
