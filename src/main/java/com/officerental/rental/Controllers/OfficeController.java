package com.officerental.rental.Controllers;

import com.officerental.rental.Services.OfficeService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;



@Controller
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/offices")
    public String listOffices(Model model, Principal principal) {
        model.addAttribute("offices", officeService.getAllOffices());
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        //model.addAttribute("username", authentication.name());
        return "offices";
    }

    @GetMapping("/offices/{id}")
    public String officeDetails(@PathVariable Long id, Model model) {
        model.addAttribute("office", officeService.getOfficeById(id));
        return "office-details";
    }

    @PostMapping("/offices/{id}/rent")
    public String rentOffice(@PathVariable Long id) {
        officeService.rentOffice(id);
        return "redirect:/offices";
    }
}