package com.ravimishra.newstar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.ravimishra.newstar.Model.Articles;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    List<Articles> articles;
    Context context;
    FragmentManager manager;
    // static Context context
   // public OnItemClickListener itemClickListener;

    public RvAdapter(List<Articles> articles, Context context) {
        this.articles = articles;
        this.context = context;

       // this.itemClickListener= (OnItemClickListener) context;
       // this.itemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_row_layout, null);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        final Articles model = articles.get(i);
        final String url=model.getUrl();
        String datee = Utils.DateFormat(model.getDate());
        myViewHolder.tle.setText(model.getTitle());
        myViewHolder.desc.setText(model.getDescription());
        myViewHolder.date.setText(datee);
        myViewHolder.source.setText(model.getSource().getName());
        myViewHolder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(context, "clicked url is \n"+model.getUrl(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("news detail",url);
                context.startActivity(intent);
             //   manager.beginTransaction().replace(R.id.viewpager,new FragmentDetail()).commit();

            }
        });
        Glide.with(context)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(myViewHolder.imageView);


    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView tle, desc, date, source;
        ImageView imageView;
        ProgressBar progressBar;
        FrameLayout frame;
        OnItemClickListener onItemClickListener;
        //Context c;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tle = itemView.findViewById(R.id.title);
           // view.setOnClickListener(setOnItemClickListener());
            date = itemView.findViewById(R.id.date);
            desc = itemView.findViewById(R.id.descr);
            source = itemView.findViewById(R.id.tvSource);
            imageView = itemView.findViewById(R.id.iv);
            frame = itemView.findViewById(R.id.framee);

            this.onItemClickListener = onItemClickListener;
        }


//        @Override
//        public void onClick(View v) {
//            if(onItemClickListener==null){
//                Toast.makeText(tle.getContext(), "listner is null", Toast.LENGTH_SHORT).show();
//            }
//            else
//           onItemClickListener.onItemClick(v, getLayoutPosition());
//
//
//            Toast.makeText(tle.getContext(), "Clicked", Toast.LENGTH_SHORT).show(); }
//    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}}