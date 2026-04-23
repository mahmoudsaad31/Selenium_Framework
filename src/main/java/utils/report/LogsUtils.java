package utils.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtils {


    public static final String LOGS_PATH = "test-outputs/Logs";

    private LogsUtils() {
        super();
    }

    public static Logger logger() {
        // layout.pattern like
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    // logging for console and report
    public static void info(String... message) {logger().info(String.join(" ", message));}

    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }


}
