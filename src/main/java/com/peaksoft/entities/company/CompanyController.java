package com.peaksoft.entities.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
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
    public String newCompany(@ModelAttribute("company") Company company, Model model) {
        model.addAttribute("company", new Company());
        return "company/newCompany";
    }

    @PostMapping
    public String create(@ModelAttribute("company") @Valid Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/company/newCompany";}
        companyService.saveCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company/editCompany";
    }

    @PatchMapping("/{id}/update")
    public String updateCompany(@ModelAttribute("company") @Valid Company company, BindingResult bindingResult, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()){
            return "/company/editCompany";}
        companyService.updateCompany(company, id);
        return "redirect:/companies";

    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        companyService.deleteCompanyById(id);
        return "redirect:/companies";
    }
}
