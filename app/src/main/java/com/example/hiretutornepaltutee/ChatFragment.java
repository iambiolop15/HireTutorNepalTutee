package com.example.hiretutornepaltutee;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatFragment extends Fragment {
    private EditText edtMessage;
    private ImageView sendBtn;
    private RecyclerView recyclerView;
    private TextView userFullname;
    private String userfullname, receiverId, message;
    private FirebaseAuth mAuth;
    private CollectionReference collectionReferenceforChat;
    private ChatAdapter chatAdapter;
    private List<ChatDto> chatDtoList = new ArrayList<>();
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, null);
        userfullname = "Admin";
        receiverId = "o5CAmYp27RS32OrbLpvZKxA9WZW2";
        edtMessage =view.findViewById(R.id.message);
        sendBtn = view.findViewById(R.id.send_btn);
        recyclerView = view.findViewById(R.id.recyclerview);
        mAuth = FirebaseAuth.getInstance();
        collectionReferenceforChat = FirebaseFirestore.getInstance().collection("chatRoom");
        chatAdapter = new ChatAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatAdapter);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = edtMessage.getText().toString().trim();
                if (!TextUtils.isEmpty(message)) {
                    saveMessageInFirebase(message, receiverId);
                } else {
                    edtMessage.setError("Message cant be null");
                    edtMessage.requestFocus();
                }

            }
        });
        chatAdapter.addChatList(chatDtoList);
        getListofChat(receiverId);

        return  view;
    }
    private void getListofChat(String receiverId) {
        chatDtoList.clear();
        String senderId = mAuth.getCurrentUser().getUid();
        final String id = setOneToOneChat(receiverId, senderId);
        collectionReferenceforChat.document(id).collection(senderId).orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        ChatDto chatDto = new ChatDto(documentSnapshot.getData().get("message").toString(),
                                documentSnapshot.getData().get("receiverId").toString(),
                                documentSnapshot.getData().get("senderId").toString(),
                                documentSnapshot.getData().get("timestamp")
                        );
                        Log.d("kesharpaudel", documentSnapshot.getId() + "=>" + documentSnapshot.getData().get("message"));
                        chatDtoList.add(chatDto);
                    }

                } else {
                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        collectionReferenceforChat.document(id).collection(receiverId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                ChatDto chatDto = new ChatDto(documentSnapshot.getData().get("message").toString(),
                                        documentSnapshot.getData().get("receiverId").toString(),
                                        documentSnapshot.getData().get("senderId").toString(),
                                        documentSnapshot.getData().get("timestamp"));
                                Log.d("kesharpaudel", documentSnapshot.getId() + "=>" + documentSnapshot.getData().get("message"));
                                chatDtoList.add(chatDto);
                            }
                            chatAdapter.addChatList(chatDtoList);


                        } else {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }

    private void saveMessageInFirebase(final String message, final String receiverId) {
        final String senderId = mAuth.getCurrentUser().getUid();
        Object timestamp=FieldValue.serverTimestamp();
        String id = setOneToOneChat(receiverId, senderId);
        final ChatDto chatDto = new ChatDto(message, receiverId, senderId, timestamp);
        chatDtoList.clear();
        collectionReferenceforChat.document(id).collection(senderId).document().set(chatDto).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "sent:)", Toast.LENGTH_SHORT).show();
                chatDtoList.add(chatDto);
                chatAdapter.addChatList(chatDtoList);
                edtMessage.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String setOneToOneChat(String uid2, String uid1) {
        int compare = uid1.compareTo(uid2);
        if (compare < 0) {
            return uid1 + uid2;
        } else {
            return uid2 + uid1;
        }
    }
}
