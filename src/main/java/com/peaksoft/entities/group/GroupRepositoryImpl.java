package com.peaksoft.entities.group;

import com.peaksoft.entities.course.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Group> getAllGroups() {
        return entityManager.createQuery("select h from Group h", Group.class).getResultList();
    }

    public void saveGroup(Group group, Long idOfCourse) {
        Course course = entityManager.find(Course.class, idOfCourse);
        course.getGroups().add(group);
        group.addCourse(course);
        entityManager.merge(group);
        entityManager.merge(course);
    }

    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    public void deleteGroupById(Long id) {
        entityManager.remove(getGroupById(id));
    }

    public void updateGroup(Group updatedGroup, Long id) {
        Group groupToBeUpdated = entityManager.find(Group.class, id);
        groupToBeUpdated.setGroupName(updatedGroup.getGroupName());
        groupToBeUpdated.setCourses(updatedGroup.getCourses());
        groupToBeUpdated.setImage(updatedGroup.getImage());
        groupToBeUpdated.setStudentList(updatedGroup.getStudentList());
        groupToBeUpdated.setDateOfStart(updatedGroup.getDateOfStart());
        entityManager.merge(groupToBeUpdated);
    }
}
