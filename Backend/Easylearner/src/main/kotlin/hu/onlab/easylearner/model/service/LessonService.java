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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void bookLesson(Integer lessonId, Integer studentId){
        StoredProcedureQuery query = em.createStoredProcedureQuery("BookLessonAsStudent");

        query.registerStoredProcedureParameter("LessonId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("NewId", Integer.class, ParameterMode.IN);

        query.setParameter("LessonId", lessonId);
        query.setParameter("NewId", studentId);

        query.execute();
        //BookLessonAsStudent(LessonId in NUMBER, NewId in NUMBER)

    }
}