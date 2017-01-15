package com.javangarda.fantacalcio.titolari.application.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MatchEvent {
    private String team;
    private String player;
    private int minute;
    private MatchEventType type;
    private MatchEvent relation;
}
