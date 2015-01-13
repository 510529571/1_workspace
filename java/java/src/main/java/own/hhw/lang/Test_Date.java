package own.hhw.lang;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test_Date {
    @Test
    public void date1() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		TimeZone zone = TimeZone.getTimeZone("GMT+8");// 补上时差
//		sdf.setTimeZone(zone);
        System.out.println(sdf.format(date));
    }

    @Test
    public void test2() throws ParseException {
        //hhw:test
        Date now = new Date();

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm");
        Date shutDate = sdf.parse(sdf0.format(now) + " 15:00");
        if (now.after(shutDate))
            System.out.println(sdf0.format(DateUtils.addDays(now, 1)));
        else
            System.out.println(sdf0.format(now));
    }
}
