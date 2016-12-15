package com.perraulthealth;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.RemoteMessage;
import com.perraulthealth.Notifications;
import com.perraulthealth.R;

import static android.R.id.message;
import static java.lang.String.valueOf;

/**
 * Created by D on 11/18/2016.
 */



public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {



     public static int not_id;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){



        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        DatabaseReference notRef = mRootRef.child("Notification number");
       ValueEventListener listener=notRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                not_id=dataSnapshot.getValue(int.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });notRef.removeEventListener(listener);



        Object objb = remoteMessage.getData().get("body");
        String body = valueOf(objb.toString());

        Object objt = remoteMessage.getData().get("title");
        String title = valueOf(objt.toString());



        mRootRef.child("Consumer").child(uid).child("notifications").child("N"+not_id).child("NTitle").setValue(title);

        mRootRef.child("Consumer").child(uid).child("notifications").child("N"+not_id).child("NBody").setValue(body);




        Intent i = new Intent(this, Notifications.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentText(body)
                .setSmallIcon(R.drawable.brandlogo)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());


       not_id=not_id+1;



    }


}
