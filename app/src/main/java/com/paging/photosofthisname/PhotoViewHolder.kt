package com.paging.photosofthisname

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paging.photosofthisname.domain.model.FlickrPhoto
import com.paging.photosofthisname.presentation.loadImage

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val description: TextView = view.findViewById(R.id.photo_description)
    private val datesTaken: TextView = view.findViewById(R.id.dates_taken)
    private val owner: TextView = view.findViewById(R.id.owner_name)
    private val image: ImageView = view.findViewById(R.id.small_image)
    private var repo: FlickrPhoto? = null

    init {
        view.setOnClickListener {
            repo?.imageUrlMedium?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(repo: FlickrPhoto?) {
        repo?.let {
            showRepoData(it)
        }
    }

    private fun showRepoData(flickrPhoto: FlickrPhoto) {
        this.repo = flickrPhoto
        description.text = setText(flickrPhoto.description)
        owner.text = setText(flickrPhoto.owner)
        datesTaken.text = setText(flickrPhoto.datesTaken)
        image.loadImage(flickrPhoto.imageUrlSmall)
    }

    private fun setText(content: String): Spanned =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                content,
                Html.FROM_HTML_MODE_COMPACT
            )
        } else {
            Html.fromHtml(content)
        }

    companion object {
        fun create(parent: ViewGroup): PhotoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_view_item, parent, false)
            return PhotoViewHolder(view)
        }
    }
}