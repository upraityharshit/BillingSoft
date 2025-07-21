package com.BILLINGSOFT.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.BILLINGSOFT.Entity.Permissions;
import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Repository.MenuRepository;
import com.BILLINGSOFT.Repository.PermissionRepository;
import com.BILLINGSOFT.Repository.SessionFinyearRepo;
import com.BILLINGSOFT.Repository.UserRepository;

@RestController
public class RestfullController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private SessionFinyearRepo sessionfinyearRepo;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard/user_management/{menugroup}")
    public List<String> getmenuslist(@PathVariable String menugroup, Model model){
        return this.menuRepository.getMenusByMenugroup(menugroup);
    }

    @GetMapping("/dashboard/user_management/{username}/{menuGroup}/{menuName}")
    public Permissions getPermissions(@PathVariable String username, 
                                        @PathVariable String menuGroup,
                                        @PathVariable String menuName)
    {
        return this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(username, menuGroup, menuName);
    }

    // @GetMapping("/dashboard/hsncode/delete/{name}")
    // public boolean checkdeletion(@PathVariable String name){
    //     List<Wood> wood = this.woodRepository.findByHsncode(name);
        
    //     if("[]".equals(wood.toString()))
    //         return true;
    //     else 
    //         return false;
        
    // }

    
    @GetMapping("/findUser/{username}")
    public User getUserByUsername(@PathVariable String username) {
    	return this.userRepository.findUserByUsername(username);
    }
}
