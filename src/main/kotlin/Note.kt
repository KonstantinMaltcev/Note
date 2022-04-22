open class Note(
    id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Comment,
    val viewUrl: String,
    val count: Int = 20,
    val privacyView: PrivacySettings = PrivacySettings.ALL,
    var deleted: Boolean,
) : ElementId(id)

data class Comment
    (
    val commentId: Int,
    val message: String,
    val replyTo: Int?,
    val privacyComment: PrivacySettings = PrivacySettings.ALL,
    var deleted: Boolean,
) : ElementId(commentId)