package fr.icdc.dei.todolist.service;

import fr.icdc.dei.todolist.persistence.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {

	List<Task> list();

	Task add(Task task);

	List<Task> listByUser(long userId);



    void finishTask(Task task);


    void finishTasksOfUserEndingInPeriod(long userId, Date beginningDate, Date endingDate);

    List<Task> listUnfinishedTasksOfUserEndingInPeriod(long userId, Date beginningDate, Date endingDate);

    List<Task> listUnfinishedTasksOfUser(long userId);
}
