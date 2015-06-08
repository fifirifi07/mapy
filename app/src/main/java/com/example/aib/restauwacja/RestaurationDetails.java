package com.example.aib.restauwacja;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.aib.restauwacja.adapter.CommentListAdapter;
import com.example.aib.restauwacja.data.Comment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@OptionsMenu(R.menu.menu_restauration_details)
@EActivity(R.layout.activity_restauration_details)
public class RestaurationDetails extends ActionBarActivity {

    @ViewById(R.id.makeButton)
    Button reservationBtn;
    @ViewById
    ListView commentList;
    @Bean
    CommentListAdapter adapter;
    @ViewById
    RatingBar ratingBar;
    @ViewById
    EditText newcomment;

    @AfterViews
    void init(){
        commentList.setAdapter(adapter);
        resizeCommentList();
        ReservationOnClick();
    }

    @Click
    void commentbtnClicked(){
        //do nothing if comment is empty
        if(newcomment.getText().toString().trim().isEmpty()) return;
        //create new comment object
        Comment newComment = new Comment();
        newComment.text = newcomment.getText().toString().trim();
        newComment.rating = (int)ratingBar.getRating();
        //temporary, should be current user's name
        newComment.author = "User";
        //add new comment to adapter, change to permanent storage
        adapter.add(newComment);
        resizeCommentList();
    }

    public void ReservationOnClick()
    {
        //reservationBtn = (Button)findViewById(R.id.makeButton);
        reservationBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.aib.restauwacja.DateAndTimePicker");
                        startActivity(intent);
                    }
                }
        );
    }
    //manually resize listview, since it does not work well with parent scrollview
    private void resizeCommentList(){
        ViewGroup.LayoutParams params = commentList.getLayoutParams();
        int dim = (int)getResources().getDimension(R.dimen.comment_list_item_height);
        params.height = adapter.getCount() * dim;
        commentList.setLayoutParams(params);
    }
}


