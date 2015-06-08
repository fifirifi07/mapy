package com.example.aib.restauwacja.itemView;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aib.restauwacja.R;
import com.example.aib.restauwacja.data.Comment;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EViewGroup(R.layout.comment_list_item)
public class CommentItemView extends RelativeLayout {
    @ViewById
    TextView author;
    @ViewById
    TextView text;
    @ViewById
    TextView date;
    @ViewById
    ImageView star1;
    @ViewById
    ImageView star2;
    @ViewById
    ImageView star3;
    @ViewById
    ImageView star4;
    @ViewById
    ImageView star5;

    List<ImageView> stars;

    public CommentItemView(Context context) {
        super(context);

    }
    public void bind(Comment comment) {
        if(comment.author != null) author.setText(comment.author);
        else author.setText("User " + Integer.toString(comment.ownerId));
        text.setText(comment.text);
        if (comment.created != null) date.setText(comment.created);
        //change some stars to full according to rating
        stars = new ArrayList<>();
        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);
        for(int i = 0; i< comment.rating; i++) stars.get(i).setImageResource(R.drawable.starfull);
    }
}
