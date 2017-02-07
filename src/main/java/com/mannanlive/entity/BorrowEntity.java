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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "borrow")
public class BorrowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_seq_gen")
    @SequenceGenerator(name = "borrow_seq_gen", sequenceName = "borrow_id_seq")
    private Long id;

    @ManyToOne(optional = false)
    private UserEntity requester;

    @ManyToOne(optional = false)
    public LibraryEntity library;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public BorrowState state;

    @Column(nullable = false)
    public LocalDate start;

    @Column(nullable = false)
    public LocalDate finish;

    @Column(nullable = false)
    public LocalDateTime created;

    @Column
    public LocalDateTime completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibraryEntity getLibrary() {
        return library;
    }

    public void setLibrary(LibraryEntity library) {
        this.library = library;
    }

    public BorrowState getState() {
        return state;
    }

    public void setState(BorrowState state) {
        this.state = state;
    }

    public UserEntity getRequester() {
        return requester;
    }

    public void setRequester(UserEntity requester) {
        this.requester = requester;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getFinish() {
        return finish;
    }
}
