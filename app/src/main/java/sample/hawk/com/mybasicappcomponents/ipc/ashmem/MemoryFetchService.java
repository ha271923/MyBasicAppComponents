package sample.hawk.com.mybasicappcomponents.ipc.ashmem;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
// import android.os.SharedMemory; // API_27+ support SharedMemory class，API 27之前需要通过reflect去拿到文件描述符。
import android.system.ErrnoException;
import android.util.Log;

import java.io.FileDescriptor;
import java.lang.reflect.Method;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;


public class MemoryFetchService extends Service {
    private static final String TAG = "MemoryFetchService";
    private static final String SHM_FILE_NAME = "test_memory";
    @Override
    public IBinder onBind(Intent intent) {
        return new MemoryFetchStub();
    }

    static class MemoryFetchStub extends IMemoryAidlInterface.Stub {
        @Override
        public ParcelFileDescriptor getParcelFileDescriptor() throws RemoteException {
            // if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {
            if (Build.VERSION.SDK_INT < 27) {
                MemoryFile memoryFile = null;
                try {
                    memoryFile = new MemoryFile(SHM_FILE_NAME, 1024);
                    byte[] data = new byte[]{1, 2, 3, 4, 5};
                    memoryFile.getOutputStream().write(data);
                    SMLog.d(TAG, "onServiceConnected: write +++ data=" + data);
                    Method method = MemoryFile.class.getDeclaredMethod("getFileDescriptor");
                    FileDescriptor des = (FileDescriptor) method.invoke(memoryFile);
                    return ParcelFileDescriptor.dup(des);
                } catch (Exception e) {
                    SMLog.d(TAG, "getParcelFileDescriptor: exception : " + e.toString());
                }
            }else { // TODO: API_27+
                /*
                try {
                    SharedMemory sharedMemory = SharedMemory.create(SHM_FILE_NAME, 1024);
                } catch (ErrnoException e) {
                    SMLog.d(TAG, "getParcelFileDescriptor: exception : " + e.toString());
                }
                */
            }

            return null;
        }
    }
}