package fr.icdc.dei.todolist.service.impl;

import fr.icdc.dei.todolist.persistence.dao.TaskDao;
import fr.icdc.dei.todolist.persistence.entity.Task;
import fr.icdc.dei.todolist.service.TaskService;
import fr.icdc.dei.todolist.util.ProgressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        if (task.getUser().getTasks().size() < 10)
            return taskDao.save(task);
        return null;
    }

    private boolean taskIsNotFinished(Task task) {
        return task.getEndingDate() == null;
    }

    @Override
    public List<Task> listByUser(long userId) {
        return taskDao.findAllByUserId(userId);
    }


    public void finishListOfTasks(List<Task> tasks) {
        for (Task task : tasks) {
            finishTask(task);
        }
    }

    @Override
    public void finishTask(Task task) {
        task.setEndingDate(Calendar.getInstance().getTime());
        taskDao.save(task);
    }

    @Override
    public void finishTasksOfUserEndingInPeriod(long userId, Date beginningDate, Date endingDate) {
        finishListOfTasks(listUnfinishedTasksOfUserEndingInPeriod(userId, beginningDate, endingDate));
    }

    @Override
    public List<Task> listUnfinishedTasksOfUserEndingInPeriod(long userId, Date beginningDate, Date endingDate) {

        return taskDao.findAllByUserId(userId).stream()
                .filter(task -> unfinishedTaskEndsInPeriod(beginningDate, endingDate, task))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> listUnfinishedTasksOfUser(long userId) {
        return taskDao.findAllByUserId(userId).stream()
                .filter(this::taskIsNotFinished)
                .collect(Collectors.toList());
    }

    private boolean unfinishedTaskEndsInPeriod(Date beginningDate, Date endingDate, Task task) {
        return taskIsNotFinished(task)
                && taskEstimatedEndIsInPeriod(beginningDate, endingDate, task);
    }

    private boolean taskEstimatedEndIsInPeriod(Date beginningDate, Date endingDate, Task task) {
        return beginningDate.before(task.getEstimatedEndingDate())
                && endingDate.after(task.getEstimatedEndingDate());
    }

    private int currentProgressOfTaskAsPercent(Task task) {
        return ProgressUtil.currentAdvancementRatioAsPercent(task.getBeginningDate(), task.getEstimatedEndingDate());
    }


    @Override
    public Map<Long, Integer> listCurrentProgressOfTasksMappedByTaskID(List<Task> tasks) {

        return tasks
                .stream()
                .collect(Collectors.toMap(Task::getId,
                        this::currentProgressOfTaskAsPercent));
    }


}
