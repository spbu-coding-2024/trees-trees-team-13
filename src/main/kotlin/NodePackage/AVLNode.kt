package main.kotlin.NodePackage

import kotlin.math.max

class AVLNode<K : Comparable<K>, T>(
    key: K,
    value: T,
    left: AVLNode<K, T>? = null,
    right: AVLNode<K, T>? = null,
) : Node<K, T, AVLNode<K, T>>(key, value, left, right) {
    internal var height: Int = 0
}