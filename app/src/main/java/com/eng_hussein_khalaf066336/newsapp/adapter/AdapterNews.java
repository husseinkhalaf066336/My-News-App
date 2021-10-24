package com.eng_hussein_khalaf066336.newsapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.listener.OnClickAddToFavoriteListener;
import com.eng_hussein_khalaf066336.newsapp.listener.OnClickDeleteFormFavoriteListener;
import com.eng_hussein_khalaf066336.newsapp.utils.Utils;
import com.eng_hussein_khalaf066336.newsapp.listener.OnItemClickListener;
import com.eng_hussein_khalaf066336.newsapp.model.Articles;

import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.BreakingNewsViewHolder> {
    private String typeOptionOfPopupMenu;
    private List<Articles> articles;
    Context context;
    OnItemClickListener listener;
    OnClickAddToFavoriteListener onClickAddToFavoriteListener;
    OnClickDeleteFormFavoriteListener onClickDeleteFormFavoriteListener;
    public AdapterNews(List<Articles> articles, Context context, OnItemClickListener listener
    , OnClickAddToFavoriteListener onClickAddToFavoriteListener
    , OnClickDeleteFormFavoriteListener onClickDeleteFormFavoriteListener
    ,String typeOptionOfPopupMenu) {
              this.articles = articles;
              this.context = context;
              this.listener=listener;
              this.onClickAddToFavoriteListener=onClickAddToFavoriteListener;
              this.onClickDeleteFormFavoriteListener=onClickDeleteFormFavoriteListener;
              this.typeOptionOfPopupMenu=typeOptionOfPopupMenu;
          }

          @NonNull
    @Override
    public BreakingNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
              View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
              return new BreakingNewsViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull BreakingNewsViewHolder holder, int position) {
       // final BreakingNewsViewHolder mholder = holders;
        Articles model = articles.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        Glide.with(context)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);
        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.source.setText(model.getSource().getName());
        holder.time.setText(" \u2022 " + Utils.DateToTimeFormat(model.getPublishedAt()));
        holder.published_ad.setText(Utils.DateFormat(model.getPublishedAt()));
        holder.author.setText(model.getAuthor());


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class BreakingNewsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, PopupMenu.OnMenuItemClickListener  {
        TextView title, desc, author, published_ad, source, time;
        ImageView imageView,icon_PopupMenu;
        ProgressBar progressBar;
        public BreakingNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);
            icon_PopupMenu = itemView.findViewById(R.id.icon_PopupMenu);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(v,getAdapterPosition());
                }
            });
            icon_PopupMenu.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
            if (typeOptionOfPopupMenu.equals("add"))
            {
                popupMenu.getMenu().findItem(R.id.AddToFavorite).setVisible(true);
                popupMenu.getMenu().findItem(R.id.DeleteFromFavorite).setVisible(false);

            }
            else
                {
                    popupMenu.getMenu().findItem(R.id.AddToFavorite).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.DeleteFromFavorite).setVisible(true);
            }
        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.AddToFavorite:
                    onClickAddToFavoriteListener.onClickAddToFavoriteListener(getAdapterPosition());
                    return true;
                case R.id.DeleteFromFavorite:
                    onClickDeleteFormFavoriteListener.onClickDeleteFormFavoriteListener(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }
}
