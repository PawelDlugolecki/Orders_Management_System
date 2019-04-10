package dlugolecki.pawel.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyExceptionDTO {

    private Long id;
    private String className;
    private String errorInfo;
    private LocalDateTime time;

    public MyExceptionDTO(MyExceptionDTOBuilder myExceptionDTOBuilder) {
        this.id = myExceptionDTOBuilder.id;
        this.className = myExceptionDTOBuilder.className;
        this.errorInfo = myExceptionDTOBuilder.errorInfo;
        this.time = myExceptionDTOBuilder.time;
    }

    public static MyExceptionDTO.MyExceptionDTOBuilder builder() {
        return new MyExceptionDTOBuilder();
    }

    public static class MyExceptionDTOBuilder {
        private Long id;
        private String className;
        private String errorInfo;
        private LocalDateTime time;

        public MyExceptionDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MyExceptionDTOBuilder className(String className) {
            this.className = className;
            return this;
        }

        public MyExceptionDTOBuilder errorInfo(String errorInfo) {
            this.errorInfo = errorInfo;
            return this;
        }

        public MyExceptionDTOBuilder time(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public MyExceptionDTO build() {
            return new MyExceptionDTO(this);
        }
    }
}