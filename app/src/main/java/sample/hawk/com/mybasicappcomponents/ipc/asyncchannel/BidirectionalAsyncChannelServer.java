package sample.hawk.com.mybasicappcomponents.ipc.asyncchannel;

public class BidirectionalAsyncChannelServer {
    // AsyncChannel 不能在APP上使用, 因為他需要framework.jar
}
/*
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import com.android.internal.util.AsyncChannel;

import java.util.HashMap;
import java.util.Map;


// Provides an interface for the server side implementation of a bidirectional channel as described
// in {@link com.android.internal.util.AsyncChannel}.
public class BidirectionalAsyncChannelServer {

    private static final String TAG = "BidirectionalAsyncChannelServer";

    // Keeps track of incoming clients, which are identifiable by their messengers.
    private final Map<Messenger, AsyncChannel> mClients = new HashMap<>();

    private Messenger mMessenger;

    public BidirectionalAsyncChannelServer(final Context context, final Looper looper,
                                           final Handler messageHandler) {
        Handler handler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                AsyncChannel channel = mClients.get(msg.replyTo);
                switch (msg.what) {
                    case AsyncChannel.CMD_CHANNEL_FULL_CONNECTION:
                        if (channel != null) {
                            Log.d(TAG, "duplicate client connection: " + msg.sendingUid);
                            channel.replyToMessage(msg,
                                    AsyncChannel.CMD_CHANNEL_FULLY_CONNECTED,
                                    AsyncChannel.STATUS_FULL_CONNECTION_REFUSED_ALREADY_CONNECTED);
                        } else {
                            channel = new AsyncChannel();
                            mClients.put(msg.replyTo, channel);
                            channel.connected(context, this, msg.replyTo);
                            channel.replyToMessage(msg, AsyncChannel.CMD_CHANNEL_FULLY_CONNECTED,
                                    AsyncChannel.STATUS_SUCCESSFUL);
                        }
                        break;
                    case AsyncChannel.CMD_CHANNEL_DISCONNECT:
                        channel.disconnect();
                        break;

                    case AsyncChannel.CMD_CHANNEL_DISCONNECTED:
                        mClients.remove(msg.replyTo);
                        break;

                    default:
                        messageHandler.handleMessage(msg);
                        break;
                }
            }
        };
        mMessenger = new Messenger(handler);
    }

    public Messenger getMessenger() {
        return mMessenger;
    }

}
*/