package just.cleandemo.interfaces

import just.cleandemo.model.databaseclass.PostsDB

interface ItemClick {
    fun onClick(pos: PostsDB)
}