package com.droptablepwr.cemetery.model;

import com.droptablepwr.cemetery.model.projection.GuestInfo;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "guests", indexes = {
        @Index(name = "Guests_tombstone_id_uindex", columnList = "tombstone_id", unique = true)
})
@Entity
public class Guest implements GuestInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "tombstone_id", nullable = false)
    private Tombstone tombstone;

    @Column(name = "first_name", nullable = false, length = 64)
    private String firstName;

    @Column(name = "last_name", length = 64)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "death_date")
    private LocalDate deathDate;

    public Guest() {
    }

    public Guest(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, Tombstone tombstone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.tombstone = tombstone;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Tombstone getTombstone() {
        return tombstone;
    }

    public void setTombstone(Tombstone tombstone) {
        this.tombstone = tombstone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
