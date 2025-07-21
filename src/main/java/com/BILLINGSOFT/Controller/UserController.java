package com.BILLINGSOFT.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Helper.Message;
import com.BILLINGSOFT.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/create_user")
    public String setting_createUser(Model model){

        model.addAttribute("user", new User());

        List<User> allUser= this.userRepository.findAll();
    	model.addAttribute("allusers", allUser);
    	model.addAttribute("edit_user", new User());

        return "create_user";
    }
    
    // @GetMapping("/edit_user")
    // public String editUser(Model model){
    // 	List<User> allUser= this.userRepository.findAll();
    // 	model.addAttribute("allusers", allUser);
    // 	model.addAttribute("user", new User());
    //     return "edit_user";
    // }

    @PostMapping("/user_register")
    public String createUser(@Valid @ModelAttribute User user, BindingResult result, 
    Model model, HttpSession session, @RequestParam String cpass, Principal principal){
        
        List<User> allUser= this.userRepository.findAll();
        boolean userExit = false;
        
        try{
            for (User u : allUser) {
                if(u.getUsername().equals(user.getUsername())){
                    userExit = true;
                }
            }

            if(result.hasErrors())
			{
				System.out.println(result);
				throw new Exception();
			}
			else {
                if(userExit)
                {
                    model.addAttribute("user", user);
                    session.setAttribute("message", new Message("Username already exists","alert-danger"));
                }
                else{
                    if(user.getPassword().equals(cpass)){

                        long maxuserid = this.userRepository.findMaxUserId();

                        user.setUser_id(maxuserid+1);
                        user.setRole("USER");
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        user.setIslocked(false);

                        userRepository.save(user);

                        model.addAttribute("user", new User());
                        session.setAttribute("message", new Message("Successfully Registered !!","alert-success"));
                        
                    }
                    else{
                        model.addAttribute("user", user);
                        session.setAttribute("message", new Message("Password is not matched","alert-danger"));
                    }
                }
                return "redirect:/dashboard/create_user";
            }
        }catch(Exception e){
            e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!"+e,"alert-danger"));
			
            return "redirect:/dashboard/create_user";
        }
    }
    
    @PostMapping("/user/edit_user")
    public String UserEdit(@Valid @ModelAttribute User user, BindingResult result, 
    Model model, HttpSession session, @RequestParam String cpass, Principal principal){
        
        try{

            if(result.hasErrors())
			{
				System.out.println(result);
				throw new Exception();
			}
			else {
                        if(user.getPassword().equals(cpass)){
                        	
                        User user1 = this.userRepository.findUserByUsername(user.getUsername()); 

                        user.setUser_id(user1.getUser_id());
                        user.setRole("USER");
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        user.setIslocked(false);

                        userRepository.save(user);

                        model.addAttribute("user", new User());
                        session.setAttribute("message", new Message("User Data Updated !!","alert-success"));
                        
                    }
                    else{
                        model.addAttribute("user", user);
                        session.setAttribute("message", new Message("Password is not matched","alert-danger"));
                    }
                }
                   
        }catch(Exception e){
            e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!"+e,"alert-danger"));
			
        }
        return "redirect:/dashboard/create_user";
    }
    
}
