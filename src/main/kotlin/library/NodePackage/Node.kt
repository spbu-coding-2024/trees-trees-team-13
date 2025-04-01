package org.example.library.NodePackage

/* Абстрактный класс для всех узлов
* @param K тип ключа
* @param T тип значения
* @param L тип класса дерева
* @property key ключ, по которому определяется положение узла
* @property value значение, хранимое в узле
* @property left левый потомок узла
* @property right правый потомок узла
*/
abstract class Node<K : Comparable<K>, T, L>(
    var key: K,
    var value: T,
    var left: L?,
    var right: L?
)