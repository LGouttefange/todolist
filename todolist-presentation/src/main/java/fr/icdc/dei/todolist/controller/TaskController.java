package fr.icdc.dei.todolist.controller;

import fr.icdc.dei.todolist.persistence.dao.UserDao;
import fr.icdc.dei.todolist.persistence.entity.Task;
import fr.icdc.dei.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tasks")
@SessionAttributes("")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserDao userDao;

    private Date beginningDate;
    private Date endingDate;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView listTasks() {
        ModelAndView page = new ModelAndView("Tasks/List");
        page.addObject("tasks", taskService.list());
        return page;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView formChooseUser() {
        ModelAndView page = new ModelAndView("Tasks/formChooseUser");
        page.addObject("users", userDao.findAll());
        return page;
    }

    private ModelAndView listTasksWithCurrentProgress(List<Task> listTasks) {
        ModelAndView page = new ModelAndView("Tasks/ListUnfinishedTasksOfUser");
        page.addObject("tasks", listTasks);
        page.addObject("currentProgressOfTasks", taskService.listCurrentProgressOfTasksMappedByTaskID(listTasks));
        return page;
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    private ModelAndView listTasksOfUser(@PathVariable long userId) {
        return listTasksWithCurrentProgress(taskService.listByUser(userId));

    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, params = "beginningDate")
    private ModelAndView listTasksOfUserEndingInPeriod(@PathVariable long userId,
                                                       @RequestParam Date beginningDate,
                                                       @RequestParam Date endingDate) {

        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        return listTasksWithCurrentProgress(taskService.listUnfinishedTasksOfUserEndingInPeriod(userId, beginningDate, endingDate));
    }

    @RequestMapping(value = "/users/finishTasks/{userId}", method = RequestMethod.POST)
    private ModelAndView finishAllTasksOfSelectedUserEndingInPeriod(@PathVariable long userId) {
        taskService.finishTasksOfUserEndingInPeriod(userId, beginningDate, endingDate);
        return new ModelAndView("redirect:" + "../../user");

    }


}
