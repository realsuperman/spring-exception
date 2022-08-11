package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("400에러임");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage()); // was에게 400이라고 안내해줌
                return new ModelAndView(); // was는 정상흐름으로 인지함
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return null; // null 반환시 해당 예외가 그냥 was까지 날라감
    }
}
