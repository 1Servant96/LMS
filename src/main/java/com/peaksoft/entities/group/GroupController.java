package com.peaksoft.entities.group;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Controller
@RequestMapping
public class GroupController {
    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public String getGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "group/getGroups";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", groupService.getGroupById(id));
        return "group/show";
    }

    @GetMapping("/new")
    public String newGroup(@ModelAttribute("person") Group group) {
//        model.addAttribute("person", new Person());
        return "group/new";
    }

    @PostMapping
    public String create(@ModelAttribute("group") /*@Valid*/ Group group, Long id /*BindingResult bindingResult*/) {
//        if (bindingResult.hasErrors()){
//            return "/people/new";}
        groupService.saveGroup(group, id);
        return "redirect:/group";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", groupService.getGroupById(id));
        return "group/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Group group,/*, BindingResult bindingResult,*/ @PathVariable("id") Long id) {
//        if(bindingResult.hasErrors()){
//            return "/people/edit";}
        groupService.updateGroup(group, id);
        return "redirect:/group";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        groupService.deleteGroupById(id);
        return "redirect:/group";
    }

}
