package tobyspring.helloboot

interface HelloRepository {
    fun findHello(name: String): Hello?

    fun increaseCount(name: String)

    fun countOf(name: String): Int? {
        val hello = findHello(name)
        return hello?.count
    }
}