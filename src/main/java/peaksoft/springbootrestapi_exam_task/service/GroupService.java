package peaksoft.springbootrestapi_exam_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springbootrestapi_exam_task.dto.request.CompanyRequest;
import peaksoft.springbootrestapi_exam_task.dto.request.GroupRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.CompanyResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.CourseResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.GroupResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.entity.Student;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.CompanyNotFoundException;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.GroupNotFoundException;
import peaksoft.springbootrestapi_exam_task.repository.CourseRepository;
import peaksoft.springbootrestapi_exam_task.repository.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(
                () -> new GroupNotFoundException(
                        "Group with id = " + groupId + " not found!"
                )
        );
    }

    public List<GroupResponse> findAll() {
        return groupRepository.findAll().stream().map(GroupResponse::from).collect(Collectors.toList());
    }

    public GroupResponse save(GroupRequest groupRequest) {
        Group group = new Group(
                groupRequest.getGroupName(),
                groupRequest.getDateOfStart(),
                groupRequest.getDateOfFinish());
        List<Course> courseList = courseRepository.findAllById(groupRequest.getCourseId());
        group.setCourses(courseList);

//                groupRequest.getCourses(),
//                groupRequest.getStudents()

       groupRepository.save(group);
       return GroupResponse.from(group);
    }

    public GroupResponse findById(Long groupId) {
        return GroupResponse.from(groupRepository.findById(groupId).get());
    }
    @Transactional
    public GroupResponse updateById(Long groupId, GroupRequest groupRequest) {

        Group group = getGroupById(groupId);

        //name
        String currentName = group.getGroupName();
        String newName = groupRequest.getGroupName();

        if (currentName != null && !currentName.equals(newName)) {
            group.setGroupName(newName);
        }

        //dateOfStart
        String currentDateOfStart = group.getDateOfStart();
        String newDateOfStart = groupRequest.getDateOfStart();

        if (newDateOfStart != null && !newDateOfStart.equals(currentDateOfStart)) {
            group.setDateOfStart(newDateOfStart);
        }

        //dateOfFinish
        String currentDateOfFinish = group.getDateOfFinish();
        String newDateOfFinish = groupRequest.getDateOfFinish();

        if (newDateOfFinish != null && !newDateOfFinish.equals(currentDateOfFinish)) {
            group.setDateOfFinish(newDateOfFinish);
        }
//        //courses
//        List<Course> currentCourses = group.getCourses();
//        List<Course> newCourses = groupRequest.getCourses();
//
//        if (newCourses != null && !newCourses.equals(currentCourses)) {
//            group.setCourses(newCourses);
//        }
//        //students
//        List<Student> currentStudents = group.getStudents();
//        List<Student> newStudents = groupRequest.getStudents();
//
//        if (newStudents != null && !newStudents.equals(currentStudents)) {
//            group.setStudents(newStudents);
//
//        }
        return GroupResponse.from(group);

    }

    public SimpleResponse deleteById(Long groupId) {
        boolean exists = groupRepository.existsById(groupId);

        if (!exists) {
            throw new GroupNotFoundException(
                    "Group with id = " + groupId + " not found!"
            );
        }
        groupRepository.deleteById(groupId);
        return new SimpleResponse("DELETED!!!", "Group successfully deleted!!!");
    }
}
