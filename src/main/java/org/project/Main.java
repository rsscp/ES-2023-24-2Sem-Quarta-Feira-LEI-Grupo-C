package org.project;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException {


        //UI.main(args);
        //App.main(args);

        HashMap<LocalDate, DaySlots> slots = new HashMap<>();

        List<TimeSlot> slots1 = new ArrayList<>();
        slots1.add(TimeSlot.SLOT_0);
        slots1.add(TimeSlot.SLOT_2);
        slots1.add(TimeSlot.SLOT_3);
        List<TimeSlot> slots2 = new ArrayList<>();
        slots1.add(TimeSlot.SLOT_4);
        slots1.add(TimeSlot.SLOT_5);
        slots1.add(TimeSlot.SLOT_6);
        slots1.add(TimeSlot.SLOT_8);

        LocalDate date = LocalDate.of(2024, 10, 10);

        Set<TimeSlot> timeSlots = HashSet.newHashSet(TimeSlot.values().length);
        timeSlots.add(TimeSlot.SLOT_0);
        timeSlots.remove(TimeSlot.SLOT_0);

        for (TimeSlot s: timeSlots.stream().toList())
            System.out.println(s);

        //System.out.println(slots.get(LocalDate.of(2024, 10, 10)));
        //Testar se um LectureSlot novo com os mesmos valores que uma key anterior vão dar à mesma entrada do hashmap
    }
}
