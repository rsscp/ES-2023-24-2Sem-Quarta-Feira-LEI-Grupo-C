package org.project;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeUtils {
    public static DayOfWeek determineDayOfWeekFromFile(String dayString) throws IllegalArgumentException {
        switch(dayString) {
            case "Seg", "MONDAY":
                return DayOfWeek.MONDAY;
            case "Ter", "TUESDAY":
                return DayOfWeek.TUESDAY;
            case "Qua", "WEDNESDAY":
                return DayOfWeek.WEDNESDAY;
            case "Qui", "THURSDAY":
                return DayOfWeek.THURSDAY;
            case "Sex", "FRIDAY":
                return DayOfWeek.FRIDAY;
            case "Sáb", "SATURDAY":
                return DayOfWeek.SATURDAY;
            case "Dom", "SUNDAY":
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + dayString);
        }
    }

    public static DayOfWeek determineDayOfWeek(String dayString) throws IllegalArgumentException {
        switch(dayString) {
            case "MONDAY":
                return DayOfWeek.MONDAY;
            case "TUESDAY":
                return DayOfWeek.TUESDAY;
            case "THURSDAY":
                return DayOfWeek.THURSDAY;
            case "WEDNESDAY":
                return DayOfWeek.WEDNESDAY;
            case "FRIDAY":
                return DayOfWeek.FRIDAY;
            case "SATURDAY":
                return DayOfWeek.SATURDAY;
            case "SUNDAY":
                return DayOfWeek.SUNDAY;
            default:
                return null;
        }
    }

    public static LocalDate determineStartOfWeek(LocalDate day) {
        return day.minusDays(day.getDayOfWeek().getValue()-1);
    }

    public static LocalTime determineLocalTime(String timeString) throws IndexOutOfBoundsException {
        String[] timeParts = timeString.split(":");
        return LocalTime.of(
                Integer.parseInt(timeParts[0]),
                Integer.parseInt(timeParts[1])
                // Integer.parseInt(timeParts[2])
        );
    }

    public static LocalDate determineLocalDate(String dateString) {
        String[] timeParts = dateString.split("/");
        return LocalDate.of(
                Integer.parseInt(timeParts[2]),
                Integer.parseInt(timeParts[1]),
                Integer.parseInt(timeParts[0])
        );
    }

    public static LocalDate determineLocalDate(String dateString, String strSplit) {
        String[] timeParts = dateString.split(strSplit);

        if (strSplit.equals("-")) {
            return LocalDate.of(
                    Integer.parseInt(timeParts[0]),
                    Integer.parseInt(timeParts[1]),
                    Integer.parseInt(timeParts[2])
            );
        }
        return LocalDate.of(
                Integer.parseInt(timeParts[2]),
                Integer.parseInt(timeParts[1]),
                Integer.parseInt(timeParts[0])
        );
    }
}
