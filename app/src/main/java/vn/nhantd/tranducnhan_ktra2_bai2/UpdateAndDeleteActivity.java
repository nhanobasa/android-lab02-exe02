package vn.nhantd.tranducnhan_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.nhantd.tranducnhan_ktra2_bai2.model.QuyenGop;

/**
 * Created by Tran Duc Nhan on 2021-05-14
 */
public class UpdateAndDeleteActivity extends AppCompatActivity {

    private Button btBack, btDelete, btUpdate;
    private EditText etID, etName, etDate, etMoney;
    Spinner spCity;
    private SQLiteObjectOpenHelper db;
    private QuyenGop quyenGop;
    private List<QuyenGop> quyenGopList;

    String[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        db = new SQLiteObjectOpenHelper(this);
        quyenGopList = new ArrayList<>();

        Intent intent = getIntent();
        quyenGop = (QuyenGop) intent.getSerializableExtra("quyengop");
        System.out.println(quyenGop);

        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
        btBack = findViewById(R.id.btBack);

        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etMoney = findViewById(R.id.etMoney);

        spCity = findViewById(R.id.spCity);
        List<String> cityList = new ArrayList<>();
        cityList.add("Hanoi");
        cityList.add("DaNang");
        cityList.add("HCM");
        ArrayAdapter arrayAdapter = new ArrayAdapter(UpdateAndDeleteActivity.this, android.R.layout.simple_spinner_item, cityList);
        spCity.setAdapter(arrayAdapter);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCity.setSelection(cityList.indexOf(quyenGop.getCity()));

        Calendar calendar = Calendar.getInstance();
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int m = calendar.get(Calendar.MONTH);
        int y = calendar.get(Calendar.YEAR);
        etDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateAndDeleteActivity.this, (view, year, month, dayOfMonth) -> {
                    etDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }, y, m, d);

                datePickerDialog.show();
            }
        });
        etDate.setOnClickListener(v -> {

            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateAndDeleteActivity.this, (view, year, month, dayOfMonth) -> {
                etDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }, y, m, d);

            datePickerDialog.show();
        });


        etID.setText(String.valueOf(quyenGop.getId()));
        etName.setText(quyenGop.getName());
        etDate.setText(quyenGop.getDate());
        etMoney.setText(quyenGop.getMoney().toString());

        // Update
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = quyenGop.getId();
                String name = etName.getText().toString();
                String city = spCity.getSelectedItem().toString();
                String date = etDate.getText().toString();
                Double money = Double.parseDouble(etMoney.getText().toString());
                QuyenGop quyenGop = new QuyenGop(id, name, city, date, money);
                int changedRows = db.update(quyenGop);
                if (changedRows > 0) {
                    Toast.makeText(UpdateAndDeleteActivity.this, "Thanh cong", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateAndDeleteActivity.this, "That bai", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Delete
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = quyenGop.getId();
                int deletedRows = db.deleteQuyenGop(id);
                if (deletedRows > 0) {
                    Toast.makeText(UpdateAndDeleteActivity.this, "Thanh cong", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateAndDeleteActivity.this, "That bai", Toast.LENGTH_LONG).show();
                }
//
            }
        });
        // BACK
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateAndDeleteActivity.this, MainActivity.class));
            }
        });
    }
}