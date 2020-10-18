package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class Meeting {
    private String name;
    private String description;
    private LocalDate publishDate;
    private LocalDateTime startDateTime;
    private Temporal duration;
    private int frequency;
    private LocalDateTime lastHappenAt;
    private LocalDate endDate;
    private String note;
    private boolean isDone;

    /**
     * Constructor for meeting.
     * Date and timing for the meeting should be present and not null.
     *
     * @param localDateTime date and time of meeting
     */
    public Meeting(String localDateTime) {
        requireAllNonNull(localDateTime);
        startDateTime = LocalDateTime.parse(localDateTime);
        isDone = false;
    }

    /**
     * Finishes a meeting.
     */
    public void done() {
        isDone = true;
    }

    public String getMeetingName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public Temporal getDuration() {
        return duration;
    }

    public int getFrequency() {
        return frequency;
    }

    public LocalDateTime getLastHappenAt() {
        return lastHappenAt;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getNote() {
        return note;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setDuration(Temporal duration) {
        this.duration = duration;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setLastHappenAt(LocalDateTime lastHappenAt) {
        this.lastHappenAt = lastHappenAt;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
