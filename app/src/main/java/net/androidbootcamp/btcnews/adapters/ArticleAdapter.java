package net.androidbootcamp.btcnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.androidbootcamp.btcnews.R;
import net.androidbootcamp.btcnews.db.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.AdapterViewHolder> {


    private Context mContext;
    private List<Article> mArticles = new ArrayList<>();

    public ArticleAdapter (Context context) {
        this.mContext = context;
    }

    public void setArticles (List<Article> articles) {
        mArticles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        Article article = mArticles.get(position);
        holder.mTitle.setText(article.getTitle());
        Glide.with(mContext).load(article.getUrlToImage()).into(holder.imageView);

//        holder.mCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, DetailActivity.class);
//                //intent.putExtra("Article", article);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView mTitle;
        @BindView(R.id.list_item) LinearLayout mCard;
        @BindView(R.id.image_view) ImageView imageView;

        public AdapterViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
