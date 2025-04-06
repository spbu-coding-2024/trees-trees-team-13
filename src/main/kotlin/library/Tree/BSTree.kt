package org.example.library.Tree

import library.Tree.BinaryTree
import org.example.library.NodePackage.BSTNode

class BSTree<K : Comparable<K>, T> : BinaryTree<K, T, BSTNode<K, T>>() {
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
}