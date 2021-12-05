package nnh19607021.tdtu.phongtro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView recyclerView;
    ArrayList<class_PhongTro> listPhong;
    PhongTroAdapter phongTroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        phongTroAdapter = new PhongTroAdapter(listPhong,this);
        recyclerView.setAdapter(phongTroAdapter);

    }

    void init(){
        recyclerView = findViewById(R.id.recyclerView);
    }
    public void getData(){
        listPhong = new ArrayList<>();
        _myRef = mDatabase.getReference("PhongTro");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (phongTroAdapter != null)
            phongTroAdapter.release();
    }
}