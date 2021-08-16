package moonz.toyProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//Controller는 Model을 이용해 데이터를 가져와서 View에 전달하면서 적절한 View를 생성하는 역할
@Controller
public class HelloController {

    /*-------파라미터 요청-------*/
    //웹 브라우저의 url로 파라미터받기
    // '/hello-mvc'로 요청이 왔을 때
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    //hello 호출시
    @GetMapping("hello")
    public String hello(Model model) {  //모델을 매개변수로 받음.
        model.addAttribute("data","Hey!!!!!!!!");   //view에 전달할 데이터를 key-value 쌍으로 전달할 수 있다.
        return "hello";  //"hello"라는 view를 찾아서 랜더링.
        // 스프링 부트 템플릿 엔진 기본 viewName 매핑은 어떻게 이루어질까?
        // resources:templates/ +{ViewName}+ .html
    }

    /*-------API-------*/
    @GetMapping("hello-API")
    @ResponseBody
    public String helloAPI(@RequestParam("name") String name) {
        //model 없이!!!!!! View 전달 없이!!!!!!!!!!
        return "Hello "+ name;
    }
    //ResponseBody란?

    @GetMapping("hello-API-obj")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
