package com.kedu.firmware.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kedu.firmware.DTO.EventDTO;
import com.kedu.firmware.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/calendar/{calendarId}")
    public List<EventDTO> getEventsByCalendarId(@PathVariable Long calendarId) {
        return eventService.getEventsByCalendarId(calendarId);
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventDTO event) {
        try {
            eventService.createEvent(event);
            return ResponseEntity.ok("Event created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the event");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody EventDTO event) {
        try {
            event.setEventsSeq(id);
            eventService.updateEvent(event);
            return ResponseEntity.ok("Event updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the event");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok("Event deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the event");
        }
    }
}
