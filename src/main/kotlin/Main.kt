package main.kotlin
import main.kotlin.Tree.AVLTree
import main.kotlin.Tree.BSTree
import org.junit.jupiter.api.Assertions.assertEquals

fun main() {
    var tree : AVLTree<Int, Any> = AVLTree()
//    tree.insert(1, "один")
//    tree.insert(2, "two")
//    tree.insert(3, "three")
//    tree.insert(0, "zero")
//    tree.insert(-2, "-two")
//    tree.insert(-3, "-three")
//    tree.insert(-4, "-четыр")
//    tree.insert(-1,"-один")
//    tree.insert(-1, "fkiwn")
//    tree.printTree()
//    println()
//    tree.delete(3)
//    tree.delete(-2)
//    tree.delete(1)
//    tree.printTree()

//    for (i in 1 .. 5) {
//        tree.insert(i, i)
//    }
//    var i: Int = 1
//    val iter = tree.iterator()
//    println(iter)
//    for (pair in tree) {
//        println("${pair.key}, $i")
//        i++
//    }

    var avlt: AVLTree<Int, String> = AVLTree()
    var mas = listOf(5, 2, 7, 3, -5, 6, 8)
    for (i in 0..<mas.size){
        avlt.insert(mas[i], "${mas[i]}")
    }
//    assertEquals(7, avlt.getSize())
//    avlt.printTree()
    for (i in 0..<mas.size){
        println("${mas[i]} ${avlt.find(mas[i])}")
        avlt.delete(mas[i])


    }
    avlt.printTree()
}