package com.zenika.rennes.mbel.api.model



class ArticlesWrapper(val data: ArticlesData)
class ArticlesData(val children: List<ArticlesDataChildren>)
class ArticlesDataChildren(val data: Article)
class Article(val title: String?, val url_overridden_by_dest: String?, val thumbnail: String?)
