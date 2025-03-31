package org.example.library.Tree

import org.example.library.NodePackage.AVLNode
import kotlin.math.max

public class AVLTree<K : Comparable<K>, T> : BinaryTree<K, T, AVLNode<K, T>> {
    private var root: AVLNode<K, T>? = null
    private var size: Int = 0

    // Получает высоту узла
    private fun getHeight(node: AVLNode<K, T>?): Int {
        return node?.height ?: -1
    }
    // Обновляет высоту узла
    private fun updateHeight(node: AVLNode<K, T>?) {
        node?.height = max(getHeight(node?.left), getHeight(node?.right)) + 1
    }

    // Проверяет сбалансированость дерева
    private fun balance(node: AVLNode<K, T>?): Int {
        return if (node == null) 0 else getHeight(node.left) - getHeight(node.right)
    }

    // Делает правый поворот
    private fun rightRotation(node: AVLNode<K, T>?): AVLNode<K, T>? {
        val leftNode = node?.left
        val rightOfLeftNode = leftNode?.right

        leftNode?.right = node
        node?.left = rightOfLeftNode

        updateHeight(leftNode)
        updateHeight(node)

        return leftNode
    }

    // Делает левый поворот
    private fun leftRotation(node: AVLNode<K, T>?): AVLNode<K, T>? {
        val rightNode = node?.right
        val leftOfRightNode = rightNode?.left

        rightNode?.left = node
        node?.right = leftOfRightNode

        updateHeight(rightNode)
        updateHeight(node)

        return rightNode
    }

    // Балансирует дерево для разных случаев (левый правый и правый левый поворот)
    private fun balanceRotation(node: AVLNode<K, T>?): AVLNode<K, T>? {
        if (node == null) return null
        val balance = balance(node)

        if (balance == 2) {
            if (balance(node.left) == -1) {
                node.left = leftRotation(node.left)
            }
            return rightRotation(node)
        } else if (balance == -2) {
            if (balance(node.right) == 1) {
                node.right = rightRotation(node.right)
            }
            return leftRotation(node)
        }
        return node
    }
    
    override fun insert(key: K, value: T, node: AVLNode<K, T>?) {
        root = insertRecursion(key, value, root)
    }

    private fun insertRecursion(key: K, value: T, node: AVLNode<K, T>?): AVLNode<K, T>? {
        if (node == null) {
            size++
            return AVLNode(key, value)
        }
        if (key < node.key) node.left = insertRecursion(key, value, node.left)
        else if (key > node.key) node.right = insertRecursion(key, value, node.right)
        else {
            node.value = value;
            return node
        }
        // Балансирует дерево после добавлнения узла
        updateHeight(node)
        return balanceRotation(node)
    }


    private fun remove(key: K, node: AVLNode<K, T>?): AVLNode<K, T>? {
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
                    val el: AVLNode<K, T>? = maxNode(node.left)
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
        // Балансирует дерево после удаления узла
        updateHeight(node)
        return balanceRotation(node)

    }

    override fun delete(key: K) {
        root = remove(key, root)
    }

    override fun find(key: K): T? {
        return findNode(key)?.value
    }

    private fun findNode(key: K): AVLNode<K, T>? {
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

    private fun minNode(root: AVLNode<K, T>?): AVLNode<K, T>? {
        var root_copy = root
        while (root_copy?.left != null)
            root_copy = root_copy.left
        return root_copy
    }

    override fun min(): T? {
        return minNode(root)?.value
    }

    private fun maxNode(root: AVLNode<K, T>?): AVLNode<K, T>? {
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

    private fun printTreePrivate(node: AVLNode<K, T>?){
        if (node == null) return
        printTreePrivate(node.left)
        println("${node.key} : ${node.value}")
        printTreePrivate(node.right)
    }

    override fun getSize(): Int {
        return size
    }

    inner class Iterate : Iterator<AVLNode<K, T>> {
        var array: ArrayDeque<AVLNode<K, T>> = ArrayDeque()
        var f: Boolean = true

        override fun next(): AVLNode<K, T> {
            return array.removeAt(0)
        }

        override fun hasNext(): Boolean {
            if (f) {
                getNode(root)
                f = false
            }
            return array.isNotEmpty()
        }

        fun getNode(node: AVLNode<K, T>?) {
            if (node != null) {
                getNode(node.left)
                array.add(AVLNode(node.key, node.value))
                getNode(node.right)
            }
        }
    }

    operator fun iterator(): Iterate {
        return this.Iterate()
    }
}