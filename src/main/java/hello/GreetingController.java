package hello;

import hello.bean.Trade;
import hello.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
//@MessageMapping("with")
public class GreetingController {

    @Autowired
    private TradeService tradeService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    //@MessageMapping({"/helloByUser/{baz}"})
    //@SendToUser(value = "/topic/admin", broadcast = false)
    public void greetingToUser(HelloMessage message, Principal principal,@DestinationVariable String baz) throws Exception {
        System.out.println(baz);
        Trade trade = new Trade();
        trade.setUserName(baz);
        trade.setResult("程序员的人生");
        tradeService.afterTradeExecuted(trade);
        //Thread.sleep(1000); // simulated delay
        principal.getName();
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping({"/helloByUser{baz}"})
    @SendToUser(value = "/topic/position-updates" , broadcast = false)
    public Greeting greetingToUser4(HelloMessage message, Principal principal,@DestinationVariable String baz) throws Exception {
        System.out.println(baz);

        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/ashdiashdih/{baz}/helloByUser")
    @SendToUser(value = "/topic/admin")
    public Greeting greetingToUser2(HelloMessage message, Principal principal,@DestinationVariable String baz) throws Exception {
        //Thread.sleep(1000); // simulated delay
        principal.getName();
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    @MessageMapping("/{baz}/helloByUser")
    @SendToUser(value = "/topic/admin")
    public Greeting greetingToUser3(HelloMessage message, Principal principal,@DestinationVariable String baz) throws Exception {
        //Thread.sleep(1000); // simulated delay
        principal.getName();
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @RequestMapping("/login/{username}")
    public @ResponseBody String login(HttpSession session, @PathVariable("username") String username) {
        System.out.println("登录接口,username="+username);
        session.setAttribute("username", username);

        System.out.println(session.getAttribute("username"));
        return "success";
    }

}
