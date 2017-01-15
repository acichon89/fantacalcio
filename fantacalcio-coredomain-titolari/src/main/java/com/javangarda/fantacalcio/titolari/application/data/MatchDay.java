package com.javangarda.fantacalcio.titolari.application.data;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class MatchDay {

    private MatchDayStatus status;
    private DateTime dateTime;
    private String hostTeam;
    private String awayTeam;
    private String hostFormation;
    private String awayFormation;
    private int hostScore;
    private int awayScore;
    private List<String> hostTeamPlayers = new ArrayList<>();
    private List<String> awayTeamPlayers = new ArrayList<>();
    private Set<MatchEvent> events;
    private Set<String> hostTeamReserves;
    private Set<String> awayTeamReserves;
    int currentMinute;
}
