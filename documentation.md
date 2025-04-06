# Documentation

Каждое дерево содержит пару `ключ-значение` произвольного типа (с ограничением на сравнимость типа ключа) или не содержит ничего (null)

# Методы деревьев

## Бинарное дерево поиска
*  `insert(key: K, value: T)` - добавляет новый узел
*  `delete(key: K)` - удаляет узел
*  `find(key: K): T?` - поиск значения по ключу
*  `min(): T?` - поиск минимального значения
*  `max(): T?` - поиск максимального значения
*  `printTree()` - вывод дерева
*  `getSize(): Int` - размер дерева
*  `operator iterator(): Iterate` - возвращает список пар "ключ-значение"

Приватные методы: 
* `maxNode(root: BSTNode<K, T>?): BSTNode<K, T>?` - возвращает максимальный узел или `null` если такого узла нет
* `add(key: K, value: T, node: BSTNode<K, T>?)` - добавляет новый узел с помощью рекурсии
* `remove(key: K, node: BSTNode<K, T>?): BSTNode<K, T>?` - удаляет узел с помощью рекурсии
* `findNode(key: K): BSTNode<K, T>?` - возвращает узел или `null` если такого узла нет
* `minNode(root: BSTNode<K, T>?): BSTNode<K, T>?` - возвращает минимальный узел
* `printTreePrivate(node: BSTNode<K, T>?)` - вывод дерева

## AVL дерево
*  `insert(key: K, value: T)` - добавляет новый узел
*  `delete(key: K)` - удаляет узел
*  `find(key: K): T?` - поиск значения по ключу
*  `min(): T?` - поиск минимального значения
*  `max(): T?` - поиск максимального значения
*  `printTree()` - вывод дерева
*  `getSize(): Int` - размер дерева
*  `operator iterator(): Iterate` - возвращает список пар "ключ-значение"

Приватные методы:
* `getHeight(node: AVLNode<K, T>?): Int` - получает высоту узла
* `updateHeight(node: AVLNode<K, T>?)` - обновляет высоту узла
* `balance(node: AVLNode<K, T>?): Int` - возвращает число, которое обозначает, сбалансировано ли дерево
* `rightRotation(node: AVLNode<K, T>?): AVLNode<K, T>?` - делает правый поворот
* `leftRotation(node: AVLNode<K, T>?): AVLNode<K, T>?` - делает левый поворот
* `balanceRotation(node: AVLNode<K, T>?): AVLNode<K, T>?` - балансирует дерево для разных случаев (левый правый и правый левый поворот)
* `maxNode(root: BSTNode<K, T>?): BSTNode<K, T>?` - возвращает максимальный узел или `null` если такого узла нет
* `add(key: K, value: T, node: BSTNode<K, T>?)` - добавляет новый узел с помощью рекурсии
* `remove(key: K, node: BSTNode<K, T>?): BSTNode<K, T>?` - удаляет узел с помощью рекурсии
* `findNode(key: K): BSTNode<K, T>?` - возвращает узел или `null` если такого узла нет
* `minNode(root: BSTNode<K, T>?): BSTNode<K, T>?` - возвращает минимальный узел
* `printTreePrivate(node: BSTNode<K, T>?)` - вывод дерева


## Руководство по использованию
Создание дерева
```kotlin
var bst: BSTree<KeyType, ValueType> = BSTree<KeyType, ValueType>()
```
```kotlin
var avlt: AVLTree<KeyType, ValueType> = AVLTree<KeyType, ValueType>()
```
Примеры методов
* Вставка
```kotlin
tree.insert(key, value)
```
* Удаление
```kotlin
tree.delete(key)
```
* Поиск
```kotlin
var value: ValueType = tree.find(key)
```
* Итерация по парам ключ-значение:
```kotlin
for (el in tree) {
  println("Key: + ${el.first}; Value: ${el.second}")
}
```
