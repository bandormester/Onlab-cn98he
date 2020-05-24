package hu.onlab.easylearner.model.service;

import com.sun.xml.bind.v2.model.core.ID;
import hu.onlab.easylearner.model.dto.LessonWithNamesDto;
import hu.onlab.easylearner.model.entities.LearnersKT;
import hu.onlab.easylearner.model.entities.Lesson;
import hu.onlab.easylearner.model.entities.Level;
import hu.onlab.easylearner.model.entities.Topic;
import hu.onlab.easylearner.model.repository.LearnerRepository;
import hu.onlab.easylearner.model.repository.LessonRepository;
import hu.onlab.easylearner.model.repository.LevelRepository;
import hu.onlab.easylearner.model.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class LessonService {
    LessonRepository lessonRepository;
    LearnerRepository learnerRepository;
    TopicRepository topicRepository;
    LevelRepository levelRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Lesson> findAllQueries(){
        return lessonRepository.findAll();
    }

    public List<LessonWithNamesDto> findAllWithNamesQuery(){
        List<LessonWithNamesDto> result = new ArrayList<LessonWithNamesDto>();
        List<Lesson> lessons = lessonRepository.findAll();
        LessonWithNamesDto help;

        for(Lesson l : lessons){
            Optional<LearnersKT> teacher = learnerRepository.findById(l.getTeacherId());

            String studentName = "";
            if(l.getStudentId()!=null){
                Optional<LearnersKT> student = learnerRepository.findById(l.getStudentId());
                if(student.isPresent()) studentName = student.get().getName();
            }

            Optional<Topic> topic = topicRepository.findById(l.getTopicId());
            Optional<Level> level = levelRepository.findById(l.getLevelId());
            if(teacher.isPresent()  && topic.isPresent() && level.isPresent()) {
                help = new LessonWithNamesDto(
                        l.getId(),
                        teacher.get().getUserId(),
                        l.getStudentId()==null?0:l.getStudentId(),
                        teacher.get().getName(),
                        studentName,
                        l.getInfo(),
                        l.getStartTime(),
                        l.getEndTime(),
                        l.getLongitude(),
                        l.getLatitude(),
                        l.getPayment(),
                        level.get().getName(),
                        topic.get().getName());
                result.add(help);
            }
        }
        return result;
    }

    public List<LessonWithNamesDto> findLessonAsTeacher(Integer teacherId){
        List<LessonWithNamesDto> result = new ArrayList<LessonWithNamesDto>();
        List<Lesson> lessons = lessonRepository.findAll();
        LessonWithNamesDto help;

        for(Lesson l : lessons) {
            if(l.getTeacherId() == null) {

                Optional<LearnersKT> student = learnerRepository.findById(l.getStudentId());
                Optional<Topic> topic = topicRepository.findById(l.getTopicId());
                Optional<Level> level = levelRepository.findById(l.getLevelId());

                if(student.isPresent()  && topic.isPresent() && level.isPresent() && !l.getStudentId().equals(teacherId)) {
                    help = new LessonWithNamesDto(
                            l.getId(),
                            0,
                            student.get().getUserId(),
                            "",
                            student.get().getName(),
                            l.getInfo(),
                            l.getStartTime(),
                            l.getEndTime(),
                            l.getLongitude(),
                            l.getLatitude(),
                            l.getPayment(),
                            level.get().getName(),
                            topic.get().getName());

                    result.add(help);
                }
            }
        }

        return result;
    }

    public List<LessonWithNamesDto> findLessonAsStudent(Integer studentId){
        List<LessonWithNamesDto> result = new ArrayList<LessonWithNamesDto>();
        List<Lesson> lessons = lessonRepository.findAll();
        LessonWithNamesDto help;

        for(Lesson l : lessons) {
            if(l.getStudentId() == null) {

                Optional<LearnersKT> teacher = learnerRepository.findById(l.getTeacherId());
                Optional<Topic> topic = topicRepository.findById(l.getTopicId());
                Optional<Level> level = levelRepository.findById(l.getLevelId());

                if(teacher.isPresent()  && topic.isPresent() && level.isPresent() && !l.getTeacherId().equals(studentId)) {
                    help = new LessonWithNamesDto(
                            l.getId(),
                            teacher.get().getUserId(),
                            0,
                            teacher.get().getName(),
                            "",
                            l.getInfo(),
                            l.getStartTime(),
                            l.getEndTime(),
                            l.getLongitude(),
                            l.getLatitude(),
                            l.getPayment(),
                            level.get().getName(),
                            topic.get().getName());

                    result.add(help);
                }
            }
        }

        return result;
    }

    public void addLessonAsTeacher(Integer idOfTeacher, String info, Long startTime , Integer paymentValue, Integer idOfTopic, Integer idOfLevel){
        StoredProcedureQuery query = em.createStoredProcedureQuery("AddLessonAsTeacher");

        query.registerStoredProcedureParameter("idOfTeacher", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("lessonInfo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("lessonStartTime", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("paymentValue", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idOfLevel", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idOfTopic", Integer.class, ParameterMode.IN);

        query.setParameter("idOfTeacher", idOfTeacher);
        query.setParameter("lessonInfo", info);
        query.setParameter("lessonStartTime", startTime);
        query.setParameter("paymentValue", paymentValue);
        query.setParameter("idOfLevel", idOfLevel);
        query.setParameter("idOfTopic", idOfTopic);


        query.execute();
    }

    public void addLessonAsStudent(Integer idOfStudent, String info, Long startTime , Integer paymentValue, Integer idOfTopic, Integer idOfLevel){
        StoredProcedureQuery query = em.createStoredProcedureQuery("AddLessonAsStudent");

        query.registerStoredProcedureParameter("idOfStudent", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("lessonInfo", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("lessonStartTime", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("paymentValue", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idOfLevel", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idOfTopic", Integer.class, ParameterMode.IN);

        query.setParameter("idOfStudent", idOfStudent);
        query.setParameter("lessonInfo", info);
        query.setParameter("lessonStartTime", startTime);
        query.setParameter("paymentValue", paymentValue);
        query.setParameter("idOfLevel", idOfLevel);
        query.setParameter("idOfTopic", idOfTopic);


        query.execute();
    }

    public void bookLessonAsStudent(Integer lessonId, Integer studentId){
        StoredProcedureQuery query = em.createStoredProcedureQuery("BookLessonAsStudent");

        query.registerStoredProcedureParameter("LessonId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("NewId", Integer.class, ParameterMode.IN);

        query.setParameter("LessonId", lessonId);
        query.setParameter("NewId", studentId);

        query.execute();
    }

    public void bookLessonAsTeacher(Integer lessonId, Integer teacherId){
        StoredProcedureQuery query = em.createStoredProcedureQuery("BookLessonAsTeacher");

        query.registerStoredProcedureParameter("LessonId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("NewId", Integer.class, ParameterMode.IN);

        query.setParameter("LessonId", lessonId);
        query.setParameter("NewId", teacherId);

        query.execute();
    }

    public List<LessonWithNamesDto> findBookedLessons(Integer id) {
        List<LessonWithNamesDto> result = new ArrayList<>();
        List<Lesson> lessons = lessonRepository.findAll();

        for(Lesson l : lessons){
            if(l.getStudentId()!= null && l.getTeacherId()!=null){
                if(l.getTeacherId().equals(id) || l.getStudentId().equals(id)){
                    Optional<LearnersKT> teacher = learnerRepository.findById(l.getTeacherId());
                    Optional<LearnersKT> student = learnerRepository.findById(l.getStudentId());
                    Optional<Topic> topic = topicRepository.findById(l.getTopicId());
                    Optional<Level> level = levelRepository.findById(l.getLevelId());

                    if(teacher.isPresent() && student.isPresent() && topic.isPresent() && level.isPresent()){
                        Date date = Calendar.getInstance().getTime();
                        date.setYear(date.getYear()+1900);
                        date.setHours(date.getHours()+1);
                        if(l.getStartTime() > date.getTime()){
                        result.add(new LessonWithNamesDto(
                                l.getId(),
                                teacher.get().getUserId(),
                                student.get().getUserId(),
                                teacher.get().getName(),
                                student.get().getName(),
                                l.getInfo(),
                                l.getStartTime(),
                                l.getEndTime(),
                                l.getLongitude(),
                                l.getLatitude(),
                                l.getPayment(),
                                level.get().getName(),
                                topic.get().getName()
                        ));}
                    }
                }
            }
        }
        return result;
    }

    @Transactional
    public void cancelLesson(Integer lessonId, Integer cancellerId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);

        if(lesson.isPresent()){
            System.out.println(lessonId +" --- "+ cancellerId);
            if(cancellerId.equals(lesson.get().getOwnerId())){
                System.out.println("deleted");
                em.createQuery("DELETE FROM Lesson l WHERE l.Id = :id").setParameter("id",lessonId).executeUpdate();
            }else{
                if(lesson.get().getTeacherId()!=null){
                if(cancellerId.equals(lesson.get().getTeacherId())){
                    System.out.println("teacherid");
                    em.createQuery("UPDATE Lesson l SET l.TeacherId = null WHERE l.Id = :id").setParameter("id",lessonId).executeUpdate();
                }else {
                    System.out.println("studentid");
                    em.createQuery("UPDATE Lesson l SET l.StudentId = null WHERE l.Id = :id").setParameter("id",lessonId).executeUpdate();
                }}else em.createQuery("UPDATE Lesson l SET l.StudentId = null WHERE l.Id = :id").setParameter("id",lessonId).executeUpdate();
            }
        }
    }

    public List<LessonWithNamesDto> findFinishedLessons(Integer id) {
        List<LessonWithNamesDto> result = new ArrayList<>();
        List<Lesson> lessons = lessonRepository.findAll();

        for(Lesson l : lessons){
            if(l.getStudentId()!= null && l.getTeacherId()!=null){
                if(l.getTeacherId().equals(id) || l.getStudentId().equals(id)){
                    Optional<LearnersKT> teacher = learnerRepository.findById(l.getTeacherId());
                    Optional<LearnersKT> student = learnerRepository.findById(l.getStudentId());
                    Optional<Topic> topic = topicRepository.findById(l.getTopicId());
                    Optional<Level> level = levelRepository.findById(l.getLevelId());

                    if(teacher.isPresent() && student.isPresent() && topic.isPresent() && level.isPresent()){
                        Date date = Calendar.getInstance().getTime();
                        date.setYear(date.getYear()+1900);
                        date.setHours(date.getHours()+1);
                        if(l.getStartTime() <= date.getTime()){
                        result.add(new LessonWithNamesDto(
                                l.getId(),
                                teacher.get().getUserId(),
                                student.get().getUserId(),
                                teacher.get().getName(),
                                student.get().getName(),
                                l.getInfo(),
                                l.getStartTime(),
                                l.getEndTime(),
                                l.getLongitude(),
                                l.getLatitude(),
                                l.getPayment(),
                                level.get().getName(),
                                topic.get().getName()
                        ));}
                    }
                }
            }
        }

        return result;
    }

    public List<LessonWithNamesDto> findFreeLessonsAsTeacher(Integer id) {
        List<LessonWithNamesDto> result = new ArrayList<>();
        List<Lesson> lessons = lessonRepository.findAll();

        for(Lesson l : lessons){
            if(l.getStudentId()== null && l.getTeacherId()!=null && l.getStartTime()!=null){
                if(l.getTeacherId().equals(id)){
                    Optional<LearnersKT> teacher = learnerRepository.findById(l.getTeacherId());
                    Optional<Topic> topic = topicRepository.findById(l.getTopicId());
                    Optional<Level> level = levelRepository.findById(l.getLevelId());

                    if(teacher.isPresent() && topic.isPresent() && level.isPresent()){
                        Date date = Calendar.getInstance().getTime();
                        date.setYear(date.getYear()+1900);
                        date.setHours(date.getHours()+1);
                        if(l.getStartTime() > date.getTime()){
                            System.out.println(l.getStartTime() + " --- <= --- "+System.currentTimeMillis());
                            result.add(new LessonWithNamesDto(
                                    l.getId(),
                                    teacher.get().getUserId(),
                                    0,
                                    teacher.get().getName(),
                                    "",
                                    l.getInfo(),
                                    l.getStartTime(),
                                    l.getEndTime(),
                                    l.getLongitude(),
                                    l.getLatitude(),
                                    l.getPayment(),
                                    level.get().getName(),
                                    topic.get().getName()
                            ));}
                    }
            }}
        }

        return result;
    }

    public List<LessonWithNamesDto> findFreeLessonsAsStudent(Integer id) {
        List<LessonWithNamesDto> result = new ArrayList<>();
        List<Lesson> lessons = lessonRepository.findAll();

        for(Lesson l : lessons){
            if(l.getStudentId()!= null && l.getTeacherId()== null){
                if(l.getStudentId().equals(id)){
                Optional<LearnersKT> student = learnerRepository.findById(l.getStudentId());
                Optional<Topic> topic = topicRepository.findById(l.getTopicId());
                Optional<Level> level = levelRepository.findById(l.getLevelId());

                if(student.isPresent() && topic.isPresent() && level.isPresent()){
                    Date date = Calendar.getInstance().getTime();
                    date.setYear(date.getYear()+1900);
                    date.setHours(date.getHours()+1);
                    if(l.getStartTime() > date.getTime()){
                        result.add(new LessonWithNamesDto(
                                l.getId(),
                                0,
                                student.get().getUserId(),
                                "",
                                student.get().getName(),
                                l.getInfo(),
                                l.getStartTime(),
                                l.getEndTime(),
                                l.getLongitude(),
                                l.getLatitude(),
                                l.getPayment(),
                                level.get().getName(),
                                topic.get().getName()
                        ));}
                }
            }}
        }

        return result;
    }
}