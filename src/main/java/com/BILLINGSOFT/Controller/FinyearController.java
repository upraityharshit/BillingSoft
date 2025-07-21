package com.BILLINGSOFT.Controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BILLINGSOFT.Entity.Finyear;
import com.BILLINGSOFT.Helper.Message;
import com.BILLINGSOFT.Repository.FinyearRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class FinyearController {

    @Autowired
    private FinyearRepo finyearRepo;
    

    @ModelAttribute
    public void commonData(Model model, Principal principal){

        //delete estimatetemp table data when they come on this url(if data is there)
    
    }
    
    @GetMapping("/finyear_master")
    public String Finyear(Model model){

        List<Finyear> finyear = this.finyearRepo.findAllOrderByFinid();
        model.addAttribute("finyears",finyear);
		model.addAttribute("newfinyear", new Finyear());

        return "Finyear";
    }

    @PostMapping("/save_finyear")
	public String saveFinyear(@Valid @ModelAttribute("newfinyear") Finyear finyear, BindingResult result , 
			Model model, HttpSession session, @RequestParam String status, @RequestParam String finid){

		try {
			if(result.hasErrors()) {
				System.out.println(result);
				throw new Exception();
			}
			else{
                if(status.equals("new")){
                    Finyear finyear1 = this.finyearRepo.findByName(finyear.getName());
                    if(finyear1 != null){
                        model.addAttribute("finyear", new Finyear());
                        session.setAttribute("message", new Message("Name already exist...","alert-danger"));
                    }else{
                        Integer maxid = this.finyearRepo.findMaxFinId();
                        finyear.setId((maxid != null ? maxid + 1 : 1));
                        finyear.setName(finyear.getName().toUpperCase());

                        Date fromdate = Date.valueOf(finyear.getFromdate().toString());
                        Date todate = Date.valueOf(finyear.getTodate().toString());

                        finyear.setFromdate(fromdate);
                        finyear.setTodate(todate);
                        
                        this.finyearRepo.save(finyear);

                        model.addAttribute("finyear", new Finyear());
                        session.setAttribute("message", new Message("Saved Successfully","alert-success"));
                    }  
                }else{
                    long id = Long.parseLong(finid);
                    finyear.setId(id);
                    finyear.setName(finyear.getName().toUpperCase());

                    this.finyearRepo.save(finyear);

                    model.addAttribute("finyear", new Finyear());
                    session.setAttribute("message", new Message("Saved Successfully","alert-success"));
                }	
			}	
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("finyear", finyear);
			session.setAttribute("message", new Message("Something went wrong..!"+e,"alert-danger"));
		}

		return "redirect:/dashboard/finyear_master";
	}

    @GetMapping("/delete/finyear/{id}")
	public String deleteFinyear(@Valid @PathVariable long id, Model model, HttpSession session){
		//this.finyearRepo.deleteById(id);
	    return "redirect:/dashboard/finyear_master";
	}

    @GetMapping("/edit_finyear/{id}")
    public String editHsn(@PathVariable long id, Model model){

        Finyear finyear = this.finyearRepo.findById(id).get();
        model.addAttribute("newfinyear", finyear);
        List<Finyear> finyears = this.finyearRepo.findAllOrderByFinid();
        model.addAttribute("finyears",finyears);
        
        return "Finyear";
    }
}
