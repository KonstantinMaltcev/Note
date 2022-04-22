import org.junit.Assert.*
import org.junit.Test

class CrudTest {
    private val collection = Crud<Note>()
    private var deleted = mutableMapOf<Int, Boolean>()

    @Test
    fun addElement() {
        val list = collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        val result = list.id
        assertEquals(1, result)
    }

    @Test
    fun deleteTrue() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        val result = collection.delete(1)

        assertTrue(result)
    }

    @Test
    fun deleteFalse() {
        val result = collection.delete(1)
        assertFalse(result)
    }

    @Test (expected = NotFoundException::class)
    fun deleteThrows() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        collection.delete(100)

    }

    @Test
    fun updateTrue() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        val result = collection.update(1)
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        val result = collection.update(0)
        assertFalse(result)
    }

    @Test
    fun getTrue() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        val result = collection.get(1).isEmpty()
        assertFalse(result)
    }

    @Test(expected = NotFoundException::class)
    fun getFalse() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        collection.get(1000)
    }

    @Test
    fun restored() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        collection.delete(1)
        deleted[1] = true

        val result = collection.restore(1)
        assertTrue(result)

    }

    @Test
    fun notRestored() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        collection.delete(1)

        val result = collection.restore(2)
        assertFalse(result)
    }

    @Test
    fun uniqueIdEmpty() {
        val result = collection.uniqueId()
        assertEquals(1, result)
    }

    @Test
    fun uniqueIdNotEmpty() {
        collection.add(Note(id = 1, ownerId = 1, title = "IT mem's", text = "Hello, world", date = 0,
            comments = Comment(
                commentId = 0,
                message = "First comment",
                replyTo = 0,
                privacyComment = PrivacySettings.ALL,
                deleted = false
            ), viewUrl = "", count = 20, privacyView = PrivacySettings.ALL,
            deleted = false))
        val result = collection.uniqueId()
        assertEquals(2, result)
    }
}