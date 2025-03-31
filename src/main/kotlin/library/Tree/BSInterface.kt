package org.example.library.Tree

interface BinaryTree<K, T, P> {
    fun insert(key: K, value: T, node: P? = null) // Добавление узла
    fun delete(key: K) // Удаление узла
    fun find(key: K): T? // Поиск знаяения по
    fun min(): T? // Поиск минимального ключа
    fun max(): T? // Поиск максимального ключа
    fun printTree() // Вывод
    fun getSize(): Int // Размер дерева
}

