package xyz.namjin.arthon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xyz.namjin.arthon.R
public class MainActivity extends AppCompatActivity implements BluetoothAdapter.LeScanCallback {

    BluetoothManager btManager;
    BluetoothAdapter btAdapter;
    Button btn1;
    TextView textView;
    boolean flag=false;
    public String mText="";
    MainActivity myActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivity = this;
        btManager = (BluetoothManager)getApplicationContext().getSystemService(BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();
        btAdapter.startLeScan(this);

    }
    public void onStart(){
        super.onStart();
        textView = findViewById(R.id.textView);
        btn1 = findViewById(R.id.btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag)
                {
                    textView.setText(mText);
                    btAdapter.startLeScan(myActivity);
                    flag=false;
                }else{
                    flag = true;
                    btAdapter.stopLeScan(myActivity);
                }
            }
        });
    }
    @Override
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {

        Beacon beacon = Beacon.fromScanData(bytes,i);
        if(beacon.getBeaconType()==0)
        {
            Log.d("Debuging",beacon.getProximityUuid());
            mText+=beacon.getProximityUuid()+"\n";
        }

    }
}
