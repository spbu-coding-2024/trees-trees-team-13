package test
import library.Tree.AVLTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AVLTreeTest {
    private lateinit var avlt: AVLTree<Int, String>

    @BeforeEach
    fun setup() {
        avlt = AVLTree()
    }
    
    @Test
    fun `find should return null if a node with the given key does not exist`() {
        assertEquals(null, avlt.find(5))
    }

    @Test
    fun `insert should add a node with the given key and value`() {
        avlt.insert(5, "5")
        assertEquals("5", avlt.find(5))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun `delete should remove a node with the given key if it exists`() {
        avlt.insert(5, "five")
        assertEquals("five", avlt.find(5))
        avlt.delete(5)
        assertEquals(null, avlt.find(5))
        assertEquals(0, avlt.getSize())
    }

    @Test
    fun `delete should not remove anything if the key does not exist`() {
        avlt.insert(5, "5")
        avlt.delete(10)
        assertEquals("5", avlt.find(5))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun `delete left child`() {
        avlt.insert(5, "5")
        avlt.insert(3, "3")
        avlt.delete(5)
        assertEquals(null, avlt.find(5))
        assertEquals("3", avlt.find(3))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun `delete right child`() {
        avlt.insert(5, "5")
        avlt.insert(7, "7")
        avlt.delete(5)
        assertEquals(null, avlt.find(5))
        assertEquals("7",avlt.find(7))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun `delete two children`() {
        avlt.insert(5, "5")
        avlt.insert(3, "3")
        avlt.insert(7, "7")
        avlt.delete(5)
        assertEquals(null, avlt.find(5))
        assertEquals("3", avlt.find(3))
        assertEquals("7", avlt.find(7))
        assertEquals(2, avlt.getSize())
    }

    @Test
    fun `find should return 5 if a node with the given key exists`() {
        avlt.insert(5, "5")
        assertEquals("5", avlt.find(5))
    }

    @Test
    fun `min should return the minimum value in the tree`() {
        avlt.insert(5, "5")
        avlt.insert(3, "3")
        avlt.insert(7, "7")
        assertEquals("3", avlt.min())
    }

    @Test
    fun `min should return null if the tree is empty`() {
        assertNull(avlt.min())
    }

    @Test
    fun `max should return the maximum value in the tree`() {
        avlt.insert(5, "5")
        avlt.insert(3, "3")
        avlt.insert(7, "7")
        assertEquals("7", avlt.max())
    }

    @Test
    fun `max should return null if the tree is empty`() {
        assertNull(avlt.max())
    }

    @Test
    fun `size should return the number of nodes in the tree`() {
        assertEquals(0, avlt.getSize())
        avlt.insert(5, "5")
        assertEquals(1, avlt.getSize())
        avlt.insert(-5, "-5")
        assertEquals(2, avlt.getSize())
        avlt.insert(10, "10")
        assertEquals(3, avlt.getSize())
        avlt.delete(5)
        assertEquals(2, avlt.getSize())
    }

    @Test
    fun `size should return 0 for an empty tree`() {
        assertEquals(0, avlt.getSize())
    }

    @Test
    fun hz(){
        var mas = listOf(5, 2, 7, 3, -5, 6, 8)
        for (i in 0..<mas.size){
            avlt.insert(mas[i], "${mas[i]}")
        }
        assertEquals(7, avlt.getSize())
        for (i in 0..<mas.size){
            println("${mas[i]} ${avlt.find(mas[i])}")
            avlt.delete(mas[i])
        }
        avlt.printTree()
        assertEquals(0, avlt.getSize())
    }
}