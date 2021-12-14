package agenda;

import java.time.*;
import java.time.chrono.ChronoLocalDate;

public class Event {

    /**
     * The myTitle of this event
     */
    private final String myTitle;
    
    /**
     * The starting time of the event
     */
    private final LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private final Duration myDuration;


    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        // DONE : implémenter cette méthode
        LocalDateTime end = myStart.plus(myDuration);
        return (    ( aDay.isBefore(ChronoLocalDate.from(end)) && aDay.isAfter(ChronoLocalDate.from(myStart)) ) || aDay.isEqual(ChronoLocalDate.from(myStart))  || aDay.isEqual(ChronoLocalDate.from(end))        );
    }
   
    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

    @Override
    public String toString(){
        return "Event => " + getTitle() + "\n\tBeginning : " + getStart().toString() + "\n\tDuration : " +  getDuration().toString() ;
    }

}
