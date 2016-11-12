package fr.icdc.dei.todolist.service;

import fr.icdc.dei.todolist.persistence.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {

	List<Task> list();

	Task add(Task task);

    List<Task> listUnfinishedTasksOfUser(long userId);

	List<Task> listByUser(long userId);

    void finishAllTasksOfUser(long idUser);

	void finishTask(Task task);

	int getTaskProgressAsPercent(Task task);

	List<Task> listUnfinishedTasksOfUserInPeriod(long userId, Date beginningDate, Date endingDate);

	boolean taskIsInPeriod(Date beginningDate, Date endingDate, Task task);

	List<Task> listUnfinishedTasksOfUserEndingInPeriod(long userId, Date beginningDate, Date endingDate);
}
