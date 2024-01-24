package com.streaming.videostreaming.service;

import com.streaming.videostreaming.util.Range;

public class RangeCalculator {
    private RangeCalculator() {

    }

    public static Range parseHttpRangeString(String httpRangeString, int defaultChunkSize) {
        if (httpRangeString == null) {
            return Range.builder().start(0).end(defaultChunkSize).build();
        }
        int dashIndex = httpRangeString.indexOf("-");
        long startRange = Long.parseLong(httpRangeString.substring(6, dashIndex));
        String endRangeString = httpRangeString.substring(dashIndex + 1);
        if (endRangeString.isEmpty()) {
            return Range.builder().start(startRange).end(startRange + defaultChunkSize).build();
        }
        long endRange = Long.parseLong(endRangeString);
        return Range.builder().start(startRange).end(endRange).build();
    }
}
