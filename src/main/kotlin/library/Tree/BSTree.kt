package org.example.library.Tree

import org.example.library.NodePackage.BSTNode

class BSTree<K : Comparable<K>, T> : BinaryTree<K, T, BSTNode<K, T>> {
    private var root: BSTNode<K, T>? = null
    private var size: Int = 0


    private fun add(key: K, value: T, node: BSTNode<K, T>?) {
        if (node != null) {
            if (key < node.key) {
                if (node.left == null) {
                    node.left = BSTNode(key, value)
                    size += 1
                } else add(key, value, node.left)
            } else if (key > node.key) {
                if (node.right == null) {
                    node.right = BSTNode(key, value)
                    size += 1
                } else add(key, value, node.right)
            }
        } else if (root == null) {
            size += 1
            root = BSTNode(key, value)
        } else add(key, value, root)
    }

    override fun insert(key: K, value: T) {
        add(key, value, root)
    }

    // удаление узла через рекурсию
    private fun remove(key: K, node: BSTNode<K, T>?): BSTNode<K, T>? {
        if (node == null) return null
        else if (node.key < key) node.right = remove(key, node.right)
        else if (node.key > key) node.left = remove(key, node.left)
        else if (node.left == null) {
            size -= 1
            return node.right
        } else if (node.right == null) {
            size -= 1
            return node.left
        } else {
            if (node.right == null && node.left == null)
                return null
            else {
                if (node.left != null) {
                    val el: BSTNode<K, T>? = maxNode(node.left)
                    if (el != null) {
                        node.value = el.value
                        node.key = el.key
                        node.left = remove(el.key, node.left)
                    }
                } else {
                    return node.right
                }
            }
        }
        return node
    }

    override fun delete(key: K): Unit {
        root = remove(key, root)
    }

    override fun find(key: K): T? {
        return findNode(key)?.value
    }

    // поиск узла
    private fun findNode(key: K): BSTNode<K, T>? {
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

    private fun minNode(root: BSTNode<K, T>?): BSTNode<K, T>? {
        var root_copy = root
        while (root_copy?.left != null)
            root_copy = root_copy.left
        return root_copy
    }

    override fun min(): T? {
        return minNode(root)?.value
    }

    // поиск узла с максимальным узлом
    private fun maxNode(root: BSTNode<K, T>?): BSTNode<K, T>? {
        var root_copy = root
        while (root_copy?.right != null)
            root_copy = root_copy.right
        return root_copy
    }

    override fun max(): T? {
        return maxNode(root)?.value
    }

    override fun printTree() {
        printTreePrivate(root)
    }

    private fun printTreePrivate(node: BSTNode<K, T>?) {
        if (node == null) return
        printTreePrivate(node.left)
        println("${node.key} : ${node.value}")
        printTreePrivate(node.right)
    }

    override fun getSize(): Int {
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

        fun getNode(node: BSTNode<K, T>?) {
            if (node != null) {
                getNode(node.left)
                array.add(Pair(node.key, node.value))
                getNode(node.right)
            }
        }
    }

    operator fun iterator(): Iterate {
        return this.Iterate()
    }
}