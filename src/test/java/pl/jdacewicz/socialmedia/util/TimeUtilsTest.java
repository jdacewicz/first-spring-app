package pl.jdacewicz.socialmedia.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    LocalDateTime creationDateTime;
    LocalDateTime currentDateTime;
    @BeforeEach
    void setUp() {
        this.creationDateTime = LocalDateTime.of(1,1,1,0,0,0);
        this.currentDateTime = null;
    }

    @Test
    void ReturnsProperStringIf0MinutesDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime;

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("0 minutes" ,message);
    }
    @Test
    void ReturnsProperStringIf1MinuteDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusMinutes(1);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("1 minute" ,message);
    }

    @Test
    void ReturnsProperStringIf1HourDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusHours(1);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("1 hour" ,message);
    }

    @Test
    void ReturnsProperStringIf2HoursDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusHours(2);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("2 hours" ,message);
    }

    @Test
    void ReturnsProperStringIf1DayDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusDays(1);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("1 day" ,message);
    }

    @Test
    void ReturnsProperStringIf2DaysDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusDays(2);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("2 days" ,message);
    }

    @Test
    void ReturnsProperStringIf1MonthDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusMonths(1);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("1 month" ,message);
    }
    @Test
    void ReturnsProperStringIf2MonthsDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusMonths(2);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("2 months" ,message);
    }

    @Test
    void ReturnsProperStringIf1YearDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusYears(1);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("1 year" ,message);
    }

    @Test
    void ReturnsProperStringIf2YearsDifferenceBetweenDateTimesGiven() {
        currentDateTime = creationDateTime.plusYears(2);

        String message = TimeUtils.getElapsedTimeMessage(creationDateTime, currentDateTime);

        assertEquals("2 years" ,message);
    }
}