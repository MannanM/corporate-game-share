package com.mannanlive.model.library;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mannanlive.entity.GameState;
import com.mannanlive.model.ResourceSummary;
import com.mannanlive.model.game.GameData;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class LibraryGameAttributes {
    private GameData game;
    private ResourceSummary owner;
    private ResourceSummary borrower;
    @NotNull
    private GameState state;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @ApiModelProperty(readOnly = true, example = "2014-09-13T19:00:00Z", position = 3)
    private LocalDateTime created;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @ApiModelProperty(readOnly = true, example = "2014-09-13T19:00:00Z", position = 4)
    private LocalDateTime removed;

    public GameData getGame() {
        return game;
    }

    public void setGame(GameData game) {
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

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getRemoved() {
        return removed;
    }

    public void setRemoved(LocalDateTime removed) {
        this.removed = removed;
    }
}
