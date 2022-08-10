package hello.exception.filter;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        try {
            log.info("REQUEST [{}][{}][{}]", uuid,
                    request.getDispatcherType(), requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}][{}]", uuid,
                    request.getDispatcherType(), requestURI);
        }
    }
    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}

/*
    예외나 문제가 발생했을 때 해당 하는 작업이 있으면 그 작업으로 재요청을 보내게 된다 예를들어서 에러가 발생
    했을 때 /error라는 컨트롤러를 호출하라고 하면 was는 /error를 재호출한다 그러면 필터를 또 호출할 수 있는데
    이랬을 때 모호함을 없애기 위해서 was는 상태를 erorr라는 상태로 보내준다(초기 요청은 REQUEST이다)
    이러한 해당 값이 DispatcherType이다 즉 정리하면 아래와 같다

    (결국 클라이언트로 부터 발생한 정상 요청인지, 아니면 오류 페이지를 출력하기 위한 내부 요청인지 구분할
    수 있어야 한다. 서블릿은 이런 문제를 해결하기 위해 DispatcherType 이라는 추가 정보를 제공한다.)
 */