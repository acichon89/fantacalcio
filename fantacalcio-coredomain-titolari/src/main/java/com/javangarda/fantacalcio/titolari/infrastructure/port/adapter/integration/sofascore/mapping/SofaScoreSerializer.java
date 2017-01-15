package com.javangarda.fantacalcio.titolari.infrastructure.port.adapter.integration.sofascore.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javangarda.fantacalcio.integration.data.sofascore.SofaScoreLineups;
import com.javangarda.fantacalcio.integration.data.sofascore.SofaScoreMatchDay;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SofaScoreSerializer {

    public SofaScoreMatchDay createMatchDay(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, SofaScoreMatchDay.class);
    }

    public SofaScoreLineups createLineups(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, SofaScoreLineups.class);
    }
}
