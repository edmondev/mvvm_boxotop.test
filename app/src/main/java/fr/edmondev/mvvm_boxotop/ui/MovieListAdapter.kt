package fr.edmondev.mvvm_boxotop.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import fr.edmondev.mvvm_boxotop.R
import fr.edmondev.mvvm_boxotop.model.MovieListItem
import fr.edmondev.mvvm_boxotop.ui.MovieListAdapter.*
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieListAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: MutableList<MovieListItem> = mutableListOf()
    private lateinit var ctx: Context

    fun updateDataSet(items: List<MovieListItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ShapeableImageView = itemView.movie_image
        val titleView: TextView = itemView.title_textview
        val infosView: TextView = itemView.infos_textview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ctx = parent.context;
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)

        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(items[position])

        val item = items[position]
        holder.titleView.text = item.title
        holder.infosView.text = "${item.type} - ${item.year}"
        //Picasso.get().load(item.poster_url).resize(100, 100).centerCrop().into(holder.imageView)
        Picasso.get().load(item.poster_url).fit().into(holder.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, MovieDetailActivity::class.java)
            intent.putExtra("imdbId", item.id)
            ContextCompat.startActivity(ctx, intent, null)
        }
    }

    override fun getItemCount(): Int = items.size
}