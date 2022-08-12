package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "hello.exception.api") // exceptionHandler + InitBinder (참고로 대상을 안정했으므로 모든 컨트롤러가 대상임)
// @RestControllerAdvice(annotations = RestController.class) -> 특정 컨트롤러한테만 적용 가능
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) // IllegalArgumentException을 잡는다
    // 정확히는 예외 핸들러가 이녀석을 실행 시켜준다
    public ErrorResult illegalExhandler(IllegalArgumentException e){
        log.error("[exceptionHandler] ex",e);
        return new ErrorResult("BAD",e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResult> userExHandler(){
        ErrorResult result = new ErrorResult("USER_EX","예외다");
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){ // 참고로 예외핸들러에 안적고 파라미터에 적어도 됨 한마디로 ExceptionHandler(Exception.class)임
        return new ErrorResult("EX","오류임");
    }
}
