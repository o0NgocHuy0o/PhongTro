package nnh19607021.tdtu.phongtro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    RadioButton rbmale, rbFemale;
    EditText etName, etPhone,etTien, etPTSo;
    DatePicker etNgaySinh;
    Button btnUp;
    RadioGroup rdGroup;
    Calendar myCalendar = Calendar.getInstance();

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView recyclerView;
    class_PhongTro pt;
    PhongTroAdapter phongTroAdapter;
    BottomNavigationView bottomNavigationView;
    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        getData();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.menu_bottom_save) {
                    setData(pt);
                    finish();
                }
                else if(i == R.id.menu_bottom_close){
                    finish();
                }
                return false;
            }
        });

    }



    void init(){
        rbmale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        etName = findViewById(R.id.etName);
        etNgaySinh = findViewById(R.id.etNgaySinh);
        etPhone = findViewById(R.id.etPhone);
        etTien = findViewById(R.id.etTien);
        etPTSo = findViewById(R.id.etPTSo);
        recyclerView = findViewById(R.id.recyclerView);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }
    public void getData(){
        Bundle bundle = getIntent().getExtras();
        if(bundle==null)
            return;
        flag = true;
        pt = (class_PhongTro) bundle.get("object_phongtro");
        //Toast.makeText(this, "Click" + p, Toast.LENGTH_LONG).show();
        etName.setText(pt.getNguoitro().getHoTen());
        //etNgaySinh.setMaxDate(Date.valueOf(pt.getNguoitro().getNgaySinh()));
        etPhone.setText(pt.getNguoitro().getSDT());
        if(pt.getNguoitro().isGioiTinh() == true)
            rbmale.setChecked(true);
        else
            rbFemale.setChecked(true);

        etTien.setText(String.valueOf(pt.getGiaTro()));
    }

    public void setData(class_PhongTro pt){
//        Toast.makeText( DetailActivity.this, "update thành công", Toast.LENGTH_SHORT).show();

        if(pt == null){
            pt = new class_PhongTro();
        }
        class_NguoiTro nt = new class_NguoiTro();
        nt.setHoTen(etName.getText().toString());
        nt.setSDT(etPhone.getText().toString());
        nt.setNgaySinh(Date.valueOf(etNgaySinh.toString()));
        if(rbFemale.isChecked()){
            nt.setGioiTinh(false);
        }
        else{
            nt.setGioiTinh(true);
        }
        pt.setNguoitro(nt);
        pt.setSoPhong("Phòng " + etPTSo.getText().toString());
        pt.setGiaTro(Integer.valueOf(etTien.getText().toString()));

        _myRef = mDatabase.getReference("PhongTro");

        if(flag == false){
            String id =_myRef.push().getKey();
            pt.setId(id);
            nt.setId(id);
            _myRef.child(id).setValue(pt);
        }
        else {
            _myRef.child(pt.getId()).setValue(pt);
            Toast.makeText( DetailActivity.this, "Update success!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

}