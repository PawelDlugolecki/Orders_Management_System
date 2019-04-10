package dlugolecki.pawel.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Data
@Entity(name = "exceptions")
public class MyException extends RuntimeException {

    @Id
    @GeneratedValue
    private Long id;
    private String className;
    private String errorInfo;
    private LocalDateTime time;

    public MyException(String className, String errorInfo) {
        this(className, errorInfo, LocalDateTime.now());
    }

    public MyException(String className, String errorInfo, LocalDateTime time) {
        this.className = className;
        this.errorInfo = errorInfo;
        this.time = time;
    }
}