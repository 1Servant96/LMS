package com.peaksoft.entities.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    public List<Group> getFullGroups(){
        return groupRepository.getFullGroups();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        return groupRepository.getAllGroups(courseId);
    }

    public void saveGroup(Group group, Long idOfCourse){
        groupRepository.saveGroup(group, idOfCourse);
    }
    public void deleteGroupById(Long id){
        groupRepository.deleteGroupById(id);
    }
    public Group getGroupById(Long id){
        return groupRepository.getGroupById(id);
    }
    @Override
    public void updateGroup(Group group, Long id) {
        groupRepository.updateGroup(group, id);
    }

    @Override
    public void assignGroup(Long idCourse, Long idGroup) throws Exception {
        groupRepository.assignGroup(idCourse, idGroup);
    }
}