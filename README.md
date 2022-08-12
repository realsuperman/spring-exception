## Spring에서의 api 예외처리
API 예외 처리의 경우 HTML을 반환하는 식으로 예외를 처리하면 안된다 API 예외 처리의 경우 각 오류 상황에 맞는 오류 응답 스펙을 정하고
JSON으로 데이터를 내려주어야 한다.

##
스프링 예외 처리 : 스프링에서는 기본적으로 WAS -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러 순으로 진행이 되고 컨트롤러에서 예외가 던져지면 컨트롤러부터 시작해서
역순으로 호출이 된다. 이 과정에서 서블릿 부분에서 ExceptionResolver라는걸 호출하게 되는데 ExceptionResolver가 바로 예외를 처리해주는 리졸버이다
ExceptionResolver에서 예외를 처리할 수 있으면 WAS에게는 정상처리 로직으로 전달된다(만약 was까지 예외가 전달되면 500에러이다)
##
스프링부트는 기본적으로 ExceptionResolver를 3개를 미리 등록을 해둔다 등록하는 ExceptionResolver는 아래와 같다
1. ExceptionHandlerExceptionResolver -> @ExceptionHandler로 등록된 것을 찾는다
2. ResponseStatusExceptionResolver -> @ResponseStatus로 등록된 것을 찾는다
3. DefaultHandlerExceptionResolver이다.

-> 기본적으로 예외가 서블릿까지 전달시 서블릿에서는 해당 예외를 처리해줄 ExceptionHanlder가 있는지 찾는다 찾았으면 해당 핸들러를 호출한다
찾지 못했으면 아래로 내려가게 된다. 

2번으로 등록하는 리졸버의 경우 ResponseStatus로 등록된 항목을 찾는다
만약 없다면 3번으로 내려간다 3번의 경우 파라미터가 맞지 않는다던가 그러한 경우에 호출된다 3번까지 내려왔는데 해당하는 핸들러를 못찾으면 was까지 전달되고 500으로 처리된다

##
만약 컨트롤러의 예외 처리를 AOP화 시키고 싶다면 RestControllerAdvice를 사용하면 된다
