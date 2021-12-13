package agenda;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    
        private ChronoUnit frequency;
        public ArrayList<LocalDate> exceptions;

    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        // DONE : implémenter cette méthode
        this.frequency = frequency;
        this.exceptions = new ArrayList<>();
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        // DONE : implémenter cette méthode
        exceptions.add(date);
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        // DONE : implémenter cette méthode
        return this.frequency;
    }

    @Override
    public boolean isInDay(LocalDate aDay) {
        // DONE : implémenter cette méthode

        if (exceptions.contains(aDay)){
            return false;
        } else if (aDay.isEqual(ChronoLocalDate.from(getStart()))){
            return true;
        } else if (aDay.isAfter(ChronoLocalDate.from(getStart()))) {
            LocalDateTime testing_date = getStart();
            while (testing_date.isBefore(ChronoLocalDateTime.from(aDay.plusDays(1).atStartOfDay()))) {
                if (aDay.isEqual(ChronoLocalDate.from(testing_date))) {
                    return true;
                }
                switch (getFrequency()) {
                    case DAYS:
                        testing_date = testing_date.plusDays(1);
                        break;
                    case WEEKS:
                        testing_date = testing_date.plusWeeks(1);
                        break;
                    case YEARS:
                        testing_date = testing_date.plusYears(1);
                        break;
                }
            }
        }
        return false;
    }


}
