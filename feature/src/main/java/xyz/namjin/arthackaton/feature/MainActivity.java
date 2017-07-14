package xyz.namjin.arthackaton.feature;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BluetoothAdapter.LeScanCallback {

    BluetoothAdapter bAdapter;
    BluetoothManager bManager;

    String text = "Recorded \n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bManager = (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        if(bManager==null)
            Toast.makeText(this,"Can't Searching Bluetooth BLE",Toast.LENGTH_SHORT).show();
        bAdapter = bManager.getAdapter();
        if(bAdapter==null)
            Toast.makeText(this,"Can't Searchin Bluetooth BLE 2 ", Toast.LENGTH_SHORT).show();
        bAdapter.startLeScan(this);

    }
    public void scanDataUpdate(View v)
    {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        Beacon beacon = Beacon.fromScanData(scanRecord,rssi);
            Log.d("디버깅",beacon.getProximityUuid());
            text+=beacon.getProximityUuid()+" rssi: "+rssi+"\n";

    }
}
