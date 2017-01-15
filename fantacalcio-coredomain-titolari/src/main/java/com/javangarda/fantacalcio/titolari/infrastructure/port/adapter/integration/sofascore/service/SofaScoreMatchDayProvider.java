package com.javangarda.fantacalcio.titolari.infrastructure.port.adapter.integration.sofascore.service;

import com.javangarda.fantacalcio.integration.data.sofascore.SofaScoreMatchDay;
import com.javangarda.fantacalcio.titolari.application.data.MatchDay;
import com.javangarda.fantacalcio.titolari.application.event.MatchDayProvidingException;
import com.javangarda.fantacalcio.titolari.application.internal.MatchDayProvider;
import com.javangarda.fantacalcio.titolari.infrastructure.port.adapter.integration.sofascore.mapping.SofaScoreSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SofaScoreMatchDayProvider implements MatchDayProvider{

    @Autowired
    private SofaScoreSerializer serializer;
    @Autowired
    private SofaScoreJsonProvider jsonProvider;

    @Override
    public MatchDay provide(String id) throws MatchDayProvidingException {
        try {
            String json = jsonProvider.provideMatchDayJson(id);
            SofaScoreMatchDay ssMatchDay = serializer.createMatchDay(json);
        } catch (Exception e){
            throw new MatchDayProvidingException(id);
        }
        return null;
    }


}
