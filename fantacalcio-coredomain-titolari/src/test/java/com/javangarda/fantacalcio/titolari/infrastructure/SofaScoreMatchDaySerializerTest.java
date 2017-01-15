package com.javangarda.fantacalcio.titolari.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javangarda.fantacalcio.integration.data.sofascore.SofaScoreLineups;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SofaScoreMatchDaySerializerTest {

    @Test
    public void foo() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("sofascore/lineups-finished-example1.json");
        String jsonContent = IOUtils.toString(is, "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        SofaScoreLineups lineups = mapper.readValue(jsonContent, SofaScoreLineups.class);
        assertNotNull(lineups);
    }
}