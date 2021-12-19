package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AgendaTest {
    Agenda agenda;
    
    // November 1st, 2020
    LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

    // January 5, 2021
    LocalDate jan_5_2021 = LocalDate.of(2021, 1, 5);

    // November 1st, 2020, 22:30
    LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);

    // 120 minutes
    Duration min_120 = Duration.ofMinutes(120);

    // A simple event
    // November 1st, 2020, 22:30, 120 minutes
    Event simple = new Event("Simple event", nov_1__2020_22_30, min_120);

    // A Weekly Repetitive event ending at a given date
    RepetitiveEvent fixedTermination = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, jan_5_2021);

    // A Weekly Repetitive event ending after a give number of occurrrences
    RepetitiveEvent fixedRepetitions = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, 10);
    
    // A daily repetitive event, never ending
    // November 1st, 2020, 22:30, 120 minutes
    RepetitiveEvent neverEnding = new RepetitiveEvent("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);

    @BeforeEach
    public void setUp() {
        agenda = new Agenda();
        agenda.addEvent(simple);
        agenda.addEvent(fixedTermination);
        agenda.addEvent(fixedRepetitions);
        agenda.addEvent(neverEnding);
    }
    
    @Test
    public void testMultipleEventsInDay() {
        assertEquals(4, agenda.eventsInDay(nov_1_2020).size(), "Il y a 4 événements ce jour là");
        assertTrue(agenda.eventsInDay(nov_1_2020).contains(neverEnding));
    }

    @Test
    public void testFindByTitle(){

        List<Event> list1 = new ArrayList<>();
        List<Event> list2 = new ArrayList<>();
        List<Event> list3 = new ArrayList<>();

        list1.add(simple);

        list2.add(fixedTermination);
        list2.add(fixedRepetitions);

        list3.add(neverEnding);

        assertEquals(list1, agenda.findByTitle("Simple"));
        assertEquals(list2, agenda.findByTitle("termination"));
        assertEquals(list3, agenda.findByTitle("Ending"));

    }

    @Test
    public void testIsFreeFor(){
        Event event1 = new Event("Simple event", LocalDateTime.of(2000, 11, 1, 22, 30), min_120);
        Event event2 = new Event("Simple event", LocalDateTime.of(2020, 11, 6, 21, 30), min_120);
        Event event3 = new Event("Simple event", LocalDateTime.of(2021, 7, 6, 0, 7), min_120);
        Event event4 = new Event("Simple event", LocalDateTime.of(2020, 11, 6, 23, 30), Duration.ofMinutes(10));
        Event event5 = new Event("Simple event", LocalDateTime.of(2022, 3, 7, 6, 56), Duration.ofMinutes(180));

        assertTrue(agenda.isFreeFor(event1));  // Evenement ayant lieu longtemps avant
        assertFalse(agenda.isFreeFor(event2)); // Evenement debordant sur un autre
        assertFalse(agenda.isFreeFor(event3)); // Evenement debutant pdt un autre
        assertFalse(agenda.isFreeFor(event4)); // Evenement en plein milieu d'un autre
        assertTrue(agenda.isFreeFor(event5));  // Evenement n'étant pas pendant un event

    }


}
