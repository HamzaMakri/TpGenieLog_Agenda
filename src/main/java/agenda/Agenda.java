package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    
    
    private ArrayList<Event>listEvents;
    
    //constructeur

    public Agenda() {
        this.listEvents = new ArrayList<>() ;
    }
    
    
    public void addEvent(Event e) {
        // DONE : implémenter cette méthode
        listEvents.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        // DONE : implémenter cette méthode
        ArrayList<Event> allEventsInDay = new ArrayList<>();
        for ( Event e : listEvents){
            if (e.isInDay(day)){
                allEventsInDay.add(e);
            }
        }
        return allEventsInDay;
    }
}
