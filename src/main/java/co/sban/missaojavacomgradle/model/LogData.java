package co.sban.missaojavacomgradle.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;


@Document(collection = "LogData")
public class LogData {

    @Id
    private String id;
    private LocalDateTime date;
    private Object data;

    public LogData() {
    }

    public LogData(String id, LocalDateTime date, Object data) {
        this.id = id;
        this.date = date;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogData logData = (LogData) o;
        return Objects.equals(id, logData.id) && Objects.equals(date, logData.date) && Objects.equals(data, logData.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, data);
    }

    @Override
    public String toString() {
        return "LogData{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", data=" + data +
                '}';
    }
}
