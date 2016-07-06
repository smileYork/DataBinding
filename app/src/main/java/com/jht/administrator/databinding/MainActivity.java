package com.jht.administrator.databinding;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jht.administrator.bean.Note;
import com.jht.administrator.bean.Score;
import com.jht.administrator.bean.User;
import com.jht.administrator.global.NetiWorkUtil;
import com.jht.administrator.service.NoteService;
import com.jht.administrator.service.ScoreService;
import com.jht.administrator.service.UserService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.StatsSnapshot;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello)
    TextView tv_hello;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.imageView)
    ImageView imageView;

    String TAG = "DataBinding";

    private NoteService noteService;

    private ScoreService scoreService;

    private UserService userService;

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mContext = MainActivity.this;

        noteService = NoteService.getInstance(mContext);

        scoreService = ScoreService.getInstance(mContext);

        userService = UserService.getInstance(mContext);

        long id = 3;

        Note note = new Note(id, "1", "GreenDao 测试", new Date());

        User user = new User(id, "陈遥", "男", new Date());

        Score score = new Score(id, id, 123.0, new Date());

        noteService.saveNote(note);

        scoreService.saveScore(score);

        userService.saveUser(user);

        Picasso.with(MainActivity.this).setLoggingEnabled(true);

        Picasso.with(MainActivity.this).setIndicatorsEnabled(true);

        Picasso.with(MainActivity.this).load("http://i.imgur.com/DvpvklR.png").into(imageView);

        StatsSnapshot picassoStats = Picasso.with(MainActivity.this).getSnapshot();

        NetiWorkUtil.getWithAbstrctAddress("http://www.baidu.com",loginResultHttpResponseHandler);

        Logger.init(TAG);

        List<User> list_user = userService.loadAllUser();

        //然后打印
        Logger.d("user     "+list_user.size());

    }

        private AsyncHttpResponseHandler loginResultHttpResponseHandler = new AsyncHttpResponseHandler(){


        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            Logger.d("访问成功");
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Logger.d("访问失败");
        }
    };

    @OnClick(R.id.button)
    void click(){

        Toast.makeText(this,"12345",Toast.LENGTH_SHORT).show();

        tv_hello.setText(et_name.getText().toString());

        Logger.d("你好");

        Logger.e("hello");

    }
}
