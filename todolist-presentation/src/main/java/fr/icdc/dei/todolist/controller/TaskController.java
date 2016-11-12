package fr.icdc.dei.todolist.controller;

import fr.icdc.dei.todolist.persistence.dao.UserDao;
import fr.icdc.dei.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	UserDao userDao;

	@RequestMapping(value={"/get","/"},method=RequestMethod.GET)
	public ModelAndView listTasks() {
		ModelAndView page = new ModelAndView("Tasks/List");
		page.addObject("tasks", taskService.list());
		return page;
	}

	@RequestMapping(value="/get/user",method=RequestMethod.GET)
	public ModelAndView formChooseUser() {
		ModelAndView page = new ModelAndView("Tasks/formChooseUser");
		page.addObject("users", userDao.findAll());
		return page;
	}


	@RequestMapping(value="/get/user", method = RequestMethod.POST)
	private ModelAndView listTasksBySelectedUser(@RequestParam long userId, @RequestParam Date beginningDate, @RequestParam Date endingDate) {
		ModelAndView page = new ModelAndView("Tasks/UnfinishedTasksOfUser");
		page.addObject("tasks",taskService.listUnfinishedTasksOfUserEndingInPeriod(userId,beginningDate, endingDate));
		page.addObject("userId",userId);
		return page;
	}

	@RequestMapping(value="/get/user-finishTasks-{userId}", method = RequestMethod.POST)
	private ModelAndView finishAllTasksOfSelectedUser(@PathVariable long userId) {
		taskService.finishAllTasksOfUser(userId);
		return new ModelAndView("redirect:" + "user");

	}




}
