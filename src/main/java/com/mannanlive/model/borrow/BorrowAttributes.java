package com.mannanlive.model.borrow;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mannanlive.entity.BorrowState;
import com.mannanlive.model.ResourceSummary;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BorrowAttributes {
    @ApiModelProperty(example = "PENDING")
    private BorrowState state;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(example = "2014-09-13", position = 1)
    private LocalDate startDate;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(example = "2014-10-01", position = 2)
    private LocalDate endDate;

    @ApiModelProperty(position = 3)
    private ResourceSummary game;

    @ApiModelProperty(position = 4)
    private ResourceSummary owner;

    @ApiModelProperty(position = 5)
    private ResourceSummary borrower;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @ApiModelProperty(readOnly = true, example = "2014-09-13T19:00:00Z", position = 6)
    private LocalDateTime requested;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @ApiModelProperty(readOnly = true, example = "2014-09-13T19:00:00Z", position = 7)
    private LocalDateTime completed;

    public BorrowState getState() {
        return state;
    }

    public void setState(BorrowState state) {
        this.state = state;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ResourceSummary getGame() {
        return game;
    }

    public void setGame(ResourceSummary game) {
        this.game = game;
    }

    public ResourceSummary getOwner() {
        return owner;
    }

    public void setOwner(ResourceSummary owner) {
        this.owner = owner;
    }

    public ResourceSummary getBorrower() {
        return borrower;
    }

    public void setBorrower(ResourceSummary borrower) {
        this.borrower = borrower;
    }

    public LocalDateTime getRequested() {
        return requested;
    }

    public void setRequested(LocalDateTime requested) {
        this.requested = requested;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }
}
