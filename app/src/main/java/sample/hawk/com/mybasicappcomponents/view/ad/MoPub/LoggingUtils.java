package sample.hawk.com.mybasicappcomponents.view.ad.MoPub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;

import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Used to intercept logs so that we can view logs at a lower level
 * than Verbose (ie. Level.FINEST). This will show a toast when we
 * receive a matching error from the mopub sdk.
 */
public class LoggingUtils {

    static final String LOGTAG = "MoPub Sample App";

    private LoggingUtils() {
    }

    private static boolean sEnabled;

    /**
     * Makes it so that this app can intercept Level.FINEST log messages.
     * This is not thread safe.
     *
     * @param context Needs a context to send toasts.
     */
    static void enableCanaryLogging(@NonNull final Context context) {
        if (sEnabled) {
            return;
        }

        // This makes sure the static block in MoPubLog is executed before
        // LogManager#getLogManager is called.
        MoPubLog.c("Canary level logging enabled");

        final Handler handler = new SampleAppLogHandler(context.getApplicationContext());
        final Logger logger = getLogger();
        logger.addHandler(handler);

        sEnabled = true;
    }

    private static Logger getLogger() {
        return LogManager.getLogManager().getLogger(MoPubLog.LOGGER_NAMESPACE);
    }

    private static class SampleAppLogHandler extends Handler {

        @NonNull
        private final Context mContext;

        protected SampleAppLogHandler(@NonNull final Context context) {
            super();
            mContext = context;
        }

        @Override
        public void publish(final LogRecord logRecord) {
            if (logRecord == null) {
                return;
            }
            // Toasts the warmup message if X-Warmup flag is set to 1
            if (MoPubErrorCode.WARMUP.toString().equals(logRecord.getMessage())) {
                logToast(mContext, MoPubErrorCode.WARMUP.toString());
            }
            // Toasts the no connection message if a native ad failed due to no internet
            if (MoPubErrorCode.NO_CONNECTION.toString().equals(logRecord.getMessage())) {
                logToast(mContext, MoPubErrorCode.NO_CONNECTION.toString());
            }
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() throws SecurityException {
        }

        static void logToast(Context context, String message) {
            Log.d(LOGTAG, message);

            if (context != null) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

