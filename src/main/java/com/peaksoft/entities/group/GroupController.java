package com.peaksoft.entities.group;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public String getGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "group/groups";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "group/show";
    }

    @GetMapping("/new")
    public String newGroup(@ModelAttribute("group") Group group) {
//        model.addAttribute("person", new Person());
        return "group/newGroup";
    }

    @PostMapping
    public String create(@ModelAttribute("group") /*@Valid*/ Group group, Long id /*BindingResult bindingResult*/) {
//        if (bindingResult.hasErrors()){
//            return "/people/new";}
        groupService.saveGroup(group, id);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "group/editGroup";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()){
            return "/group/editGroup";}
        groupService.updateGroup(group, id);
        return "redirect:/groups";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        groupService.deleteGroupById(id);
        return "redirect:/groups";
    }

}
