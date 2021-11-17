package com.droptablepwr.cemetery.controller;

import com.droptablepwr.cemetery.model.Feature;
import com.droptablepwr.cemetery.repository.FeatureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeatureController {
    private final FeatureRepository featureRepository;

    public FeatureController(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @GetMapping("/feature")
    List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }
}
