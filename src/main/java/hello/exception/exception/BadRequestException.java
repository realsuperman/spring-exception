package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 해당 예외 발생시 해당 상태코드로 바꿔치기 한다(우리가 만든 예외 클래스만 지정 가능)
@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "error.bad")
public class BadRequestException extends RuntimeException {

}
