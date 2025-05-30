package org.example.library.NodePackage
// Класс реализующий узел бинарного дерева поиска
class BSTNode<K : Comparable<K>, T>(
    key: K,
    value: T,
    left: BSTNode<K, T>? = null,
    right: BSTNode<K, T>? = null
) : Node<K, T, BSTNode<K, T>>(key, value, left, right)
