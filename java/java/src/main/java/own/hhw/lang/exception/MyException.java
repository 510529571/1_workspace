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

    //hhw:tag ������췽��������Ŷ
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }
}
