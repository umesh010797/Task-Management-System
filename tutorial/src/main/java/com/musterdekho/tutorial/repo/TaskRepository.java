package com.musterdekho.tutorial.repo;

import com.musterdekho.tutorial.dto.TaskDto;
import com.musterdekho.tutorial.entity.Task;
import com.musterdekho.tutorial.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

//    @Query(value = "select new com.musterdekho.tutorial.dto.TaskDto(com.id,com.title,com.description,com.dueDate,com.status,com.assignTo) from Task com")
//    List<Task> getAllTask();
    @Query("SELECT NEW com.musterdekho.tutorial.dto.TaskDto(com.id, com.title, com.description, com.dueDate, com.status, com.assignTo.name" +
            ") " +
        "FROM Task com")
    List<TaskDto> getAllTask();

    @Transactional
    @Modifying
    @Query("INSERT INTO Task (title, description, dueDate, status, assignTo) " +
            "SELECT  :title, :description, :dueDate, :status, u " +
            "FROM User u " +
            "WHERE u.id = :assignTo")
    void createTask(
            @Param("title") String title,
            @Param("description") String description,
            @Param("status") Boolean status,
            @Param("dueDate") LocalDate dueDate,
            @Param("assignTo") Integer assignTo);

    @Transactional
    @Modifying
    @Query("UPDATE Task t " +
            "SET t.title = :title, " +
            "    t.description = :description, " +
            "    t.status = :status, " +
            "    t.dueDate = :dueDate, " +
            "    t.assignTo = (SELECT u FROM User u WHERE u.id = :assignTo) " +
            "WHERE t.id = :id")
    void updateTask(
            @Param("id") Integer id,
            @Param("title") String title,
            @Param("description") String description,
            @Param("status") Boolean status,
            @Param("dueDate") LocalDate dueDate,
            @Param("assignTo") Integer assignTo);




    List<Task> findByTitle(String title);

    List<Task> findByDescription(String description);

    List<Task> findByStatus(Boolean status);

    List<Task> findByDueDate(Date dueDate);
}

