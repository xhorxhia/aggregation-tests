package com.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Section {

    private String uuid;
    private String name;
    private String description;
    private String lifeCycle;
    private String lastUpdateDate;
    private String qn;
    private String pkStart;
    private String pkStop;
    private List<String> othersChildren;
    private List<Lane> lanes;
}
