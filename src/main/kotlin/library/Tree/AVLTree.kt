package org.example.library.Tree

import library.Tree.BinaryTree
import org.example.library.NodePackage.AVLNode
import kotlin.math.max

public class AVLTree<K : Comparable<K>, T> : BinaryTree<K, T, AVLNode<K, T>>() {

    // Получает высоту узла
    private fun getHeight(node: AVLNode<K, T>?): Int {
        return node?.height ?: -1
    }
    // Обновляет высоту узла
    private fun updateHeight(node: AVLNode<K, T>?) {
        node?.height = max(getHeight(node?.left), getHeight(node?.right)) + 1
    }

    // Проверяет сбалансированность дерева
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
    
    override fun insert(key: K, value: T) {
        root = insertRecursion(key, value, root)
    }
    // рекурсивное добавление нового узла с балансировкой
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
        // Балансирует дерево после добавления узла
        updateHeight(node)
        return balanceRotation(node)
    }

    // рекурсивное удаление с балансировкой
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
}