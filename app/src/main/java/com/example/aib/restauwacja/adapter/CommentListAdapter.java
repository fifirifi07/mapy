package com.example.aib.restauwacja.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.aib.restauwacja.data.Comment;
import com.example.aib.restauwacja.data.CommentList;
import com.example.aib.restauwacja.itemView.CommentItemView;
import com.example.aib.restauwacja.itemView.CommentItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EBean
public class CommentListAdapter extends BaseAdapter {
    @RootContext
    Context context;
    List<Comment> comments = new ArrayList<Comment>();
    public CommentListAdapter() {
        //fake comments
        //comment 1
        Comment comment = new Comment();
        comment.author = "Paweł Mieszkowski";
        comment.rating = 1;
        comment.text = "Dostałem raka od samego patrzenia na opis. Nie polecam";
        comment.created = "12/05/2015";
        comments.add(comment);
        //comment 2
        comment = new Comment();
        comment.author = "Janusz Nowak";
        comment.rating = 3;
        comment.text = "Niby dobre ale drogo i nie mają Vipa";
        comment.created = "14/05/2015";
        comments.add(comment);
    }
    //update adapter after receiving new commentList
    public void update(CommentList commentList) {
        comments.clear();
        comments.addAll(commentList.records);
        //to sort comments you need to add comparator in Comment class
        //Collections.sort(comments);
        notifyDataSetChanged();
    }
    public void add(Comment comment){
        comments.add(comment);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return comments.size();
    }
    @Override
    public Comment getItem(int i) {
        return comments.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentItemView commentItemView;
        if (convertView == null) {
            commentItemView = CommentItemView_.build(context);;
        } else {
            commentItemView = (CommentItemView) convertView;
        }
        commentItemView.bind(getItem(position));
        return commentItemView;
    }
}

