package com.moneylion.assessment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FeaturePermission {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    private String feature;

    @NonNull
    private Boolean enabled;
}
