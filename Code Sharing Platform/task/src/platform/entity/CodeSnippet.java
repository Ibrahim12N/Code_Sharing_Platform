package platform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "codeSnippet")
public class CodeSnippet {

    private static final String PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id = UUID.randomUUID().toString();

    private String code;
    private String date = LocalDateTime.now().format(FORMATTER);
    private long time;
    private int views;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean timeRestricted=false;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "viewRestricted")
    private boolean viewRestricted=false;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime creationTime = LocalDateTime.now();


    public CodeSnippet() {
    }

    public CodeSnippet(String id, String code, String date, long time, int views, boolean timeRestricted, boolean viewRestricted, LocalDateTime creationTime) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
        this.timeRestricted = timeRestricted;
        this.viewRestricted = viewRestricted;
        this.creationTime = creationTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public void setTimeRestricted(boolean timeRestricted) {
        this.timeRestricted = timeRestricted;
    }

    public boolean isViewRestricted() {
        return viewRestricted;
    }

    public void setViewRestricted(boolean viewRestricted) {
        this.viewRestricted = viewRestricted;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}