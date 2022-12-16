package com.peaksoft.entities.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/allCompanies")
public class CompanyController {
    private final CompanyServiceImpl companyService;

    @Autowired
    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public String getCompanies(Model model) {
        model.addAttribute("companies", companyService.getCompanies());
        return "company/companies";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company/show";
    }

    @GetMapping("/newCompany")
    public String newCompany(@ModelAttribute("company") Company company) {
//        model.addAttribute("person", new Person());
        return "company/newCompany";
    }

    @PostMapping
    public String create(@ModelAttribute("company") /*@Valid*/ Company company /*BindingResult bindingResult*/) {
//        if (bindingResult.hasErrors()){
//            return "/allCompanies/new";}
        companyService.saveCompany(company);
        return "redirect:/allCompanies";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company/editCompany";
    }

    @PatchMapping("/{id}/update")
    public String updateCompany(@ModelAttribute("company") /*@Valid*/ Company company,/* BindingResult bindingResult,*/ @PathVariable("id") Long id) {
//        if(bindingResult.hasErrors()){
//            return "/allCompanies/edit";}
        companyService.updateCompany(company, id);
        return "redirect:/allCompanies";

    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        companyService.deleteCompanyById(id);
        return "redirect:/allCompanies";
    }
}
