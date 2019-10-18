package com.example.medpal;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MyPrescriptionsRecyclerViewAdapter extends RecyclerView.Adapter<MyPrescriptionsRecyclerViewAdapter.MyPrescriptionsViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> myPrescriptions = new ArrayList<>();
    private Context mContext;

    public MyPrescriptionsRecyclerViewAdapter(ArrayList<String> prescriptions, Context context) {
        myPrescriptions = prescriptions;
        mContext = context;
    }

    @NonNull
    @Override
    public MyPrescriptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_prescriptions_list_item, parent, false);
        MyPrescriptionsViewHolder holder = new MyPrescriptionsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPrescriptionsViewHolder holder, final int position) {
        holder.btnMyPrescriptionItem.setText(myPrescriptions.get(position));
    }

    @Override
    public int getItemCount() {
        return myPrescriptions.size();
    }

    public class MyPrescriptionsViewHolder extends RecyclerView.ViewHolder{

        Button btnMyPrescriptionItem;
        ConstraintLayout parentLayout;

        public MyPrescriptionsViewHolder(View itemView){
            super(itemView);

            mContext = itemView.getContext();
            btnMyPrescriptionItem = itemView.findViewById(R.id.btnMyPrescriptionItem);
            parentLayout = itemView.findViewById(R.id.myPrescriptionsItemParentLayout);

            btnMyPrescriptionItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToRegisterActivity = new Intent(mContext, MedicineDetails.class);
                    mContext.startActivity(goToRegisterActivity);
                }
            });
        }

    }
}
