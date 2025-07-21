package com.BILLINGSOFT.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.BILLINGSOFT.Entity.Permissions;
import com.BILLINGSOFT.Entity.SessionFinYear;
import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Repository.PermissionRepository;
import com.BILLINGSOFT.Repository.SessionFinyearRepo;
import com.BILLINGSOFT.Repository.UserRepository;

@ControllerAdvice
public class CommonController {
    @Autowired
    private UserRepository userRepository;  

    @Autowired
    private SessionFinyearRepo sessionfinyearRepo;

    @Autowired
    private PermissionRepository permissionRepository;
    
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @ModelAttribute
    public void commonData(Model model, Principal principal){
    	System.gc();

        if(principal!=null){
            
            User user = userRepository.findUserByUsername(principal.getName());
            user.setFailedAttempts(0);
            userRepository.save(user);
            
            model.addAttribute("displayname", user.getName());
            model.addAttribute("title", "DASHBOARD");
            model.addAttribute("contextPath", contextPath);

            // for the inside Finyear form checking edit aur delete
            Permissions finyearperm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "MASTERS", "FINYEAR MASTER");
            model.addAttribute("finyearperm", finyearperm);

            // for the inside HSncode form checking edit aur delete
            Permissions hsncodeperm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "MASTERS", "HSNCODE MASTER");
            model.addAttribute("hsncodeperm", hsncodeperm);

            // for the inside wood form checking edit aur delete
            Permissions woodperm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "MASTERS", "WOOD MASTER");
            model.addAttribute("woodperm", woodperm);

            // for the inside estimate form checking edit aur delete
            Permissions estimateperm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "TRANSACTIONS", "ESTIMATE");
            model.addAttribute("estimateperm", estimateperm);

            Permissions deliveryestimateperm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "TRANSACTIONS", "DELIVERY ESTIMATE");
            model.addAttribute("deliveryestimateperm", deliveryestimateperm);
            
            Permissions paymentperm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "TRANSACTIONS", "PAYMENTS");
            model.addAttribute("paymentperm", paymentperm);
            
            Permissions commissionperm = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "TRANSACTIONS", "COMMISSION");
            model.addAttribute("commissionperm", commissionperm);

            //start for top menu attribute
            Permissions finyearview = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "MASTERS", "FINYEAR MASTER");
            model.addAttribute("finyearview", finyearview);

            Permissions hsncodeview = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "MASTERS", "HSNCODE MASTER");
            model.addAttribute("hsncodeview", hsncodeview);

            Permissions woodview = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "MASTERS", "WOOD MASTER");
            model.addAttribute("woodview", woodview);

            Permissions estimateInvoiceReport = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "REPORTS", "ESTIMATE INVOICE");
            model.addAttribute("estimateInvoiceReport", estimateInvoiceReport);

            Permissions destimateInvoiceReport = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "REPORTS", "DELIVERY ESTIMATE INVOICE");
            model.addAttribute("destimateInvoiceReport", destimateInvoiceReport);

            Permissions paymentLists = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "REPORTS", "PAYMENT LISTS");
            model.addAttribute("paymentLists", paymentLists);
            
            Permissions commissionLists = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "REPORTS", "COMMISSION LISTS");
            model.addAttribute("commissionLists", commissionLists);

            Permissions createUser = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "SETTINGS", "CREATE USER");
            model.addAttribute("createUser", createUser);

            Permissions userManagement = this.permissionRepository.findByUsernameAndMenuGroupAndMenuName(principal.getName(), "SETTINGS", "USER MANAGEMENT");
            model.addAttribute("userManagement", userManagement);

            //End for top menu attribute
        }

        long finid = 1;
        SessionFinYear sFinyear = this.sessionfinyearRepo.findById(finid).get();
        model.addAttribute("finyear", sFinyear.getFinname());
    }
}
