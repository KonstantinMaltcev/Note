open class Crud<T : ElementId> {
    private var deleted = mutableMapOf<Int, Boolean>()
    private val collection = mutableListOf<T>()


    /**
     * Создание нового элемента коллекции.
     */
    internal fun add(element: T): T {
        element.id = uniqueId()
        collection.add(element)
        deleted[element.id] = false
        return collection.last()
    }

    /**
     * Удаление элемента коллекции.
     */
    internal fun delete(id: Int): Boolean {
        for (value in collection) {
            if (deleted[id] == false) {
                deleted[id] = true
                return true
            }
            throw NotFoundException("Удалять нечего")
        }
        return false
    }

    /**
     * Обновление элемента коллекции.
     */
    internal fun update(id: Int): Boolean {
        for ((index, elements) in collection.withIndex()) {
            if (elements.id == id) {
                collection[index] = elements
                return true
            }
            println("Обновление не удалось!")
        }
        return false
    }

    /**
     * Получение элемента коллекции.
     */
    internal fun get(id: Int): List<T> {
        for (element in collection) {
            if (collection.isEmpty() || deleted[id] == true || element.id != id) {
                throw  NotFoundException("Collection not found")
            }
        }
        return collection
    }

    /**
     * Восстановление удаленного элемента коллекции.
     */
    internal fun restore(id: Int): Boolean {
        for (elements in collection) {
            if (elements.id == id && deleted[id] == true) {
                deleted[id] = false
                return true
            }
            println("The element wasn't deleted")
        }
        return false
    }


    /**
     * Создание уникального Id
     */
    fun uniqueId(): Int {
        if (collection.isEmpty()) {
            return 1
        }
        return collection.size + 1
    }

}