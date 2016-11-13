
-- TaskServiceTest
-- testlistThreeLastTasks
-- ID_ACCOUNT_TYPE = 1L
INSERT INTO TA_ACCOUNT_TYPE(label) VALUES('FREE');
-- ID_USER = 1L
INSERT INTO user(name, USER_TYPE) VALUES('testNameUser', 'USER_FREE');
-- ID_CATEGORY = 1L
INSERT INTO category(name) VALUES('testNameCategory');
-- ID_TASK = 1L
INSERT INTO task(label, id_user) VALUES('testFind3lastTask1DB', 1);
-- ID_TASK = 2L
INSERT INTO task(label, id_user) VALUES('testFind3lastTask2DB', 1);
-- ID_TASK = 3L
INSERT INTO task(label, id_user) VALUES('testFind3lastTask3DB', 1);

--  testListUnfinishedTasksDifferentThanListTasks
-- ID_TASK = 4L
INSERT INTO task(label, id_user, endingDate) VALUES('testFinishedTask',  1,  '2010-04-02 15:28:22' );


-- testAddTaskWithLessThanTenTasksForFreeUser
-- ID_USER = 2L
INSERT INTO user(name, USER_TYPE) VALUES('AddTaskWithLessThanTenTasksForFreeUser', 'USER_FREE');



--  testListUnfinishedTasksInDatePeriodDifferentThanUnfinishedTasksList
--  testAllTasksEndingInPeriodTasksAreFinished
-- ID_USER = 3L
INSERT INTO user(name, USER_TYPE) VALUES('userWith', 'USER_FREE');
-- ID_TASK = 9L
INSERT INTO task(label, id_user,beginningDate,  estimatedEndingDate) VALUES('testListUnfinishedTasksInDatePeriodDifferentThanUnfinishedTasksList', 3, '2000-01-01 15:28:22', '2000-01-02 15:28:22' );
-- ID_TASK = 10L
INSERT INTO task(label, id_user,beginningDate,  estimatedEndingDate) VALUES('testListUnfinishedTasksInDatePeriodDifferentThanUnfinishedTasksList', 3, '2016-11-10 15:28:22', '2016-11-20 15:28:22' );
-- ID_TASK = 11L
INSERT INTO task(label, id_user,beginningDate,  estimatedEndingDate) VALUES('testListUnfinishedTasksInDatePeriodDifferentThanUnfinishedTasksList', 3, '2016-11-01 15:28:22', '2016-11-25 15:28:22' );



--  testFinishAllTasksEndingInPeriodTasksAreFinished
-- ID_USER = 4L
INSERT INTO user(name, USER_TYPE) VALUES('userWithTasksToFinish', 'USER_FREE');
-- ID_TASK = 9L
INSERT INTO task(label, id_user,beginningDate,  estimatedEndingDate) VALUES('testFinishAllTasksOfUserFinished', 4, '2000-01-01 15:28:22', '2000-01-02 15:28:22' );
-- ID_TASK = 10L
INSERT INTO task(label, id_user,beginningDate,  estimatedEndingDate) VALUES('testFinishAllTasksOfUserFinished', 4, '2016-11-10 15:28:22', '2016-11-20 15:28:22' );
-- ID_TASK = 11L
INSERT INTO task(label, id_user,beginningDate,  estimatedEndingDate) VALUES('testFinishAllTasksOfUserFinished', 4, '2016-11-01 15:28:22', '2016-11-30 15:28:22' );