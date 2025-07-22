package com.BILLINGSOFT.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BILLINGSOFT.Entity.Permissions;
import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Helper.Message;
import com.BILLINGSOFT.Repository.MenuRepository;
import com.BILLINGSOFT.Repository.PermissionRepository;
import com.BILLINGSOFT.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class UsermanagementController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private MenuRepository menuRepository;

    @ModelAttribute
    public void commonData(Model model, Principal principal){

        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("displayname", user.getName());
        model.addAttribute("title", "DASHBOARD");
    }
    
    @GetMapping("/user_management")
    public String setting_usermanagement(Model model,Principal principal){

        List<User> allUsers= this.userRepository.findAll();
        List<String> menugroups = this.menuRepository.getAllMenuLists();

        //Permissions perm = this.permissionRepository.findPermisisonByUsername(principal.getName());
        
        model.addAttribute("users", allUsers);
        model.addAttribute("menugroups", menugroups);
        //model.addAttribute("permission", perm);

        return "User/user_management";
    }


    @PostMapping("/save_permission")
    public String add_permission(@ModelAttribute Permissions permission, Model model, HttpSession session){

        try{

            Permissions perm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(permission.getUsername(),
            permission.getMenuGroup(), permission.getMenuName());

            if(perm !=null){
                permission.setPid(perm.getPid());
                
                this.permissionRepository.save(permission);

                model.addAttribute("permission", new Permissions());
			    session.setAttribute("message", new Message("Saved Successfully..!!","alert-success"));
            }
            else{
                this.permissionRepository.save(permission);

                model.addAttribute("permission", new Permissions());
			    session.setAttribute("message", new Message("Saved Successfully..!!","alert-success"));
            }
        }catch(Exception e){
            e.printStackTrace();
			model.addAttribute("permission", permission);
			session.setAttribute("message", new Message("Something went wrong!!"+e,"alert-danger"));
			
            return "redirect:/dashboard/user_management";
        }

        return "redirect:/dashboard/user_management";
    }
}
