package com.example.passwordsaver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.ViewHolder> {

    ArrayList<PasswordData> contacts;


    Context c ;




    public PasswordAdapter(Context c, ArrayList<PasswordData> list) {
        contacts = list;
        this.c = c ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.single_password_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvURL.setText(contacts.get(position).getURL());
        holder.tvUsername.setText(contacts.get(position).getUsername());

        holder.EditLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,Editpassword.class);
                Bundle b = new Bundle();
                b.putInt("id0",position);
                b.putInt("id",contacts.get(position).getId());
                b.putString("id1",contacts.get(position).getOfUser());
                b.putString("id2",contacts.get(position).getURL());
                b.putString("id3",contacts.get(position).getUsername());
                b.putString("id4",contacts.get(position).getPassword());
                i.putExtras(b);
                c.startActivity(i);
                Activity activity = (Activity) c;
//                activity.finish();


            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(holder.itemView.getContext());
                alertDialog.setMessage("Do you really want to delete?");
                alertDialog.setTitle("Delete Notification");

                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper database = new DatabaseHelper(c);

                        database.deletePassword(contacts.get(holder.getAdapterPosition()).getId(),contacts.get(holder.getAdapterPosition()).getOfUser(),contacts.get(holder.getAdapterPosition()).getURL(),contacts.get(holder.getAdapterPosition()).getUsername(),contacts.get(holder.getAdapterPosition()).getPassword());
                        database.close();
//                        MainActivity3.contacts

                        MainActivity3.deletedpassword.add(MainActivity3.contacts.get(holder.getAdapterPosition()));
                        MainActivity3.contacts.remove(holder.getAdapterPosition());
                        ListPasswords.adapter.notifyDataSetChanged();
//                        ListDeletedPasswords.adapter.notifyDataSetChanged();
                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();


                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvURL , tvUsername;
        LinearLayout EditLayout ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvURL = itemView.findViewById(R.id.tvURL);
            tvUsername = itemView.findViewById(R.id.tvusername);
            EditLayout = itemView.findViewById(R.id.EditLayout);
        }
    }
}