package com.BILLINGSOFT.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.BILLINGSOFT.Entity.Finyear;
import com.BILLINGSOFT.Entity.SessionFinYear;
import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Repository.FinyearRepo;
import com.BILLINGSOFT.Repository.SessionFinyearRepo;
import com.BILLINGSOFT.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController{

    @Autowired 
    private SessionFinyearRepo SessionFinyearRepo;
 
    @Autowired
    private FinyearRepo finyearRepo;
    
    @Autowired
    private UserRepository userRepository;
    
    @Value("${server.servlet.context-path}")
    private String contextPath;
    
    @GetMapping("/")
    public String redirectToLogin() {
    	
    	List<User> user = userRepository.findAll();
    	
    	for(User user1 : user) {
    		user1.setFailedAttempts(0);
    		userRepository.save(user1);
    	}
    	
        return "redirect:/login";
    }
	
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model, HttpSession session){
    		
    	model.addAttribute("title", "e-Estimate");
    	model.addAttribute("contextPath", contextPath);
    	
    	if (error != null) {
            model.addAttribute("error", error);
        }
    	
        List<Finyear> finyear = this.finyearRepo.findAllActiveYear();
        model.addAttribute("finyear", finyear);
        
        for(Finyear yr : finyear){
            if(yr.isCurrent()){
                model.addAttribute("currentyear", yr.getName());
                break;
            }
            else
                model.addAttribute("currentyear", "select");
        }
    	 
        return "index";
    }
    
    @GetMapping("/triggerRedirect")
    public void triggerRedirect(@RequestParam(value = "error", required = false) String error, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Construct the dynamic URL based on the context path
        String contextPath = request.getContextPath();
        if (contextPath == "/") {
            contextPath = null; // Fallback to empty string if context path is not set
        }
        String redirectUrl = contextPath + "/login";
        if (error != null) {
            redirectUrl += "?error=" + error;
        }
        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/save_finyear/{finname}")
    public ResponseEntity<String> saveFinyear(@PathVariable String finname) {
        try {
            SessionFinYear sFinYear = new SessionFinYear();
            sFinYear.setYearid(1);
            sFinYear.setFinname(finname);
            this.SessionFinyearRepo.save(sFinYear);

            // Return a success response
            return ResponseEntity.ok("Finyear saved successfully");
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Finyear: " + e.getMessage());
    }
}

    @GetMapping("/test")
    public String test(Model model){
       
        return "test";
    }
    
}
