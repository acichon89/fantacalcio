package com.javangarda.fantacalcio.titolari.application.event;

public class MatchDayProvidingException extends Exception {
    public MatchDayProvidingException(String id){
        super("Cannot provide MatchDay with id="+id);
    }
}
