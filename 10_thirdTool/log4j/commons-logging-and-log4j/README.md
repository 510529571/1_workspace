first, involve the commons-logging jar.


Acturally, this is enough to do the logging. Basically, two classes, they are Log and LogFactory.


see Hello class to know how to use them.

the commons logging will try to find the commons-logging.properties file to load the configuration,
like which implementation of the LogFactory it should choose. It will choose Log4JLogger if you have
the log4j library in your classpath. Otherwise, the default implementation is the JDK14Logger.

To use log4j, involve the log4j jar.

log4j needs a configuration file named 'log4j.properties'.

on the first line, DEBUG means the log level, which can be changed to something like 'trace', 'info', 'error', etc.
'file' and 'stdout' are two appenders to append the log output to file and system out.

you can change the name by change 'log4j.appender.file' to 'log4j.appender.A1', then you should change the 'file' in the first line to 'A1'