package com.neeraj.routeplanner;

import java.time.LocalDateTime;

/** represents the Start and End time for a Stop*/
public class TimeWindow {
    private LocalDateTime windowStart;
    private LocalDateTime windowEnd;

    public LocalDateTime getWindowEnd() {
        return windowEnd;
    }

    public LocalDateTime getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(LocalDateTime windowStart) {
        this.windowStart = windowStart;
    }

    public void setWindowEnd(LocalDateTime windowEnd) {
        this.windowEnd = windowEnd;
    }
}
