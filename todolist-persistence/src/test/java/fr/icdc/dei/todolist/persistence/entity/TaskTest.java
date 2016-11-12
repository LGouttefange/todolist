package fr.icdc.dei.todolist.persistence.entity;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TaskTest {

	private static final int ENDING_AND_BEGINNING_MONTH_DATE = 0;
	private static final int ENDING_AND_BEGINNING_YEAR_DATE = 2017;
	private static final String EMPTY_STRING = "";
	private static final String TASK_LABEL = "label";
	private static Calendar calendar = GregorianCalendar.getInstance();
	
	private static final Task task = new Task();
	
	@BeforeClass
	public static void setUp() {
		task.setLabel(TASK_LABEL);
	}

	@Test
	public void testTaskHasLabel() {
		assertNotNull(task.getLabel());
	}
	
	@Test
	public void testTaskIsEmpty() {
		task.setLabel(EMPTY_STRING);
		assertTrue(EMPTY_STRING.equals(task.getLabel()));
	}
	
	@Test
	public void testTaskIsNotEmpty() {
		assertFalse(EMPTY_STRING.equals(task.getLabel()));
	}
	
	@Test
	public void testTaskHasBeginningDate() {
		task.setBeginningDate(new Date());
		assertNotNull(task.getBeginningDate());
	}
	
	@Test
	public void testTaskHasEndingDate(){
		task.setEndingDate(new Date());
		assertNotNull(task.getEndingDate());
	}
	
	@Test
	public void testTaskHasValidDuration(){
		calendar.set(ENDING_AND_BEGINNING_YEAR_DATE, ENDING_AND_BEGINNING_MONTH_DATE, 1);
		task.setBeginningDate(calendar.getTime());
		calendar.set(ENDING_AND_BEGINNING_YEAR_DATE, ENDING_AND_BEGINNING_MONTH_DATE,5);
		task.setEndingDate(calendar.getTime());
		assertTrue(task.getEndingDate().compareTo(task.getBeginningDate()) > 0);
	}

}
