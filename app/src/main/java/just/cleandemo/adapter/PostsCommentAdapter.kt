package just.cleandemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import just.cleandemo.databinding.PostsCommentLayoutBinding
import just.cleandemo.interfaces.ItemClickComment
import just.cleandemo.model.postcommentResponse.PostCommentResponse


class PostsCommentAdapter(
    var context: Context,
    var allPostsComment: PostCommentResponse,
    var itemClickEvent: ItemClickComment
) : RecyclerView.Adapter<PostsCommentAdapter.RecyclerViewHolder>() {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return if (allPostsComment == null) {
            0
        } else {
            allPostsComment!!.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentUser = allPostsComment!![position]
        holder.binding.txtBody.setText(currentUser.body)
        holder.binding.txtEmail.setText(currentUser.email)
        holder.binding.txtId.setText(currentUser.id.toString())
        holder.binding.txtName.setText(currentUser.name)
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            itemClickEvent.onClick(allPostsComment)
        }

    }

    class RecyclerViewHolder(val binding: PostsCommentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding =
            PostsCommentLayoutBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return RecyclerViewHolder(binding)
    }

}