package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        // TODO : implémenter cette méthode
        ArrayList<Event> allEventsInDay = new ArrayList<>();
        for ( Event e : listEvents){
            if (e.isInDay(day)){
                allEventsInDay.add(e);
            }
        }
        return allEventsInDay;
    }

    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {

        List<Event> eventList = new ArrayList<>();
        for (Event e : this.listEvents) {
            if (e.getTitle().contains(title)){
                eventList.add(e);
            }
        }
        return eventList;
    }

    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event e) {

        List<LocalDate> daysOfEvent = new ArrayList<>();
        for (int i = 0; i <= ChronoUnit.DAYS.between(e.getStart(), e.getStart().plus(e.getDuration())); i++){
            daysOfEvent.add(LocalDate.from(e.getStart().plusDays(i)));
        }


        List<Event> eventsAtDaysOfE = new ArrayList<>();

        for (LocalDate day : daysOfEvent) {
            eventsAtDaysOfE.addAll(eventsInDay(day));
        }

        for (Event current_event : eventsAtDaysOfE) {  // eventsAtDaysOfE = la liste des evenement ayant lieu le même jour que l'evenement

            if (current_event.getStart().toLocalTime().isAfter(current_event.getStart().plus(current_event.getDuration()).toLocalTime()) && current_event.getDuration().compareTo(Duration.ofDays(1)) > 0   ){ // si un evenement empiete sur le jour suivant et qu'il ne dure pas plus d'un jour
                if (!(current_event.getStart().toLocalTime().isAfter(e.getStart().plus(e.getDuration()).toLocalTime())) || !(current_event.getStart().plus(current_event.getDuration()).toLocalTime().isBefore(e.getStart().toLocalTime()))){ // si l'heure de debut de notre evenement n'est pas apres celle de fin de e OU que l'heure de fin de notre evemenement n'est pas avant celle de debut de e, alors e est pendant notre evenement donc l'agenda n'est pas dispo
                    return false;
                }
            }else{
                if (!(current_event.getStart().toLocalTime().isAfter(e.getStart().plus(e.getDuration()).toLocalTime())) || !(current_event.getStart().plus(current_event.getDuration()).toLocalTime().isBefore(e.getStart().toLocalTime()))){ // Si l'heure de debut de notre evenement n'est pas apres l'heure de fin de e OU que l'heure de fin de notre evenement n'est pas avant l'heure de debut de e
                    return false;
                }
            }

        }

        return true;
    }

/*
    public boolean isFreeFor(Event e) {
        for (Event current_event : listEvents) {
            if (!(e.getStart().isAfter(current_event.getStart().plus(current_event.getDuration())))  &&  !(e.getStart().plus(e.getDuration()).isBefore(current_event.getStart()))   ){
                return false;
            }
        }
        return true;
    }

 */

}
