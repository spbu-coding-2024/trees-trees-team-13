package library.Tree

import org.example.library.NodePackage.Node

abstract class BinaryTree<K : Comparable<K>, T, NODE : Node<K, T, NODE>>() : Iterable<Pair<K, T>> {
    internal var root: NODE? = null
    internal var size: Int = 0

    abstract fun insert(key: K, value: T)
    abstract fun delete(key: K)

    public fun find(key: K): T? {
        return findNode(key)?.value
    }

    // поиск узла
    protected fun findNode(key: K): NODE? {
        if (root == null) {
            return null
        } else {
            var node = root
            while (node != null) {
                if (node.key == key) return node
                else if (node.key > key) {
                    node = node.left
                } else {
                    node = node.right
                }
            }
            return null
        }
    }

    fun minNode(root: NODE?): NODE? {
        var root_copy = root
        while (root_copy?.left != null)
            root_copy = root_copy.left
        return root_copy
    }

    public fun min(): T? {
        return minNode(root)?.value
    }

    // поиск узла с максимальным узлом
    protected fun maxNode(root: NODE?): NODE? {
        var root_copy = root
        while (root_copy?.right != null)
            root_copy = root_copy.right
        return root_copy
    }

    public fun max(): T? {
        return maxNode(root)?.value
    }

    public fun printTree() {
        printTreePrivate(root)
    }

    protected fun printTreePrivate(node: NODE?) {
        if (node == null) return
        printTreePrivate(node.left)
        println("${node.key} : ${node.value}")
        printTreePrivate(node.right)
    }

    public fun getSize(): Int {
        return size
    }

    inner class Iterate : Iterator<Pair<K, T>> {
        var array: ArrayDeque<Pair<K, T>> = ArrayDeque()
        var f: Boolean = true

        override fun next(): Pair<K, T> {
            return array.removeFirst()
        }

        override fun hasNext(): Boolean {
            if (f) {
                getNode(root)
                f = false
            }
            return array.isNotEmpty()
        }

        fun getNode(node: NODE?) {
            if (node != null) {
                getNode(node.left)
                array.add(Pair(node.key, node.value))
                getNode(node.right)
            }
        }
    }

    override operator fun iterator(): Iterate {
        return this.Iterate()
    }
}