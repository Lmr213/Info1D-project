package com.sailpass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sailpass.myapplication.Util.ToastUtil;
import com.sailpass.myapplication.bean.Ticket;

import java.util.List;

public class OpDbActivity extends AppCompatActivity {

    private MySQLiteOpenHelper mMySQLiteOpenHelper;
    private EditText etDes;
    private EditText etDet;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_db);


        etDes = findViewById(R.id.et_Des);
        etDet = findViewById(R.id.et_Det);
        tvResult = findViewById(R.id.tv_result);

        mMySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    }

    public void insert(View view) {
        mMySQLiteOpenHelper.insertData();

        ToastUtil.toastLong(this, "insert successfully!");
    }

    public void delete(View view) {
        String dest = etDet.getText().toString().trim();
        if (TextUtils.isEmpty(dest)) {
            mMySQLiteOpenHelper.deleteAllData();
        }
        mMySQLiteOpenHelper.deleteByDes(dest);

        // toast
        ToastUtil.toastLong(this, "delete successfully!");

    }

    public void query(View view) {
        String des = etDes.getText().toString().trim();
        if (TextUtils.isEmpty(des)) {
            // query all data
            List<Ticket> tickets = mMySQLiteOpenHelper.queryFromDbByInfo();
            showData(tickets);
            return;
        }

        // base on the destination to query
        List<Ticket> tickets = mMySQLiteOpenHelper.queryFromDbByDes(des);
        showData(tickets);
    }


    // display the information to user
    private void showData(List<Ticket> tickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ticket tkt : tickets) {
            stringBuilder.append("Destination：");
            stringBuilder.append(tkt.getDestination());
            stringBuilder.append("，DepartureDate：");
            stringBuilder.append(tkt.getDepartureDate());
            stringBuilder.append(", ArrivalDate：");
            stringBuilder.append(tkt.getArrivalDate());
            stringBuilder.append(", AdultPrice：");
            stringBuilder.append(tkt.getAdultPrice());
            stringBuilder.append(", ChildPrice：");
            stringBuilder.append(tkt.getChildPrice() + "\n");
        }

        tvResult.setText(stringBuilder.toString());
        tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}