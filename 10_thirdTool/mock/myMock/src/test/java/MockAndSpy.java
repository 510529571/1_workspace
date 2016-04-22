import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

/**
 * User: hanwei
 * Date: 15-11-6
 * Time: 下午4:31
 */
public class MockAndSpy {
    class Student {
        public String getName() {
            return "张三";
        }

        public int getAge() {
            return 25;
        }
    }

    @Test
    public void testMock() {
        Student student = mock(Student.class);

        when(student.getName()).thenReturn("李四");

        assertEquals(student.getName(), "李四");

        assertEquals(student.getAge(),0);
    }

    public void testSpy(){
        Student student = spy(new Student());

        when(student.getName()).thenReturn("李四");

        assertEquals(student.getName(), "李四");

        assertEquals(student.getAge(),25);
    }

}
