package com.zenika.rennes.mbel.api.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zenika.rennes.mbel.R


class ArticleArrayWithImageAdapter(aContext: Context, articles: List<Article>) : BaseAdapter() {

    private val listData: List<Article> = articles
    private val layoutInflater: LayoutInflater = LayoutInflater.from(aContext)
    private val context: Context = aContext


    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(position: Int): Any {
        return listData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, aconvertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        var convertView: View? = aconvertView
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.article_3_points, null)
            holder = ViewHolder(convertView.findViewById(R.id.icon), convertView.findViewById(R.id.title), convertView.findViewById(R.id.description))
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val article: Article = this.getItem(position) as Article
        holder.titleView.text = article.title
        holder.descriptionView.text = article.description
        Picasso.with(context).load(article.urlToImage).into(holder.iconView)

        return convertView!!

    }
    internal class ViewHolder(i: ImageView, t: TextView, d: TextView) {
        val iconView: ImageView = i
        val titleView: TextView = t
        val descriptionView: TextView = d
    }

}