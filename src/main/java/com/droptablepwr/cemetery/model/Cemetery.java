package com.droptablepwr.cemetery.model;

import com.droptablepwr.cemetery.repository.CemeteryRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Set;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Table(name = "cemeteries", indexes = {
        @Index(name = "Cemeteries_name_uindex", columnList = "name", unique = true)
})
@Entity
public class Cemetery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Length(max = 64)
    @IsUnique
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @NotNull
    @Length(max = 200)
    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @NotNull
    @Column(name = "max_grid_x", nullable = false)
    private Integer maxGridX;

    @NotNull
    @Column(name = "max_grid_y", nullable = false)
    private Integer maxGridY;

    @NotNull
    @Column(name = "type", nullable = false)
    private Integer type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cemetery_id")
    private Set<CemeteriesForbiddenPosition> forbiddenPositions;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cemetery_id")
    private Set<Tombstone> tombstones;


    public Cemetery() {
    }

    public Cemetery(String name, String description, Integer maxGridX, Integer maxGridY, Integer type) {
        this.name = name;
        this.description = description;
        this.maxGridX = maxGridX;
        this.maxGridY = maxGridY;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMaxGridY() {
        return maxGridY;
    }

    public void setMaxGridY(Integer maxGridY) {
        this.maxGridY = maxGridY;
    }

    public Integer getMaxGridX() {
        return maxGridX;
    }

    public void setMaxGridX(Integer maxGridX) {
        this.maxGridX = maxGridX;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CemeteriesForbiddenPosition> getForbiddenPositions() {
        return forbiddenPositions;
    }

    public void setForbiddenPositions(Set<CemeteriesForbiddenPosition> forbiddenPositions) {
        this.forbiddenPositions = forbiddenPositions;
    }

    public Set<Tombstone> getTombstones() {
        return tombstones;
    }

    public void setTombstones(Set<Tombstone> tombstones) {
        this.tombstones = tombstones;
    }

    public void setAll(Cemetery newVal) {
        this.setDescription(newVal.getDescription());
        this.setName(newVal.getName());
        this.setType(newVal.getType());
        this.setMaxGridX(newVal.getMaxGridX());
        this.setMaxGridY(newVal.getMaxGridY());
        this.setForbiddenPositions(newVal.getForbiddenPositions());
    }


    @Target({FIELD})
    @Retention(RUNTIME)
    @Constraint(validatedBy = IsUnique.IsUniqueValidator.class)
    private @interface IsUnique {
        String message() default "Name is not unique";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        class IsUniqueValidator implements ConstraintValidator<IsUnique, String> {
            @Autowired
            CemeteryRepository cemeteryRepository;

            @Override
            public boolean isValid(String value, ConstraintValidatorContext context) {
                return !cemeteryRepository.existsByName(value);
            }
        }
    }

}
