package sample.hawk.com.mybasicappcomponents.utils.logger2;

public class Logger {
    private static final String TAG_PACKAGE = "MyBasicAppComponents";
    private static LoggerBase logger = LoggerFactory.getLogger(TAG_PACKAGE);
    public static final int MAX_LOG_STRING_LENGTH = 200;

    public static final boolean LOGD = LoggerBase.LOCAL_DEBUG_FLAG;

    public static String getLogTag(Class<?> clz) {
        return LoggerFactory.getValidName(TAG_PACKAGE + clz.getSimpleName());
    }

    public static void v(String tag, String msg) {
        logger.v(tag, msg);
    }

    public static void v(String tag, String format, Object... args) {
        logger.v(tag, String.format(format, args));
    }

    public static void d(String tag, String msg) {
        logger.d(tag, msg);
    }

    public static void d(String tag, String format, Object... args) {
        logger.d(tag, format, args);
    }

    public static void d(String tag, String msg, Throwable tr) {
        logger.d(tag, tr, msg);
    }

    public static void i(String tag, String msg) {
        logger.i(tag, msg);
    }

    public static void i(String tag, String format, Object... args) {
        logger.i(tag, format, args);
    }

    public static void w(String tag, String msg) {
        logger.w(tag, msg);
    }

    public static void w(String tag, String format, Object... args) {
        logger.w(tag, format, args);
    }

    public static void w(String tag, String msg, Throwable tr) {
        logger.w(tag, tr, msg);
    }

    public static void w(String tag, Throwable tr, String format, Object... args) {
        logger.w(tag, tr, format, args);
    }

    public static void e(String tag, String msg) {
        logger.e(tag, msg);
    }

    public static void e(String tag, String format, Object... args) {
        logger.e(tag, format, args);
    }

    public static void e(String tag, String msg, Throwable tr) {
        logger.e(tag, tr, msg);
    }

    public static void e(String tag, Throwable tr, String format, Object... args) {
        logger.e(tag, tr, format, args);
    }

    public static String showStack(int level, String tag) {
        return logger.showStack(tag, level);
    }

    public static void traceBegin(String name) {
        logger.traceBegin(name);
    }

    public static void traceEnd() {
        logger.traceEnd();
    }
}
