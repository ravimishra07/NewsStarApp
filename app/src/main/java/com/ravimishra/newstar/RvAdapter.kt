package com.ravimishra.newstar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ravimishra.newstar.Model.Articles
import com.ravimishra.newstar.RvAdapter.MyViewHolder
import com.ravimishra.newstar.Utils.DateFormat
import com.ravimishra.newstar.Utils.randomDrawbleColor

class RvAdapter// this.itemClickListener= (OnItemClickListener) context;
// this.itemClickListener=onItemClickListener;
// static Context context
// public OnItemClickListener itemClickListener;
    (var articles: List<Articles>, var context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    var manager: FragmentManager? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.news_row_layout, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(randomDrawbleColor)
        requestOptions.error(randomDrawbleColor)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        //  requestOptions.override(100, 80);
        val model = articles[i]
        val url = model.url
        val datee = DateFormat(model.date)
        myViewHolder.tle.text = model.title
        myViewHolder.desc.text = model.description
        myViewHolder.date.text = datee
        myViewHolder.source.text = model.source!!.name
        myViewHolder.frame.setOnClickListener {
            //                Toast.makeText(context, "clicked url is \n"+model.getUrl(), Toast.LENGTH_SHORT).show();
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("news detail", url)
            context.startActivity(intent)
            //   manager.beginTransaction().replace(R.id.viewpager,new FragmentDetail()).commit();
        }
        Glide.with(context)
            .load(model.urlToImage)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(myViewHolder.imageView)
        myViewHolder.share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)
            context.startActivity(Intent.createChooser(shareIntent, "choose one"))
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tle: TextView
        var desc: TextView
        var date: TextView
        var source: TextView
        var imageView: ImageView
        var progressBar: ProgressBar? = null
        var frame: FrameLayout
        var share: ImageButton
        lateinit var onItemClickListener: OnItemClickListener

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
        interface OnItemClickListener {
            fun onItemClick(v: View?, position: Int)
        }

        //Context c;
        init {
            tle = itemView.findViewById(R.id.title)
            // view.setOnClickListener(setOnItemClickListener());
            date = itemView.findViewById(R.id.date)
            desc = itemView.findViewById(R.id.descr)
            source = itemView.findViewById(R.id.tvSource)
            imageView = itemView.findViewById(R.id.iv)
            share = itemView.findViewById(R.id.btnShare)
            frame = itemView.findViewById(R.id.framee)
            //onItemClickListener = onItemClickListener
        }
    }
}