package com.example.laughingmn.picscan;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.example.laughingmn.picscan.R;
import com.example.laughingmn.picscan.other.Profile;
import com.example.laughingmn.picscan.other.TinderCard;
import com.example.laughingmn.picscan.other.Utils;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

/**
 * Created by laughingmn on 2/5/17.
 */

public class PhotoActivity extends AppCompatActivity {
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        int bottomMargin = Utils.dpToPx(5);
        Point windowSize = Utils.getDisplaySize(getWindowManager());
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));


        for(Profile profile : Utils.loadProfiles(this.getApplicationContext())){
            mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));
        }

//        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSwipeView.doSwipe(false);
//            }
//        });
//
//        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSwipeView.doSwipe(true);
//            }
//        });
    }

    public void onBackPressed(){
        this.finish();

    }
}

