package com.example.hiretutornepaltutee;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    private List<ChatDto> chatDtoList=new ArrayList<>();
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        if(position==0){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chat_layout2,parent,false);
            return new ChatAdapter.ChatViewHolder(view);
        }else {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chat_layout,parent,false);
            return new ChatAdapter.ChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int i) {
        ChatDto currentDetails=chatDtoList.get(i);
        chatViewHolder.txtMessage.setText(currentDetails.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatDtoList.size();
    }
    public void addChatList(List<ChatDto> chatDtos){
        chatDtoList.addAll(chatDtos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(chatDtoList.get(position).getSenderId())){
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
        ImageView profilePic;
        TextView txtMessage;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic=itemView.findViewById(R.id.profilePic);
            txtMessage=itemView.findViewById(R.id.message);
        }
    }
}
