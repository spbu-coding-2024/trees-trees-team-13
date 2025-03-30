//package main.kotlin.Tree
//
//import main.kotlin.NodePackage.BSTNode
//import main.kotlin.NodePackage.Node
//abstract class BinaryTree1 <K: Comparable<K>, T, P: Node<K, T, P>>{
//    var root: Node<K, T, P>? = null
//    var size: Int = 0
//
//    abstract fun insert(key: K, value: T, node: P? = null)
//    abstract fun delete(key: K)
//    abstract fun find(key: K): Boolean
//    abstract fun min(): T?
//    abstract fun max(): T?
//    //   abstract fun findNode(key: K): P?
//    abstract fun getSize(): Int
//    abstract fun printTree()
//}