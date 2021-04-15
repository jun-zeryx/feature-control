package com.moneylion.assessment.repository;

import com.moneylion.assessment.model.FeaturePermission;
import com.moneylion.assessment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface FeaturePermissionRepository extends CrudRepository<FeaturePermission, Long> {
    FeaturePermission findByUserAndFeature(User user, String feature);
}
