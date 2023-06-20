package com.example.plannerscheduler.repository;

import com.example.plannerscheduler.enums.LessonType;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.models.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "select ls from Schedule ls where ls.group.name = :groupId and (ls.typeOfLesson = 'LECTURE' or ls.typeOfLesson = 'PRACTICAL' or ls.typeOfLesson = 'LABORATORY') order by ls.dayOfWeek ASC, ls.isEvenWeek ASC, ls.lessonOrder ASC ")
    List<Schedule> findAllByGroupIdOrderByDayOfWeekLessonOrder(String groupId);

    @Query(value = "select ls from Schedule ls where ls.creator.id = :teacherId and (ls.typeOfLesson = 'LECTURE' or ls.typeOfLesson = 'PRACTICAL' or ls.typeOfLesson = 'LABORATORY') order by ls.dayOfWeek ASC, ls.isEvenWeek ASC, ls.lessonOrder ASC ")
    List<Schedule> findAllByTeacherIdOrderByDayOfWeekLessonOrder(Long teacherId);

    @Query(value = "select ls from Schedule ls where ls.creator.id = :creatorId order by ls.dayOfWeek ASC, ls.isEvenWeek ASC, ls.lessonOrder ASC ")
    List<Schedule> findAllByCreatorIdOrdered(Long creatorId);

    @Query(value = "select ls from Schedule ls where ls.group.name = :groupId order by ls.dayOfWeek ASC, ls.isEvenWeek ASC, ls.lessonOrder ASC ")
    List<Schedule> findAllByGroupIdOrdered(String groupId);

    @Query(value = "select ls from Schedule ls join ls.attendees at where at.email = :attendeesEmail order by ls.dayOfWeek ASC, ls.isEvenWeek ASC, ls.lessonOrder ASC ")
    List<Schedule> findAllByAttendeesEmailOrdered(String attendeesEmail);

    @Query(value = "select ls from Schedule ls where ls.typeOfLesson = 'LECTURE' or ls.typeOfLesson = 'PRACTICAL' or ls.typeOfLesson = 'LABORATORY' order by ls.dayOfWeek ASC,ls.lessonOrder ASC, ls.isEvenWeek ASC, ls.group.id ASC")
    List<Schedule> getAllSchedule();

    @Query(value = "select case when count(ls)> 0 then true else false end from Schedule ls where ls.subject.id = :subjectId and " +
            "ls.creator.id = :teacherId and ls.group.id = :groupId and ls.dayOfWeek = :dayOfWeek and ls.lessonOrder = :lessonOrder and ls.isEvenWeek = :evenWeek and ls.typeOfLesson = :type")
    boolean existsBySubjectAndTeacherAndGroupAndDayOfWeekAndLessonOrderAndEvenWeekAndTypeOfLesson(Long subjectId, Long teacherId, Long groupId, Long dayOfWeek, Long lessonOrder, boolean evenWeek, LessonType type);

    @Query(value = "select ls from Schedule ls where ls.dayOfWeek = :dayOfWeek and ls.lessonOrder = :lessonOrder and ls.isEvenWeek = :evenWeek and ls.group.id = :groupId")
    Schedule findScheduleByDayOfWeekAndLessonOrderAndEvenWeekAndGroup(Long dayOfWeek,Long lessonOrder,boolean evenWeek,Long groupId);

    @Query(value = "select ls.subject from Schedule ls where ls.creator.id = :creatorId")
    List<Subject> findSubjectsfromScheduleByTeacher(Long creatorId);

    @Query(value = "select ls.group from Schedule ls where ls.creator.id = :creatorId")
    List<Group> findGroupsfromScheduleByTeacher(Long creatorId);

    @Query("select ls from Schedule ls where ls.typeOfLesson = 'LECTURE' or ls.typeOfLesson = 'PRACTICAL' or ls.typeOfLesson = 'LABORATORY' order by ls.dayOfWeek ASC,ls.lessonOrder ASC, ls.isEvenWeek ASC, ls.group.id ASC")
    Page<Schedule> findPageOfSchedule(Pageable pageable);
}
