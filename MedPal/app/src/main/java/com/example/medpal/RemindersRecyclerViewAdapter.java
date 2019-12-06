package com.example.medpal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RemindersRecyclerViewAdapter extends RecyclerView.Adapter<RemindersRecyclerViewAdapter.RemindersViewHolder>{
    MedPalDatabase db;
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> reminders = new ArrayList<>();
    private Context mContext;

    public RemindersRecyclerViewAdapter(ArrayList<String> remindersList, Context context) {
        reminders = remindersList;
        mContext = context;
    }

    @NonNull
    @Override
    public RemindersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_list_item, parent, false);
        RemindersViewHolder holder = new RemindersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemindersViewHolder holder, final int position) {
        holder.btnReminderItem.setText(reminders.get(position));
        holder.reminderId = position;
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public class RemindersViewHolder extends RecyclerView.ViewHolder{

        Integer reminderId;
        Button btnReminderItem;
        ConstraintLayout parentLayout;

        public RemindersViewHolder(View itemView){
            super(itemView);

            mContext = itemView.getContext();
            btnReminderItem = itemView.findViewById(R.id.btnReminderItem);
            parentLayout = itemView.findViewById(R.id.remindersItemParentLayout);

            btnReminderItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onReminderPressed(reminderId);
                    /*Intent goToRegisterActivity = new Intent(mContext, MedicineDetails.class);
                    mContext.startActivity(goToRegisterActivity);*/
                }
            });
        }

    }

    /**
     * Method to ask confirmation from user if they want to delete the selected reminder
     * Taken from https://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity/2258147
     */
    public void onReminderPressed(final Integer reminderId) {
        new AlertDialog.Builder(mContext)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Remove Reminder")
                .setMessage("Are you sure you want to remove this reminder? You will not be notified to take your medication anymore.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reminders.remove(reminderId);
                        Log.d("onReminderPressed", "Reminder Deleted");
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
