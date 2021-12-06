package nnh19607021.tdtu.phongtro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    RadioButton rbmale, rbFemale;
    EditText etName, etNgaySinh, etPhone,etTien;
    Button btnUp;
    RadioGroup rdGroup;
    Calendar myCalendar = Calendar.getInstance();

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView recyclerView;
    class_PhongTro pt;
    PhongTroAdapter phongTroAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        getData();

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( DetailActivity.this, "đây là nút update", Toast.LENGTH_SHORT).show();
            }
        });
    }



    void init(){
        rbmale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        etName = findViewById(R.id.etName);
        etNgaySinh = findViewById(R.id.etNgaySinh);
        etPhone = findViewById(R.id.etPhone);
        btnUp = findViewById(R.id.btnUP);
        etTien = findViewById(R.id.etTien);
        recyclerView = findViewById(R.id.recyclerView);
    }
    public void getData(){
        Bundle bundle = getIntent().getExtras();
        if(bundle==null)
            return;
        pt = (class_PhongTro) bundle.get("object_phongtro");
        //Toast.makeText(this, "Click" + p, Toast.LENGTH_LONG).show();
        etName.setText(pt.getNguoitro().getHoTen());
        etNgaySinh.setText(String.valueOf(pt.getNguoitro().getNgaySinh()));
        etPhone.setText(pt.getNguoitro().getSDT());
        if(pt.getNguoitro().isGioiTinh() == true)
            rbmale.setChecked(true);
        else
            rbFemale.setChecked(true);

        etTien.setText(String.valueOf(pt.getGiaTro()));
    }

}