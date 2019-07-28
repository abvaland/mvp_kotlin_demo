package com.abvaland.tasktechflake.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class VideoTab {
    @Id
    var id: Long = 0

    var videoId: String? = null

    var upVote: Long = 0

    var downVote: Long = 0
}
