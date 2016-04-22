package own.hhw.lang;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        List<String> dates = getDate(sdf.parse("20151031"), sdf.parse("20151103"));
        for (String date:dates){
            System.out.println(date);
        }
    }

    public List<String> getDate(Date startDate, Date endDate) {
        List<String> dates = new ArrayList<String>();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);
        dates.add(df.format(startDate));
        if (startDate.getTime() != endDate.getTime()) {
            dates.add(df.format(endDate));
        }
        while (true) {
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {//TODO 转数组或是集合，楼主看着写吧
                dates.add(df.format(startCalendar.getTime()));
            } else {
                break;
            }
        }
        return dates;
    }
}
