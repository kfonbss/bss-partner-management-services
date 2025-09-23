package in.gov.kfon.partner.contract;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
  private int status; // HTTP status code (e.g. 200, 201, 404, 500)
  private String message; // "OK", "Created", "Validation failed", etc.
  private Object error; // null on success; String or Map<String,String> for errors
  private T data; // payload

  public static <T> Response<T> ok(T data, String message) {
    return Response.<T>builder()
        .status(200)
        .message(message == null ? "OK" : message)
        .error(null)
        .data(data)
        .build();
  }

  public static <T> Response<T> created(T data, String message) {
    return Response.<T>builder()
        .status(201)
        .message(message == null ? "Created" : message)
        .error(null)
        .data(data)
        .build();
  }

  public static <T> Response<T> fail(int status, String message, Object error) {
    return Response.<T>builder()
        .status(status)
        .message(message == null ? "Error" : message)
        .error(error)
        .data(null)
        .build();
  }
}
