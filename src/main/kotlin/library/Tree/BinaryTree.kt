package org.example.library.Tree

interface BinaryTree<K, T, P> {
    fun insert(key: K, value: T) // Добавление узла
    fun delete(key: K) // Удаление узла
    fun find(key: K): T? // Поиск значения
    fun min(): T? // Поиск минимального значения
    fun max(): T? // Поиск максимального значения
    fun printTree() // Вывод
    fun getSize(): Int // Размер дерева
}

