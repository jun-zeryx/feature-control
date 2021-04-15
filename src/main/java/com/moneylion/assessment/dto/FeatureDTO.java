package com.moneylion.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeatureDTO {
    private Boolean canAccess;
    private String featureName;
    private String email;
    private Boolean enable;
}
