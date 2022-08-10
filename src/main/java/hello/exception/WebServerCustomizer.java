package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error-page/500");
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class,"/error-page/500");
        factory.addErrorPages(errorPage404,errorPage500,errorPageEx);

    }
}

// was가 뜨는 시점에 톰켓이 해당 내용을 인지하고 에러나 예외가 was까지 전달되면
// 동작에 맞게 페이지를 수행한다 (참고로 스프링 부트를 사용하면 이렇게 생긴걸 자동으로 만들어줌)
// static/template/error(해당 디렉토리 생성 필요) 이 안에 이름에 맞게 넣으면 자동으로 처리해줌
// 만약 404.html을 만들었다하면 404에러 발생시 이걸 호출함
// 기본적으로 html을 찾을 때 resuources/templates/error/404.html를 찾고 없으면
// resources/static/error/404.html를 찾고 없다면 resources/templates/404.html를 찾는다
// 참고로 스프링은 오류 페이지로만 이동하지 않고 모델도 추가로 붙여준다