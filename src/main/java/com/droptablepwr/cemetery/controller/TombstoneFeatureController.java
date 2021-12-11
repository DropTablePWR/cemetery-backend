package com.droptablepwr.cemetery.controller;

import com.droptablepwr.cemetery.model.Feature;
import com.droptablepwr.cemetery.model.Tombstone;
import com.droptablepwr.cemetery.model.projection.TombstonesFeatureWriteModel;
import com.droptablepwr.cemetery.repository.FeatureRepository;
import com.droptablepwr.cemetery.repository.TombstoneRepository;
import com.droptablepwr.cemetery.repository.TombstonesFeatureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/cemetery/{cemeteryId}/tombstone/{tombstoneId}/feature")
public class TombstoneFeatureController {
    private final TombstonesFeatureRepository tombstonesFeatureRepository;
    private final TombstoneRepository tombstoneRepository;
    private final FeatureRepository featureRepository;

    public TombstoneFeatureController(TombstonesFeatureRepository tombstonesFeatureRepository, TombstoneRepository tombstoneRepository, FeatureRepository featureRepository) {
        this.tombstonesFeatureRepository = tombstonesFeatureRepository;
        this.tombstoneRepository = tombstoneRepository;
        this.featureRepository = featureRepository;
    }

    @GetMapping
    ResponseEntity<?> getAllTombstonesFeatures(@PathVariable Integer cemeteryId, @PathVariable Integer tombstoneId) {
        if (tombstoneRepository.existsByIdAndCemetery_Id(tombstoneId, cemeteryId)) {
            return ResponseEntity.ok(tombstonesFeatureRepository.getByTombstone_IdAndTombstone_Cemetery_Id(tombstoneId, cemeteryId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<?> addNewFeature(@PathVariable Integer cemeteryId, @PathVariable Integer tombstoneId, @RequestBody @Valid TombstonesFeatureWriteModel model) {
        Optional<Tombstone> tombstone = tombstoneRepository.findByIdAndCemetery_Id(tombstoneId, cemeteryId);
        Optional<Feature> feature = featureRepository.findById(model.getFeatureId());
        if (tombstone.isPresent() && feature.isPresent()) {
            if (tombstone.get().getFeatures().stream().anyMatch(tombstonesFeature -> Objects.equals(tombstonesFeature.getPlace(), model.getPlace())))
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(tombstonesFeatureRepository.save(model.toFeature(tombstone.get(), feature.get())));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{featureId}")
    ResponseEntity<?> removeTombstonesFeature(@PathVariable Integer cemeteryId, @PathVariable Integer tombstoneId, @PathVariable Integer featureId) {
        if (tombstonesFeatureRepository.existsByIdAndTombstone_IdAndTombstone_Cemetery_Id(featureId, tombstoneId, cemeteryId)) {
            tombstonesFeatureRepository.deleteById(featureId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
