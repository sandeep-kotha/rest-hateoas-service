package hello;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    private static final String TEMPLATE = "<status>processed sucessfully</status>";

    @RequestMapping("/subscriber")
    public HttpEntity<String> greeting(@RequestBody(required=true) String payload) {
    	System.err.println(payload);
    	return new ResponseEntity<String>(TEMPLATE, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping("/dart")
    public HttpEntity<String> dart(@RequestBody(required=false) String payload) {
    	System.err.println(payload);
    	return new ResponseEntity<String>(TEMPLATE, HttpStatus.ACCEPTED);
    }
}