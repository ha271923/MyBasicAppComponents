package sample.hawk.com.mybasicappcomponents.ipc.asyncchannel;

public class BidirectionalAsyncChannel {
    // AsyncChannel 不能在APP上使用, 因為他需要framework.jar
}

/*
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

// Android 如何使用internal 和 hidden API?
// https://blog.csdn.net/github_24235341/article/details/41743751
// link lib with system/framework/framework.jar
import com.android.internal.util.AsyncChannel;

import static junit.framework.Assert.assertEquals;


// Provides an AsyncChannel interface that implements the connection initiating half of a
// bidirectional channel as described in {@link com.android.internal.util.AsyncChannel}.
// http://zenki2001cn.github.io/Wiki/Android/%E5%BC%82%E6%AD%A5%E9%80%9A%E9%81%93%E6%9C%BA%E5%88%B6.html

public class BidirectionalAsyncChannel {
    private static final String TAG = "BidirectionalAsyncChannel";

    private AsyncChannel mChannel;
    public enum ChannelState { DISCONNECTED, HALF_CONNECTED, CONNECTED, FAILURE };
    private ChannelState mState = ChannelState.DISCONNECTED;

    public void assertConnected() {
        assertEquals("AsyncChannel was not fully connected", ChannelState.CONNECTED, mState);
    }

    public void connect(final Looper looper, final Messenger messenger,
                        final Handler incomingMessageHandler) {
        assertEquals("AsyncChannel must be disconnected to connect",
                ChannelState.DISCONNECTED, mState);
        mChannel = new AsyncChannel();
        Handler rawMessageHandler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case AsyncChannel.CMD_CHANNEL_HALF_CONNECTED:
                        if (msg.arg1 == AsyncChannel.STATUS_SUCCESSFUL) {
                            Log.d(TAG, "Successfully half connected " + this);
                            mChannel.sendMessage(AsyncChannel.CMD_CHANNEL_FULL_CONNECTION);
                            mState = ChannelState.HALF_CONNECTED;
                        } else {
                            Log.d(TAG, "Failed to connect channel " + this);
                            mState = ChannelState.FAILURE;
                            mChannel = null;
                        }
                        break;
                    case AsyncChannel.CMD_CHANNEL_FULLY_CONNECTED:
                        mState = ChannelState.CONNECTED;
                        Log.d(TAG, "Channel fully connected" + this);
                        break;
                    case AsyncChannel.CMD_CHANNEL_DISCONNECTED:
                        mState = ChannelState.DISCONNECTED;
                        mChannel = null;
                        Log.d(TAG, "Channel disconnected" + this);
                        break;
                    default:
                        incomingMessageHandler.handleMessage(msg);
                        break;
                }
            }
        };
        mChannel.connect(null, rawMessageHandler, messenger);
    }

    public void disconnect() {
        assertEquals("AsyncChannel must be connected to disconnect",
                ChannelState.CONNECTED, mState);
        mChannel.sendMessage(AsyncChannel.CMD_CHANNEL_DISCONNECT);
        mState = ChannelState.DISCONNECTED;
        mChannel = null;
    }

    public void sendMessage(Message msg) {
        assertEquals("AsyncChannel must be connected to send messages",
                ChannelState.CONNECTED, mState);
        mChannel.sendMessage(msg);
    }
}

*/