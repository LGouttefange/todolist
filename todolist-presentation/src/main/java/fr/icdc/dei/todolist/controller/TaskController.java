package fr.icdc.dei.todolist.controller;

import fr.icdc.dei.todolist.persistence.dao.UserDao;
import fr.icdc.dei.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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

    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public ModelAndView listTasks() {
        ModelAndView page = new ModelAndView("Tasks/List");
        page.addObject("tasks", taskService.list());
        return page;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView formChooseUser() {
        ModelAndView page = new ModelAndView("Tasks/formChooseUser");
        page.addObject("users", userDao.findAll());
        return page;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    private ModelAndView listTasksOfUserEndingInPeriod(@RequestParam long userId, @RequestParam Date beginningDate, @RequestParam Date endingDate) {
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;

        ModelAndView page = new ModelAndView("Tasks/UnfinishedTasksOfUser");
        page.addObject("tasks", taskService.listUnfinishedTasksOfUserEndingInPeriod(userId, beginningDate, endingDate));
        page.addObject("userId", userId);
        return page;
    }

    @RequestMapping(value = "/user/finishTasks/{userId}", method = RequestMethod.POST)
    private ModelAndView finishAllTasksOfSelectedUserEndingInPeriod(@PathVariable long userId) {
        taskService.finishTasksOfUserEndingInPeriod(userId, beginningDate, endingDate);
        return new ModelAndView("redirect:" + "../../user");

    }


}
