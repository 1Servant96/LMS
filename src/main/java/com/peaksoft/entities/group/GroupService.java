package com.peaksoft.entities.group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    void deleteGroupById(Long id);
    void saveGroup(Group group, Long idOfCourse);
    void updateGroup(Group group, Long id);
}
