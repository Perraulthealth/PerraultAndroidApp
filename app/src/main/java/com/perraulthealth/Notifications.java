package com.perraulthealth;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.RemoteMessage;

import static android.R.attr.id;
import static android.R.attr.key;
import static android.R.attr.value;
import static com.perraulthealth.R.id.textViewTitle;

public class Notifications extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseRecyclerAdapter<Notification, NotificationHolder> mAdapter;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    DatabaseReference mRef = mRootRef.child("Consumer").child(uid).child("notifications");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewNotifications);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }


    @Override
    protected void onStart() {
        super.onStart();


        mAdapter = new FirebaseRecyclerAdapter<Notification, NotificationHolder>(Notification.class, R.layout.notification, NotificationHolder.class, mRef) {
            @Override
            public void populateViewHolder(NotificationHolder notificationViewHolder, Notification model, int position) {
                notificationViewHolder.setNTitle(model.getNTitle());
                notificationViewHolder.setNBody(model.getNBody());
            }
        };
        mRecyclerView.setAdapter(mAdapter);

    }


    public static class NotificationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View mView;

        private SparseBooleanArray selectedItems = new SparseBooleanArray();

        public NotificationHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mView = itemView;
        }

        public void setNTitle(String NTitle) {
            TextView field = (TextView) mView.findViewById(R.id.textViewTitle);
            field.setText(NTitle);
        }

        public void setNBody(String NBody) {
            TextView field = (TextView) mView.findViewById(R.id.textViewBody);
            field.setText(NBody);
        }

        @Override
        public void onClick(View view) {
            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                view.setSelected(false);
            } else {
                selectedItems.put(getAdapterPosition(), true);
                view.setSelected(true);
            }

        }
    }


        public static class Notification {
            private String NBody;
            private String NTitle;


            public Notification() {
            }

            public Notification(String NBody, String NTitle) {
                this.NTitle = NTitle;
                this.NBody = NBody;

            }

            public String getNTitle() {
                return NTitle;
            }


            public String getNBody() {
                return NBody;
            }

        }


        @Override
        protected void onDestroy() {
            super.onDestroy();
            mAdapter.cleanup();
        }

    }









