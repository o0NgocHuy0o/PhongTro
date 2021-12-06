package nnh19607021.tdtu.phongtro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView recyclerView;
    class_PhongTro pt;
    ArrayList<class_PhongTro> listPhong;
    PhongTroAdapter phongTroAdapter;
    ArrayList<String> mKeys = new ArrayList<>();
    CardView layout_item;


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
        layout_item = findViewById(R.id.layout_item);
    }
    public void getData(){
        listPhong = new ArrayList<>();
        _myRef = mDatabase.getReference("PhongTro");
        _myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                class_PhongTro pt = snapshot.getValue(class_PhongTro.class);
                if (pt != null) {
                    listPhong.add(pt);
                    String key = snapshot.getKey();
                    mKeys.add(key);
                    phongTroAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                class_PhongTro pt = snapshot.getValue(class_PhongTro.class);
                if (pt == null || listPhong == null || listPhong.isEmpty())
                    return;
//                for(int i=0;i<lstPerson.size(); i++){
//                    if(p.getId()==lstPerson.get(i).getId())
//                        lstPerson.set(i,p);
//                }

                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                listPhong.set(index, pt);
                phongTroAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                class_PhongTro p = snapshot.getValue(class_PhongTro.class);
                if (p == null || listPhong == null || listPhong.isEmpty())
                    return;
                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                if (index != -1) {
                    listPhong.remove(index);
                    mKeys.remove(index);
                }
                phongTroAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.master_bottom_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                Intent i = new Intent(this, DetailActivity.class);
                startActivity(i);
            case R.id.navigationHome:
                Toast.makeText(this, "Nút Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationMenu:
                Toast.makeText(this, "nút Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationRegister:
                Intent id = new Intent(this,RegisterActivity.class);
                startActivity(id);
        }
        return super.onOptionsItemSelected(item);
    }

//    public void setData(class_PhongTro pt){
//        if (pt == null){
//            pt = new class_PhongTro();
//        }
//        class_NguoiTro nt = new class_NguoiTro();
//        nt.setId("5");
//        nt.setHoTen("null");
//        nt.setGioiTinh(false);
//        nt.setNgaySinh(null);
//        nt.setSDT(null);
//
//        pt.setSoPhong("Phòng 5");
//        pt.setPhongTrong(true);
//        pt.setGiaTro(1500000);
//        pt.setNguoitro(nt);
//
//        _myRef = mDatabase.getReference("PhongTro");
//
//        String id = _myRef.push().getKey();
//        pt.setId(id);
//        _myRef.child(id).setValue(pt);
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (phongTroAdapter != null)
            phongTroAdapter.release();
    }
}