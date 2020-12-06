package com.zenika.rennes.mbel.api.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.zenika.rennes.mbel.R


class ArticleArrayWithoutImageAdapter(aContext: Context, articles: List<Article>) : BaseAdapter() {

    private val listData: List<Article> = articles
    private val layoutInflater: LayoutInflater = LayoutInflater.from(aContext)


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
            convertView = layoutInflater.inflate(R.layout.article_2_points, null)
            holder = ViewHolder(convertView.findViewById(R.id.title), convertView.findViewById(R.id.description))
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val article: Article = this.getItem(position) as Article
        holder.titleView.text = article.title
        holder.descriptionView.text = article.description

        return convertView!!

    }
    internal class ViewHolder(t: TextView, d: TextView) {
        val titleView: TextView = t
        val descriptionView: TextView = d
    }

}