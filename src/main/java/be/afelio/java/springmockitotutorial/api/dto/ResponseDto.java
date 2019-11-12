package be.afelio.java.springmockitotutorial.api.dto;

public class ResponseDto<T> {

    private String status;
    private String message;
    private T payload;

    public ResponseDto() {
    }

    public ResponseDto(ResponseDtoStatus status, String message) {
        super();
        this.status = status.name();
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
