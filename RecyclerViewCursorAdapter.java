package com.example.a2019rmcbride.collegeapphelper;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a2019rmcbride.collegeapphelper.databinding.CollegeRecyclerBinding;

import java.util.List;

public class RecyclerViewCursorAdapter extends RecyclerView.Adapter<RecyclerViewCursorAdapter.ViewHolder> {

    Context mContext;
    Cursor mCursor;
    ItemClickListener mClickListener;
    int pos;


    public RecyclerViewCursorAdapter(Context context, Cursor cursor) {

        mContext = context;
        mCursor = cursor;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CollegeRecyclerBinding itemBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        public void bindCursor(Cursor cursor) {
            itemBinding.nameLabel.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(Database.College.COLUMN_NAME)
            ));
            itemBinding.deadlineLabel.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(Database.College.COLUMN_DEADLINE)
            ));
            itemBinding.majorLabel.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(Database.College.COLUMN_MAJOR)
            ));
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            pos = getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        //Log.d("OOOOOOOOOOOOOOOOOOOOOOO",  "" + mCursor.getCount());
        return mCursor.getCount();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.bindCursor(mCursor);  //said myCursor in tutorial so I changed to mCursor? assume typo?
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.college_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    int getPos(){
        return pos;
    }



    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}