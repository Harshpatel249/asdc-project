package com.group6.commune.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.commune.Controller.EventController;
import com.group6.commune.Model.Event;
import com.group6.commune.Service.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(EventController.class)
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEvents() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Event event1 = new Event(1, "Music Festival", "Live performances by popular artists", "Join us for a day full of music, food, and fun!", "Central Park", sdf.parse("2023-07-10 18:00:00"), sdf.parse("2023-07-11 02:00:00"), "music_festival_poster.jpg", 50, "Festival", 2);
        Event event2 = new Event(2, "Art Exhibition", "Discover the beauty of contemporary art", "Explore stunning artworks created by talented local artists.", "Gallery XYZ", sdf.parse("2023-07-10 18:00:00"), sdf.parse("2023-07-11 02:00:00"), "art_exhibition_poster.jpg", 10, "Exhibition", 2);
        List<Event> events = Arrays.asList(event1, event2);

        when(eventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(MockMvcRequestBuilders.get("/events")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].eventId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].eventId").value(2));

        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    public void testCreateEvent() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Event event = new Event(1, "Music Festival", "Live performances by popular artists", "Join us for a day full of music, food, and fun!", "Central Park", sdf.parse("2023-07-10 18:00:00"), sdf.parse("2023-07-11 02:00:00"), "music_festival_poster.jpg", 50, "Festival", 2);

        when(eventService.createEvent(any(Event.class))).thenReturn(event);

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(event))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventId").value(event.getEventId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eventName").value(event.getEventName()));

        verify(eventService, times(1)).createEvent(any(Event.class));
    }

}
