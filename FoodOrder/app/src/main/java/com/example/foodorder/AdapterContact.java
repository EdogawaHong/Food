package com.example.foodorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AdapterContact extends RecyclerView.Adapter<AdapterContact.Viewhoder> {

    List<Contact> contacts;
    IonclickContact ionclickContact;

    public AdapterContact(List<Contact> contacts) {
        this.contacts=contacts;
    }

    public void setIonclickContact(IonclickContact ionclickContact) {
        this.ionclickContact = ionclickContact;
    }

    @NonNull
    @Override
    public AdapterContact.Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item,parent,false);
        Viewhoder vh=new Viewhoder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final Contact contact=contacts.get(position);

        holder.tvFood.setText(contact.getFood());
        holder.tvFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionclickContact.onClickFood(contact.getFood());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvFood;
        public Viewhoder(@NonNull View itemView) {

            super(itemView);
            tvFood=itemView.findViewById(R.id.tvFood);
        }
    }
}
