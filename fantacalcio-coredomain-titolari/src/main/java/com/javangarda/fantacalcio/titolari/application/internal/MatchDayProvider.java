package com.javangarda.fantacalcio.titolari.application.internal;

import com.javangarda.fantacalcio.titolari.application.data.MatchDay;
import com.javangarda.fantacalcio.titolari.application.event.MatchDayProvidingException;

public interface MatchDayProvider {

    MatchDay provide(String id) throws MatchDayProvidingException;
}
