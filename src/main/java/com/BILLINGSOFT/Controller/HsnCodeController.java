package com.BILLINGSOFT.Controller;

import java.security.Principal;
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

import com.BILLINGSOFT.Entity.HsnCode;
import com.BILLINGSOFT.Helper.Message;
import com.BILLINGSOFT.Repository.HsnCodeRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class HsnCodeController {
    
    @Autowired
    private HsnCodeRepo hsncodeCodeRepo;

    @ModelAttribute
    public void commonData(Model model, Principal principal){

        //delete estimatetemp table data when they come on this url(if data is there)
        
    }


    @GetMapping("/hsncode_master")
    public String itemmaster(Model model){

        List<HsnCode> hsncode = this.hsncodeCodeRepo.findAllOrderByHsnid();
        model.addAttribute("hsncode",hsncode);
		model.addAttribute("newhsncode", new HsnCode());

        return "hsncode_master";
    }

    @PostMapping("/save_hsncode")
	public String saveExam(@Valid @ModelAttribute("newhsncode") HsnCode hsncode, BindingResult result , 
			Model model, HttpSession session, @RequestParam String status, @RequestParam String hsnid1){

		try {
			if(result.hasErrors()) {
				System.out.println(result);
				throw new Exception();
			}
			else{
                if(status.equals("new")){
                    HsnCode hsncode1 = this.hsncodeCodeRepo.findByHsncode(hsncode.getHsncode());
                    if(hsncode1 != null){
                        model.addAttribute("hsncode", new HsnCode());
                        session.setAttribute("message", new Message("HSN Code already exist...","alert-danger"));
                    }else{
                        Integer maxid = this.hsncodeCodeRepo.findMaxHsnCodeId();
                        if(maxid != null)
                            hsncode.setId(maxid+1);
                        else
                            hsncode.setId(1);
                        hsncode.setHsncode(hsncode.getHsncode().toUpperCase());
                        hsncode.setDescription(hsncode.getDescription().toUpperCase());

                        this.hsncodeCodeRepo.save(hsncode);

                        model.addAttribute("hsncode", new HsnCode());
                        session.setAttribute("message", new Message("Saved Successfully","alert-success"));
                    }  
                }else{
                    // HsnCode hsncode1 = this.hsncodeCodeRepo.findByHsncode(hsncode.getHsncode());
                    // if(hsncode1 != null){
                    //     model.addAttribute("hsncode", new HsnCode());
                    //     session.setAttribute("message", new Message("HSN Code already exist...","alert-danger"));
                    // }else{     
                        long id = Long.parseLong(hsnid1);
                        hsncode.setId(id);
                        hsncode.setHsncode(hsncode.getHsncode().toUpperCase());
                        hsncode.setDescription(hsncode.getDescription().toUpperCase());

                        this.hsncodeCodeRepo.save(hsncode);

                        model.addAttribute("hsncode", new HsnCode());
                        session.setAttribute("message", new Message("Saved Successfully","alert-success"));
                    //}
                }	
			}	
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("hsncode", hsncode);
			session.setAttribute("message", new Message("Something went wrong..! Hsncode and Gst is mandatory, if gst is not applicable enter 0 (zero)","alert-danger"));
		}

		return "redirect:/dashboard/hsncode_master";
	}

    @GetMapping("/delete/hsn/{id}")
	public String deleteExam(@Valid @PathVariable long id, Model model, HttpSession session){

		this.hsncodeCodeRepo.deleteById(id);
		
		return "redirect:/dashboard/hsncode_master";

	}

    @GetMapping("/edit_Hsn/{id}")
    public String editHsn(@PathVariable long id, Model model){

        HsnCode hsncode = this.hsncodeCodeRepo.findById(id).get();
        List<HsnCode> hsncode1 = this.hsncodeCodeRepo.findAllOrderByHsnid();
        model.addAttribute("hsncode",hsncode1);
        model.addAttribute("newhsncode", hsncode);

        return "hsncode_master";
    }
}
