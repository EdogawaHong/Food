package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class activity1 extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView tvUsername, tvPrice;
    RecyclerView recyclerView;
    Contact contact1, contact2, contact3, contact4, contact5, contact6;
    List<Contact> contacts;
    Button btnOrder;
    BottomNavigationView navigationView;
    int d = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        Intent intent = getIntent();
        String ten = intent.getStringExtra("Name");
        tvUsername = findViewById(R.id.tvUsername);
        tvPrice = findViewById(R.id.tvPrice);

        tvUsername.setText(ten);

        recyclerView = findViewById(R.id.rvFood);

        contacts = new ArrayList<>();

        contact1 = new Contact("Pizza Panda");
        contact2 = new Contact("KFC Super");
        contact3 = new Contact("Bread Eggs");
        contact4 = new Contact("Coca Cola");
        contact5 = new Contact("Chicken super");
        contact6 = new Contact("Cup Cake");

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        contacts.add(contact6);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 1, RecyclerView.VERTICAL, false);

        AdapterContact adapterContact = new AdapterContact(contacts);
        recyclerView.setAdapter(adapterContact);
        recyclerView.setLayoutManager(layoutManager);

        adapterContact.setIonclickContact(new IonclickContact() {
            @Override
            public void onClickFood(String food) {
                Toast.makeText(getBaseContext(), food, Toast.LENGTH_SHORT).show();
                d++;
                tvPrice.setText(d * 10 + "$");
            }
        });

        btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Thank you! Your order is sent to restaurant!", Toast.LENGTH_SHORT).show();
            }
        });

        navigationView=findViewById(R.id.nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    getFragment(view_cart.newInstance());
                return false;
            }
        });
    }
    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }
}
