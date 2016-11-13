package fr.icdc.dei.todolist.service;

import fr.icdc.dei.todolist.persistence.entity.Task;
import fr.icdc.dei.todolist.persistence.entity.User;
import fr.icdc.dei.todolist.persistence.entity.UserFree;
import org.apache.commons.collections.CollectionUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TaskServiceTest extends AbstractServiceTest {

    private static final String TASK_LABEL = "taskLabel";

    private final static Task task = new Task();
    private final static User user = new UserFree();
    private static final long ID_USER_WITH_TASKS_TO_FINISH = 4L;
    private static final long ID_USER_TEST_TASKS_IN_PERIOD = 3L;
    private static final Task TASK_IN_DATE_PERIOD = new Task();
    private static Date BEGINNING_DATE_OF_PERIOD;
    private static Date ENDING_DATE_OF_PERIOD;


    @Autowired
    private TaskService taskService;

    @BeforeClass
    public static void setUp() {
        task.setLabel(TASK_LABEL);
        user.setId(2L);
        task.setUser(user);

        TASK_IN_DATE_PERIOD.setId(10L);

        Calendar calendar = Calendar.getInstance();
        initBeginningDateOfPeriod(calendar);

        initEndingDateOfPeriod(calendar);
    }

    private static void initEndingDateOfPeriod(Calendar calendar) {
        calendar.set(2016, 10, 30);
        ENDING_DATE_OF_PERIOD = calendar.getTime();
    }

    private static void initBeginningDateOfPeriod(Calendar calendar) {
        calendar.set(2016, 10, 1);
        BEGINNING_DATE_OF_PERIOD = calendar.getTime();
    }


    @Test
    public void testListTasks() {
        assertTrue(CollectionUtils.isNotEmpty(taskService.list()));
    }

    @Test
    public void testAddTaskSucceedWithLessThanTenTasksForFreeUser() {
        assertNotNull(taskService.add(task));
    }

    @Test
    public void testListUnfinishedTasksInDatePeriodDifferentThanUnfinishedTasksList() {
        assertNotEquals(taskService.listUnfinishedTasksOfUserEndingInPeriod(ID_USER_TEST_TASKS_IN_PERIOD, BEGINNING_DATE_OF_PERIOD, ENDING_DATE_OF_PERIOD).size(),
                taskService.listUnfinishedTasksOfUser(ID_USER_TEST_TASKS_IN_PERIOD).size());
    }

    @Test
    public void testAllTasksEndingInPeriodTasksAreFinished() {
        for (Task task : taskService.listUnfinishedTasksOfUserEndingInPeriod(ID_USER_TEST_TASKS_IN_PERIOD, BEGINNING_DATE_OF_PERIOD, ENDING_DATE_OF_PERIOD)) {
            assertNull(task.getEndingDate());
        }
    }


    @Test
    public void testFinishAllTasksEndingInPeriodUserHasTasks(){
        assertNotEquals(0, taskService.listUnfinishedTasksOfUserEndingInPeriod(ID_USER_WITH_TASKS_TO_FINISH, BEGINNING_DATE_OF_PERIOD, ENDING_DATE_OF_PERIOD).size());
    }

    @Test
    public void testFinishAllTasksEndingInPeriodTasksAreFinished(){
        taskService.finishTasksOfUserEndingInPeriod(ID_USER_WITH_TASKS_TO_FINISH,BEGINNING_DATE_OF_PERIOD, ENDING_DATE_OF_PERIOD);
        assertEquals(0, taskService.listUnfinishedTasksOfUserEndingInPeriod(ID_USER_WITH_TASKS_TO_FINISH, BEGINNING_DATE_OF_PERIOD, ENDING_DATE_OF_PERIOD).size());
    }

}
