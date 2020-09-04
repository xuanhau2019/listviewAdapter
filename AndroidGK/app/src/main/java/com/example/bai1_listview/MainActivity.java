package com.example.bai1_listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton img_btn_add;
    private ListView lv_contact;
    private ArrayList<Contact>contacts;
    private CustomAdapter adapter;
    private static final int ADD=999;
    private static final int EDIT = 888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_contact =findViewById(R.id.lv_contact);
        img_btn_add = findViewById(R.id.img_btn_add);

        contacts = new ArrayList<>();
        contacts.add(new Contact("Hậu", "duykin1235@gmail.com", "0974979691"));
        contacts.add(new Contact("Hoàng", "duykin1235@gmail.com", "0974979691"));
        contacts.add(new Contact("Giang", "duykin1235@gmail.com", "0974979691"));
        contacts.add(new Contact("Huy", "duykin1235@gmail.com", "0974979691"));
        contacts.add(new Contact("Minh", "duykin1235@gmail.com", "0974979691"));

        adapter= new CustomAdapter(MainActivity.this,R.layout.custom_listview,contacts);
        lv_contact.setAdapter(adapter);

        //1.thêm
        img_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FormActivitiy.class);
                startActivityForResult(intent,ADD);
            }
        });
        //3.chi tiết
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contacts.get(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", contact);
                intent.putExtra("detail", bundle);
                startActivity(intent);
            }
        });
        //4.đk menu
        registerForContextMenu(lv_contact);
    }
    //2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD){
            if(resultCode==RESULT_OK){
                Bundle bundle = data.getBundleExtra("add");
                Contact contact = (Contact) bundle.getSerializable("contact");
                contacts.add(contact);
                adapter.notifyDataSetChanged();
            }
        }
        //8.xử lý kq trả về
        else if (requestCode == EDIT) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("edit");
                int pos = bundle.getInt("pos");
                Contact contact = (Contact) bundle.getSerializable("contact");
                Contact needEdit = contacts.get(pos);
                needEdit.setName(contact.getName());
                needEdit.setEmail(contact.getEmail());
                needEdit.setSdt(contact.getSdt());
                adapter.notifyDataSetChanged();
            }
        }
    }
//5dè menu ra edit,delete
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lv_contact) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.custom_menu,menu);
        }
    }
//6 xử lý menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            //ez
            case R.id.add:
                Intent intent = new Intent(MainActivity.this,FormActivitiy.class);
                startActivityForResult(intent,ADD);

                break;
                //7 xử lý edit
            case R.id.edit:
                int pos = menuInfo.position;
                Contact contact = contacts.get(pos);
                Intent intent1 = new Intent(MainActivity.this, FormActivitiy.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", contact);
                bundle.putInt("pos",pos);
                intent1.putExtra("edit", bundle);
                startActivityForResult(intent1,EDIT);
                break;
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete?");
                builder.setMessage("Do you want to delete this contact?");
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contacts.remove(menuInfo.position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
