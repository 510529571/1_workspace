import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

/**
 * User: hanwei
 * Date: 15-11-6
 * Time: ����4:31
 */
public class MockAndSpy {
    class Student {
        public String getName() {
            return "����";
        }

        public int getAge() {
            return 25;
        }
    }

    @Test
    public void testMock() {
        Student student = mock(Student.class);

        when(student.getName()).thenReturn("����");

        assertEquals(student.getName(), "����");

        assertEquals(student.getAge(),0);
    }

    public void testSpy(){
        Student student = spy(new Student());

        when(student.getName()).thenReturn("����");

        assertEquals(student.getName(), "����");

        assertEquals(student.getAge(),25);
    }

}
