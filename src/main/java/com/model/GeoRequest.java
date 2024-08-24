package com.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class GeoRequest {

    private String roadId;
    private String directionId;
    private String sectionId;
}
