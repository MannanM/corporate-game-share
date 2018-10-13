package com.mannanlive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity(name = "library")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"owner_id", "game_id"})} )
public class LibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_seq_gen")
    @SequenceGenerator(name = "library_seq_gen", sequenceName = "library_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    public UserEntity owner;

    @ManyToOne
    private UserEntity borrower;

    @ManyToOne(optional = false)
    public GameEntity game;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public GameState state;

    @Column(nullable = false)
    public LocalDateTime created;
    public LocalDateTime removed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public LocalDateTime getRemoved() {
        return removed;
    }

    public void setRemoved(LocalDateTime removed) {
        this.removed = removed;
    }

    public UserEntity getBorrower() {
        return borrower;
    }

    public void setBorrower(UserEntity borrower) {
        this.borrower = borrower;
    }
}
