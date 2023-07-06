package com.group6.commune.Repository;

import com.group6.commune.Mapper.EventRowMapper;
import com.group6.commune.Model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Event> getAllEvents(){
        var query = """
                    select * from events;
                """;
        return jdbcTemplate.query(query,new EventRowMapper());
    }

    public Event createEvent(Event event){
        String query = """
                INSERT INTO events (event_name, short_description,
                event_description, location, event_start_date, event_end_date,
                event_poster, entry_fees, event_type, created_by) VALUES(?,?,?,?,?,?,?,?,?,?);
                """;

        int result = jdbcTemplate.update(query,
                event.getEventName(),
                event.getShortDescription(),
                event.getDescription(),
                event.getLocation(),
                event.getEventStartTime(),
                event.getEventEndTime(),
                event.getEventPoster(),
                event.getEntryFees(),
                event.getEventType(),
                event.getCreatedByUserId());

        return result ==1 ? event : new Event();
    }

    @Override
    public Event getEventById(int id) {
        String query = String.format("""
                    SELECT * FROM event where event_id= %d"""+id);
        Event event = (Event) jdbcTemplate.query(query, new EventRowMapper());
        return event == null ? new Event() : event;
    }

    public Event updateEvent(Event event){
        String query= """
                  UPDATE events
                  SET
                      event_name = ?,
                      short_description = ?,
                      event_description = ?,
                      location = ?,
                      event_start_date = ?,
                      event_end_date = ?,
                      event_poster = ?,
                      entry_fees = ?,
                      event_type = ?,
                      created_by = ?
                  WHERE
                      event_id = ?;
                """;

        int res = jdbcTemplate.update(query,
                event.getEventName(),
                event.getShortDescription(),
                event.getDescription(),
                event.getLocation(),
                event.getEventStartTime(),
                event.getEventEndTime(),
                event.getEventPoster(),
                event.getEntryFees(),
                event.getEventType(),
                event.getCreatedByUserId(),
                event.getEventId());

        return res == 1 ? event : new Event();
    }

    @Override
    public Event deleteEvent(int id) {
        String query = """
                    DELETE FROM events WHERE event_id = ?;
                """;
        int res = jdbcTemplate.update(query, id);
        return new Event();
    }

}


