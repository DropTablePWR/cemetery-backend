package com.droptablepwr.cemetery.controller;


import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.model.Tombstone;
import com.droptablepwr.cemetery.model.projection.TombstoneInfo;
import com.droptablepwr.cemetery.model.projection.TombstoneWriteModel;
import com.droptablepwr.cemetery.repository.CemeteryRepository;
import com.droptablepwr.cemetery.repository.TombstoneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/cemetery/{cemeteryId}/tombstone")
public class TombstoneController {
    private final CemeteryRepository cemeteryRepository;
    private final TombstoneRepository tombstoneRepository;

    public TombstoneController(CemeteryRepository cemeteryRepository, TombstoneRepository tombstoneRepository) {
        this.cemeteryRepository = cemeteryRepository;
        this.tombstoneRepository = tombstoneRepository;
    }

    @GetMapping
    ResponseEntity<?> getAllTombstones(@PathVariable("cemeteryId") Integer cemeteryId) {
        if (!cemeteryRepository.existsById(cemeteryId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tombstoneRepository.findBy(TombstoneInfo.class));
    }

    @GetMapping("/{tombstoneId}")
    ResponseEntity<?> getSelectedTombstone(@PathVariable("cemeteryId") Integer cemeteryId, @PathVariable("tombstoneId") Integer tombstoneId) {
        if (cemeteryRepository.existsById(cemeteryId))
            return tombstoneRepository.findById(tombstoneId, Tombstone.class).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{tombstoneId}")
    ResponseEntity<?> deleteSelectedTombstone(@PathVariable("cemeteryId") Integer cemeteryId, @PathVariable("tombstoneId") Integer tombstoneId) {
        if (tombstoneRepository.existsByIdAndCemetery_Id(tombstoneId, cemeteryId)) {
            tombstoneRepository.deleteById(tombstoneId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<?> insertTombstone(@PathVariable("cemeteryId") Integer cemeteryId, @RequestBody @Valid TombstoneWriteModel writeModel) {
        Optional<Cemetery> cemetery = cemeteryRepository.findById(cemeteryId);
        if (cemetery.isPresent()) {
            if (tombstoneRepository.existsByCemeteryAndGridXAndGridY(cemetery.get(), writeModel.getGridX(), writeModel.getGridY())
                    || writeModel.getGridX() < 0 || writeModel.getGridY() < 0 || writeModel.getGridX() >= cemetery.get().getMaxGridX() || writeModel.getGridY() >= cemetery.get().getMaxGridY()) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(tombstoneRepository.save(writeModel.toTombstone(cemetery.get())));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
