package fr.icdc.dei.todolist.service.impl;

import fr.icdc.dei.todolist.persistence.dao.TaskDao;
import fr.icdc.dei.todolist.persistence.entity.Task;
import fr.icdc.dei.todolist.service.TaskService;
import fr.icdc.todolist.util.ProgressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public List<Task> list() {
		return taskDao.findAll();
	}

	@Override
	public Task add(Task task) {
		if(task.getUser().getTasks().size() < 10)
			return taskDao.save(task);
		return null;
	}

	@Override
	public List<Task> listUnfinishedTasksOfUser(long userId) {
		return taskDao.findAllByUserId(userId).stream()
				.filter(task -> taskIsNotFinished(task))
				.collect(Collectors.toList());
	}

	private boolean taskIsNotFinished(Task task) {
		return task.getEndingDate() == null;
	}

	@Override
	public List<Task> listByUser(long userId) {
		return taskDao.findAllByUserId(userId);
	}

	@Override
	public void finishAllTasksOfUser(long idUser) {
		for(Task unfinishedTask : listUnfinishedTasksOfUser(idUser)){
			finishTask(unfinishedTask);
		}
	}

	@Override
	public void finishTask(Task task) {
		task.setEndingDate(Calendar.getInstance().getTime());
		taskDao.save(task);
	}

	@Override
	public int getTaskProgressAsPercent(Task task) {
		return ProgressUtil.currentAdvancementRatioAsPercent(task.getBeginningDate(), task.getEndingDate());
	}

	@Override
	public List<Task> listUnfinishedTasksOfUserInPeriod(long userId, Date beginningDate, Date endingDate) {
		return taskDao.findAllByUserId(userId).stream()
				.filter(task -> taskIsNotFinished(task)
						&& taskIsInPeriod(beginningDate, endingDate, task))
				.collect(Collectors.toList());
	}

	@Override
	public boolean taskIsInPeriod(Date beginningDate, Date endingDate, Task task) {
		return beginningDate.before(task.getBeginningDate())
				&& endingDate.after(task.getEstimatedEndingDate());
	}

	@Override
	public List<Task> listUnfinishedTasksOfUserEndingInPeriod(long userId, Date beginningDate, Date endingDate) {
		return taskDao.findAllByUserId(userId).stream()
				.filter(task -> taskIsNotFinished(task)
						&& taskEstimatedEndIsInPeriod(beginningDate, endingDate, task))
				.collect(Collectors.toList());
	}

	private boolean taskEstimatedEndIsInPeriod(Date beginningDate, Date endingDate, Task task) {
		return beginningDate.before(task.getEstimatedEndingDate())
				&& endingDate.after(task.getEstimatedEndingDate());
	}

}
