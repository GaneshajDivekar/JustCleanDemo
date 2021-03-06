package just.cleandemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import just.cleandemo.databinding.PostsLayoutBinding
import just.cleandemo.interfaces.ItemClick
import just.cleandemo.model.databaseclass.PostsDB


class FavAdapter(
    var context: Context,
    var allPosts: List<PostsDB>
) : RecyclerView.Adapter<FavAdapter.RecyclerViewHolder>() {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return if (allPosts == null) {
            0
        } else {
            allPosts!!.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentUser = allPosts!![position]
        holder.binding.postdb = currentUser
        holder.binding.executePendingBindings()
    }

    class RecyclerViewHolder(val binding: PostsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = PostsLayoutBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return RecyclerViewHolder(binding)
    }

}