package com.javangarda.fantacalcio.titolari.infrastructure.port.adapter.integration.sofascore.service;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.MessageFormat;

@Component
public class SofaScoreJsonProvider {

    private static final String MATCHDAY_URL = "http://www.sofascore.com/event/{0}/json?_={1}";
    private static final String LINEUPS_URL = "http://www.sofascore.com/lineups/{0}/json?_={1}";

    public String provideMatchDayJson(String id) throws IOException{
        String url = provideMatchDayUrl(id);
        JSONObject jsonObject = getByUrl(url);
        return jsonObject.toString();
    }

    protected String provideMatchDayUrl(String id){
        String[] values = {id, System.currentTimeMillis()+""};
        return MessageFormat.format(MATCHDAY_URL, values);
    }

    protected String provideLineupsUrl(String id){
        String[] values = {id, System.currentTimeMillis()+""};
        return MessageFormat.format(LINEUPS_URL, values);
    }

    protected JSONObject getByUrl(String url) throws IOException {
        return new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
    }

}
