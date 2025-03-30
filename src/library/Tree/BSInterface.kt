package library.Tree

interface BinaryTree<K, T, P> {
    fun insert(key: K, value: T, node: P? = null)
    fun delete(key: K)
    fun find(key: K): T?
    fun min(): T?
    fun max(): T?
//    fun findNode(key: K): P?
    fun printTree()
    fun getSize(): Int
}

