package hello;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    @MessageMapping("/action")
    public void handleAction() throws Exception{
        // raise MyBusinessException here
        System.out.println("ERROR ");
    }

    @MessageExceptionHandler
    @SendToUser(value="/queue/errors", broadcast=false)
    public String handleException(MyBusinessException exception) {
        // ...
        return "error";
    }
}
