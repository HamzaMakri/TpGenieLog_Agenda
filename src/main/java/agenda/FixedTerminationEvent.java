package agenda;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {

    private LocalDate terminationInclusive;
    private long numberOfOccurrences;

    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
         super(title, start, duration, frequency);
        // DONE : implémenter cette méthode
        this.terminationInclusive=terminationInclusive;
        this.numberOfOccurrences = getNumberOfOccurrences();
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        // DONE : implémenter cette méthode
        this.numberOfOccurrences=numberOfOccurrences;
        this.terminationInclusive = getTerminationDate();
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        // DONE : implémenter cette méthode
            switch (getFrequency()){
                case DAYS:
                    return LocalDate.from(getStart().plusDays(numberOfOccurrences-1));
                case WEEKS:
                    return LocalDate.from(getStart().plusWeeks(numberOfOccurrences-1));
                case YEARS:
                    return LocalDate.from(getStart().plusYears(numberOfOccurrences-1));
                default:
                    throw new IllegalStateException(" Invalid frequency : " + getFrequency() + " it must either be DAYS, WEEKS or YEARS");
            }
    }

    public long getNumberOfOccurrences() {
        // DONE : implémenter cette méthode
        switch (getFrequency()){
            case DAYS:
                return ChronoUnit.DAYS.between(getStart(), terminationInclusive.atStartOfDay()) + 1;
            case WEEKS:
                return ChronoUnit.WEEKS.between(getStart(), terminationInclusive.atStartOfDay()) + 1;
            case YEARS:
                return ChronoUnit.MONTHS.between(getStart(), terminationInclusive.atStartOfDay()) + 1;
            default:
                throw new IllegalStateException(" Invalid frequency : " + getFrequency() + " it must either be DAYS, WEEKS or YEARS");

        }
    }


    @Override
    public boolean isInDay(LocalDate aDay) {
        // DONE : implémenter cette méthode


        if (aDay.isAfter(getTerminationDate())){
            return false;
        }else{
            return super.isInDay(aDay);
        }
    }


}
