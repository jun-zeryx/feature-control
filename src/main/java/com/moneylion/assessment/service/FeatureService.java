package com.moneylion.assessment.service;

import com.moneylion.assessment.dto.FeatureDTO;
import com.moneylion.assessment.exception.CommonErrorCode;
import com.moneylion.assessment.exception.CommonException;
import com.moneylion.assessment.model.FeaturePermission;
import com.moneylion.assessment.model.User;
import com.moneylion.assessment.repository.FeaturePermissionRepository;
import com.moneylion.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true)
public class FeatureService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeaturePermissionRepository featureRepository;

    public FeatureDTO checkFeatureEnabled(String email, String featureName) throws CommonException {
        FeatureDTO featureDTO = new FeatureDTO();

        User user = checkValidUser(email);

        FeaturePermission feature = featureRepository.findByUserAndFeature(user, featureName);

        if (feature == null) {
            featureDTO.setCanAccess(false);
            // throw new CommonException(CommonErrorCode.FEATURE_NOT_SET);
        } else {
            featureDTO.setCanAccess(feature.getEnabled());
        }

        return featureDTO;
    }

    @Transactional(readOnly = false)
    public FeatureDTO setFeature(FeatureDTO requestDTO) throws CommonException {
        FeatureDTO responseDTO = new FeatureDTO();

        validateSetFeatureRequest(requestDTO);

        User user = checkValidUser(requestDTO.getEmail());

        FeaturePermission feature = featureRepository.findByUserAndFeature(user, requestDTO.getFeatureName());

        if (feature == null) {
            feature = new FeaturePermission();
            feature.setFeature(requestDTO.getFeatureName());
            feature.setEnabled(requestDTO.getEnable());
            feature.setUser(user);

            featureRepository.save(feature);
        } else {
            if (feature.getEnabled().equals(requestDTO.getEnable())) {
                throw new CommonException(CommonErrorCode.UPDATE_FAILED);
            } else {
                feature.setEnabled(requestDTO.getEnable());
            }
        }

        return responseDTO;
    }

    private void validateSetFeatureRequest(FeatureDTO requestDTO) throws CommonException {
        checkRequired(requestDTO);
        checkRequired(requestDTO.getFeatureName());
        checkRequired(requestDTO.getEmail());
        checkRequired(requestDTO.getEnable());

    }

    private User checkValidUser(String email) throws CommonException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new CommonException(CommonErrorCode.USER_NOT_EXIST);
        }

        return user;
    }

    private Object checkRequired(final Object value) throws CommonException {
        if (value == null) {
            throw new CommonException(CommonErrorCode.MISSING_REQUIRED_FIELD);
        }
        return value;
    }

    private String checkRequired(final String value) throws CommonException {
        if (!StringUtils.hasText(value)) {
            throw new CommonException(CommonErrorCode.MISSING_REQUIRED_FIELD);
        }
        return value;
    }

}
