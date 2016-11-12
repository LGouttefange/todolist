package fr.icdc.dei.todolist.persistence.dao;

import fr.icdc.dei.todolist.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDao extends JpaRepository<Task, Long>{

	Task findByLabel(String label);

	List<Task> findAllByUserIdAndCategoryId(long idUser, long idCategory);


    List<Task> findAllByUserId(long userId);
}
