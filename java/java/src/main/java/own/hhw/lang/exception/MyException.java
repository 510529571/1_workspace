package own.hhw.lang.exception;

public class MyException extends Exception
{
	public MyException()
	{
		super();
	}

	public MyException(String msg)
	{
		super(msg);
	}

    //hhw:tag 这个构造方法很有用哦
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }
}
