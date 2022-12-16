package com.peaksoft.entities.group;

import java.util.List;

public interface GroupRepository {
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    void deleteGroupById(Long id);
    void saveGroup(Group group, Long id);
    void updateGroup(Group group, Long id);

}
