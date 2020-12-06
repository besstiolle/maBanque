package com.zenika.rennes.mbel.api.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.zenika.rennes.mbel.R


class ArticleArrayWithoutImageAdapter(aContext: Context, articlesData: List<ArticlesDataChildren>) : BaseAdapter() {

    private val listData: List<ArticlesDataChildren> = articlesData
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
            convertView = layoutInflater.inflate(R.layout.article_2_points, null)
            holder = ViewHolder(convertView.findViewById(R.id.title), convertView.findViewById(R.id.description))
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val articleDataChildren: ArticlesDataChildren = this.getItem(position) as ArticlesDataChildren
        val article: Article = articleDataChildren.data
        holder.titleView.text = article.title
        holder.descriptionView.text = article.url_overridden_by_dest?:"N/A"

        makeClickable(convertView?.findViewById(R.id.title), article.url_overridden_by_dest)
        makeClickable(convertView?.findViewById(R.id.description), article.url_overridden_by_dest)

        return convertView!!

    }
    internal class ViewHolder(t: TextView, d: TextView) {
        val titleView: TextView = t
        val descriptionView: TextView = d
    }

    private fun makeClickable(item: View?, url: String?) {
        if(url == null){return}

        //Plug click event
        item?.setOnClickListener {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } catch (e: Exception){
                e.printStackTrace()
            }
            //finishAffinity()
        }
    }
}