package com.moneylion.assessment.controller;

import com.moneylion.assessment.dto.FeatureDTO;
import com.moneylion.assessment.exception.CommonErrorCode;
import com.moneylion.assessment.exception.CommonException;
import com.moneylion.assessment.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeatureController {

    @Autowired
    FeatureService featureService;

    @RequestMapping(value = {"/feature"}, method = RequestMethod.GET)
    public FeatureDTO checkFeature(@RequestParam final String email, @RequestParam final String featureName) throws CommonException {
        return featureService.checkFeatureEnabled(email, featureName);
    }

    @RequestMapping(value = {"/feature"}, method = RequestMethod.POST)
    public FeatureDTO setFeature(@RequestBody FeatureDTO requestDTO) throws CommonException {
        return featureService.setFeature(requestDTO);
    }

}
