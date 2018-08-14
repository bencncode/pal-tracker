package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;

    }
    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){

        return new ResponseEntity(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }
    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry timeEntry= timeEntryRepository.find(id);
        if (timeEntry!=null)
                return new ResponseEntity(timeEntry, HttpStatus.OK);
        else
                return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        
    }
    @PutMapping("/time-entries/{l}")
    public ResponseEntity update(@PathVariable long l, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry= timeEntryRepository.update(l, expected);
        if (timeEntry!=null) {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/time-entries/{l}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long l) {
        timeEntryRepository.delete(l);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }
}
