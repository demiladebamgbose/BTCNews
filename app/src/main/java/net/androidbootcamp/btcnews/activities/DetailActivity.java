package net.androidbootcamp.btcnews.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.androidbootcamp.btcnews.R;
import net.androidbootcamp.btcnews.db.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.image)
    ImageView newsImage;
    @BindView(R.id.news_body)
    TextView newsBody;
    @BindView(R.id.source) TextView newsSource;
    @BindView(R.id.author) TextView newsAuthor;
    @BindView(R.id.date) TextView newsDate;
    @BindView(R.id.news_title) TextView newsTitle;

    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();


        // Populate view
        article = intent.getParcelableExtra("Article");
        if (article != null) {

        }
        Glide.with(this).load(article.getUrlToImage()).into(newsImage);
        newsTitle.setText(article.getTitle());
        newsBody.setText(article.getDescription());
        newsSource.setText(article.getSourceName());
        newsAuthor.setText(article.getAuthor());
        newsDate.setText(article.getPublishedAt());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(article.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newsDate.setText((date == null) ? "not available" : date.toString());

    }


}
