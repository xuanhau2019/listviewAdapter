package com.example.bai1_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private ArrayList<Contact> contacts;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> contacts) {
        super(context, resource, contacts);
        this.context = context;
        this.resource = resource;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHordel viewHordel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview,parent,false);
            viewHordel=new ViewHordel();
            viewHordel.tv_name = convertView.findViewById(R.id.tv_name);
            viewHordel.tv_email = convertView.findViewById(R.id.tv_email);
            viewHordel.tv_sdt = convertView.findViewById(R.id.tv_sdt);
            convertView.setTag(viewHordel);
        }else{
            viewHordel= (ViewHordel) convertView.getTag();
        }
        Contact contact=contacts.get(position);
        viewHordel.tv_name.setText(contact.getName());
        viewHordel.tv_email.setText(contact.getEmail());
        viewHordel.tv_sdt.setText(contact.getSdt());
        return convertView;
    }

    public class ViewHordel{
         TextView tv_name,tv_email,tv_sdt;
    }
}
