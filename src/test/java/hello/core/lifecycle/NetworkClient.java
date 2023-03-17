package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url; //주소 값 담을 변수

    public NetworkClient(){
        System.out.println(">>> 생성자 호출!!! url 값: " + url);  //url 이 null 값이 뜨는 이유 : 객체를 생성하는 단계에서는 url 이 없는게 당연하기 때문이다.
    /*    connect();
        call("초기화 연결 메세지");*/

    }

    public void setUrl(String url) {
        this.url = url;
    }

    /* 서비스 시작 시 호출 */
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String msg){
        System.out.println("call = " + url + "  message =" + msg);
    }

    /* 서비스 종료 시 호출 */
    public void disconnect(){
        System.out.println("close : " + url);
    }


    /* 초기화 */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    // InitializingBean 은 afterPropertiesSet() 메서드로 초기화를 지원한다.
    // 2. implements InitializingBean, DisposableBean
   /* @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }


    //DisposableBean 은 destroy() 메서드로 소멸을 지원한다.
    @Override
    public void destroy() throws Exception {
        disconnect();
    }*/



}
