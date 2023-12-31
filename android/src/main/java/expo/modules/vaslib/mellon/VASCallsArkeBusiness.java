package expo.modules.vaslib.mellon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.arke.vas.IVASInterface;
import com.arke.vas.IVASListener;
import com.arke.vas.data.VASPayload;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VASCallsArkeBusiness {
    private static String TAG = "VASCallsArkeBusiness";

    // The Java class generated by the AIDL file
    // 由AIDL文件生成的Java类
    private static IVASInterface vasInterface = null;
    private Context context;
    private ArrayList<VASResponseListener> listeners = new ArrayList<>();

    // Server connection status, false for unconnected, true for connection
    // 服务端连接状况，false为未连接，true为连接中
    private boolean boundStatus = false;

    public VASCallsArkeBusiness(Context context) {
        this.context = context;
    }



    public ServiceConnection getServiceConnection() {
        return serviceConnection;
    }
    private String sendData;
    public void setSendData(String sendData)
    {
        this.sendData = sendData;
    }

    private String responseData;
    public String getResponseData()
    {
        return this.responseData;

    }


    public void addListener(VASResponseListener listener)
    {
        listeners.add(listener);
    }

    public void fireResponseReceived()
    {
        for(VASResponseListener listener:listeners)
        {
            listener.responseReceived();
        }
    }
    /**
     * Do transaction
     * 做交易
     *
     * @param interfaceId
     */
    public void doTransaction(final String interfaceId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "[" + interfaceId + "]");
                    bindServiceUntilConnect();

                    if ("SALE".equals(interfaceId)) {
                        vasInterface.sale(new VASPayload(sendData), listener);
                    } else if ("VOID".equals(interfaceId)) {
                        vasInterface.voided(new VASPayload(sendData), listener);
                    }  else if ("PRINT_LAST".equals(interfaceId)) {
                        vasInterface.printLast(listener);
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Obtain transaction records based on value-added service order number
     * <p>
     * 根据增值服务流水获取交易记录
     *
     * @param orderNumber
     */
    public void getTransactionRecord(final String orderNumber) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "getTransactionRecord");

                    bindServiceUntilConnect();

                    vasInterface.orderNumberQuery(new VASPayload("{\"orderNumber\":\"" +
                            (orderNumber == null ? "" : orderNumber) + "\"}"), listener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Connect to the server
     * <p>
     * 与服务端建立连接
     */
    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.arke.vas.service");
        intent.setPackage("com.arkeJCC");
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    /**
     * Connect the service until the connection is successful
     * <p>
     * 连接服务，直到连接成功
     */
    private void bindServiceUntilConnect() {
        try {
            Log.i(TAG, "unbound service. Binding service");
            bindService();

            while (vasInterface == null) {
                Thread.sleep(300);
            }
            Log.i(TAG, "server connected successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Disconnect
     * <p>
     * 断开连接
     *
     * @param responseData
     */
    private void disconnect(String... responseData) {
        Log.i(TAG, "disconnect()");
        if (responseData != null && responseData.length > 0) {
            Log.i(TAG, "onComplete,transaction end, responseData:" + responseData[0]);
            this.responseData = responseData[0];
            JSONObject json= null;  //your response
            try {
                json = new JSONObject(this.responseData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result="";
            try {
                result = json.getString("responseCode");    //result is key for which you need to retrieve data
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(result!=null&& result.length()>0 && !result.equals("1"))
            {
                final String finalResult = result;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(finalResult.equals("2"))
                        {
                            Toast.makeText(context,"No transaction found",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Transaction not completed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        }

        context.unbindService(serviceConnection);
        vasInterface = null;
        boundStatus = false;
        fireResponseReceived();

    }



    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "服务连接成功 onServiceConnected");
            vasInterface = IVASInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            vasInterface = null;
        }
    };

    private IVASListener listener = new IVASListener.Stub() {
        @Override
        public void onStart() throws RemoteException {
            Log.i(TAG, "onStart,begin transaction");
        }

        @Override
        public void onNext(VASPayload vasPayload) throws RemoteException {
            Log.i(TAG, " onNext,Transaction process information：" + vasPayload.toString());
        }


        @Override
        public void onComplete(VASPayload responseData) throws RemoteException {
            Log.i(TAG, " onComplete,Information returned at the end of the transaction：" + responseData);
            disconnect(responseData == null ? "" : responseData.getBody());


        }
    };
}
