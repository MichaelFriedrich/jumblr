package com.tumblr.jumblr.exceptions;

import org.scribe.model.Response;

import java.util.Map;

public class LimitException extends RuntimeException {
    protected Map<String, String> headers;
    protected int hourRemaining;
    protected int dayRemaining;
    protected int dayReset;
    protected int hourReset;
    protected int hourLimit;
    protected int dayLimit;

    public LimitException(Response response) {
        super(response.getBody());
        headers = response.getHeaders();
        hourRemaining = convert(response.getHeader("X-Ratelimit-Perhour-Remaining"));
        dayRemaining = convert(response.getHeader("X-Ratelimit-Perday-Remaining"));
        dayReset = convert(response.getHeader("X-Ratelimit-Perday-Reset"));
        hourReset = convert(response.getHeader("X-Ratelimit-Perhour-Reset"));
        hourLimit = convert(response.getHeader("X-Ratelimit-Perhour-Limit"));
        dayLimit = convert(response.getHeader("X-Ratelimit-Perday-Limit"));
    }
    private int convert(String s) {
        try {
            return Integer.valueOf(s);
        }
        catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getHourRemaining() {
        return hourRemaining;
    }

    public int getDayRemaining() {
        return dayRemaining;
    }

    public int getDayReset() {
        return dayReset;
    }

    public int getHourReset() {
        return hourReset;
    }

    public int getHourLimit() {
        return hourLimit;
    }

    public int getDayLimit() {
        return dayLimit;
    }
}
