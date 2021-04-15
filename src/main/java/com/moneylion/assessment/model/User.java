package com.moneylion.assessment.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String email;
}
