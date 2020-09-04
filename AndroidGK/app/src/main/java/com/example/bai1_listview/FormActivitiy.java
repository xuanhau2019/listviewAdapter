package com.example.bai1_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivitiy extends AppCompatActivity {
    private EditText edt_name,edt_email,edt_sdt;
    private Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_activitiy);
        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_email);
        edt_sdt=findViewById(R.id.edt_sdt);
        btn_save=findViewById(R.id.btn_save);

        final Intent intent= getIntent();
        if(!intent.hasExtra("edit")){
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contact contact= new Contact(edt_name.getText().toString(),edt_email.getText().toString(),edt_sdt.getText().toString());
                    Intent intent1 = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contact",contact);
                    intent1.putExtra("add",bundle);
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            });

        }else {
            final Bundle bundle = intent.getBundleExtra("edit");
            final Contact contact = (Contact) bundle.getSerializable("contact");
            edt_name.setText(contact.getName());
            edt_email.setText(contact.getEmail());
            edt_sdt.setText(contact.getSdt());
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contact.setName(edt_name.getText().toString());
                    contact.setEmail(edt_email.getText().toString());
                    contact.setSdt(edt_sdt.getText().toString());
                    Intent intent1 = new Intent();
                    intent1.putExtra("edit", bundle);
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            });
        }
    }
}
