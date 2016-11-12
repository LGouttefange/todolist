import fr.icdc.todolist.util.ProgressUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProgressUtilTest extends AbstractServiceTest {
    private static Date BASE_DATE;
    private static Date BASE_DATE_PLUS_TWO_DAYS;
    private static Date BASE_DATE_PLUS_FOUR_DAYS;
    private static Date BASE_DATE_MINUS_TWO_DAYS;

    @BeforeClass
    public static void setUp(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000,1,5);
        BASE_DATE = calendar.getTime();
        calendar.set(2000,1,7);
        BASE_DATE_PLUS_TWO_DAYS = calendar.getTime();
        calendar.set(2000,1,9);
        BASE_DATE_PLUS_FOUR_DAYS = calendar.getTime();
        calendar.set(2000,1,3);
        BASE_DATE_MINUS_TWO_DAYS = calendar.getTime();
    }

    @Test
    public  void testBaseDatePlusTwoDaysDurationIsTwoDays(){
        assertEquals(2, ProgressUtil.durationInDays(BASE_DATE,BASE_DATE_PLUS_TWO_DAYS) );
    }


    @Test
    public  void testBaseDateMinusTwoDaysDurationIsMinusTwo(){
        assertEquals(-2, ProgressUtil.durationInDays(BASE_DATE,BASE_DATE_MINUS_TWO_DAYS) );
    }


    @Test
    public  void testProgressIsHalfDurationAsRatio(){
        assertEquals(0.5, ProgressUtil.advancementRatio(BASE_DATE,BASE_DATE_PLUS_TWO_DAYS, BASE_DATE_PLUS_FOUR_DAYS), 0.01);
    }

    @Test
    public  void testProgressIsHalfDurationAPercent(){
        assertEquals(50, ProgressUtil.advancementRatioAsPercent(BASE_DATE,BASE_DATE_PLUS_TWO_DAYS, BASE_DATE_PLUS_FOUR_DAYS));
    }

}
