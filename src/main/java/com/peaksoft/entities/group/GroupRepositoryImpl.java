package com.peaksoft.entities.group;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.instructor.Instructor;
import com.peaksoft.entities.student.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Repository
public class GroupRepositoryImpl implements GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Group> getFullGroups() {
        return entityManager.createQuery(" select g from Group g").getResultList();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        List<Group> groupList = entityManager.find(Course.class, courseId).getGroups();
        groupList.forEach(System.out::println);
        return groupList;

    }

    @Override
    public void saveGroup(Group group, Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(course);
//        entityManager.merge(group);
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        Group group1 = getGroupById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public void deleteGroupById(Long id) {
            Group group = entityManager.find(Group.class, id);
            for (Course c : group.getCourses()) {
                c.getGroups().remove(group);
            }
        for (Course c: group.getCourses()) {
            for (Student student: group.getStudents()) {
                for (Instructor i: c.getInstructors()) {
                    i.minus();
                }
            }
        }
        group.getStudents().forEach(x-> entityManager.remove(x));
            group.setCourses(null);
            entityManager.remove(group);
        }


    @Override
    public void assignGroup(Long courseId, Long groupId) throws IOException {
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);
        if (course.getGroups() != null) {
            for (Group g : course.getGroups()) {
                if (Objects.equals(g.getId(), groupId)) {
                    throw new IOException("We have such group!!!");
                }
            }
        }
        group.addCourse(course);
        course.addGroup(group);
        entityManager.merge(group);
        entityManager.merge(course);
    }
}
