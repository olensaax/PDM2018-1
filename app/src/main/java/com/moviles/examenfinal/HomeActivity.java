package com.moviles.examenfinal;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moviles.examenfinal.Model.Items;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements FormFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener{


    private FormFragment formulario;
    private ListFragment lista;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mName;
    private FirebaseUser user;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mName = (TextView) findViewById(R.id.name_field);
        logout = (Button) findViewById(R.id.logout_btn);

        formulario = new FormFragment();
        lista = new ListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,lista).commit();
        mAuth = FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mAuth.signOut();
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        user = mAuth.getCurrentUser();
        if (user != null){
            String name = user.getDisplayName();
            mName.setText("Welcome " + name);
        }

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
