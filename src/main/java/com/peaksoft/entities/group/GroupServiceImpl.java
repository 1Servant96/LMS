package com.peaksoft.entities.group;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
//    @Autowired
    private GroupRepository groupRepository;
    public List<Group> getAllGroups(){
        return groupRepository.getAllGroups();
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
}