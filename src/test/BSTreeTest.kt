package test
import library.Tree.BSTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BSTreeTest {

    private lateinit var bst: BSTree<Int, String>

    @BeforeEach
    fun setup() {
        bst = BSTree()
    }

    @Test
    fun `find should return null if a node with the given key does not exist`() {
        assertEquals(null, bst.find(5))
    }

    @Test
    fun `insert should add a node with the given key and value`() {
        bst.insert(5, "5")
        assertEquals("5", bst.find(5))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun `delete should remove a node with the given key if it exists`() {
        bst.insert(5, "five")
        assertEquals("five", bst.find(5))
        bst.delete(5)
        assertEquals(null, bst.find(5))
        assertEquals(0, bst.getSize())
    }

    @Test
    fun `delete should not remove anything if the key does not exist`() {
        bst.insert(5, "5")
        bst.delete(10)
        assertEquals("5", bst.find(5))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun `delete left child`() {
        bst.insert(5, "5")
        bst.insert(3, "3")
        bst.delete(5)
        assertEquals(null, bst.find(5))
        assertEquals("3", bst.find(3))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun `delete right child`() {
        bst.insert(5, "5")
        bst.insert(7, "7")
        bst.delete(5)
        assertEquals(null, bst.find(5))
        assertEquals("7",bst.find(7))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun `delete two children`() {
        bst.insert(5, "5")
        bst.insert(3, "3")
        bst.insert(7, "7")
        bst.delete(5)
        assertEquals(null, bst.find(5))
        assertEquals("3", bst.find(3))
        assertEquals("7", bst.find(7))
        assertEquals(2, bst.getSize())
    }

    @Test
    fun `find should return 5 if a node with the given key exists`() {
        bst.insert(5, "5")
        assertEquals("5", bst.find(5))
    }

    @Test
    fun `min should return the minimum value in the tree`() {
        bst.insert(5, "5")
        bst.insert(3, "3")
        bst.insert(7, "7")
        assertEquals("3", bst.min())
    }

    @Test
    fun `min should return null if the tree is empty`() {
        assertNull(bst.min())
    }

    @Test
    fun `max should return the maximum value in the tree`() {
        bst.insert(5, "5")
        bst.insert(3, "3")
        bst.insert(7, "7")
        assertEquals("7", bst.max())
    }

    @Test
    fun `max should return null if the tree is empty`() {
        assertNull(bst.max())
    }

    @Test
    fun `size should return the number of nodes in the tree`() {
        assertEquals(0, bst.getSize())
        bst.insert(5, "5")
        assertEquals(1, bst.getSize())
        bst.insert(-5, "-5")
        assertEquals(2, bst.getSize())
        bst.insert(10, "10")
        assertEquals(3, bst.getSize())
        bst.delete(5)
        assertEquals(2, bst.getSize())
    }

    @Test
    fun `size should return 0 for an empty tree`() {
        assertEquals(0, bst.getSize())
    }
    @Test
    fun hz(){
        var mas = listOf(5, 2, 7, 3, -5, 6, 8)
        for (i in 0..<mas.size){
            bst.insert(mas[i], "${mas[i]}")
        }
        assertEquals(7, bst.getSize())
        bst.printTree()
        for (i in 0..<mas.size){
            println("${mas[i]} ${bst.find(mas[i])}")
            bst.delete(mas[i])
        }
        assertEquals(0, bst.getSize())
    }
}