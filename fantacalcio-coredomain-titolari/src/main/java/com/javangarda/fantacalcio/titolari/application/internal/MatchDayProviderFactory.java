package com.javangarda.fantacalcio.titolari.application.internal;

public interface MatchDayProviderFactory {
    MatchDayProvider getProviderForMatch(String id);
}
