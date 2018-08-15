package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    Map<Long, TimeEntry> timeEntryMap = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {
        //timeEntry.setId(1L);
        TimeEntry persitedEntry = new TimeEntry(timeEntryMap.size()+1, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntryMap.put(persitedEntry.getId(), persitedEntry);
        return persitedEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        //Collection timeEntryColl = timeEntryMap.values();
        return new ArrayList<>(timeEntryMap.values());
    }


        @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntryMap.replace(id, timeEntry);
        return timeEntryMap.get(id);

    }

    @Override
    public void delete(Long id) {
         timeEntryMap.remove(id);
    }


}
