package main.kotlin.NodePackage
abstract class Node<K : Comparable<K>, T, L>(
    var key: K,
    var value: T,
    var left: L?,
    var right: L?
)