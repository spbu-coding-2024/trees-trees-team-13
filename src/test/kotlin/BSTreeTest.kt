package test

import org.example.library.Tree.BSTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

class BSTreeTest {

    private lateinit var bst: BSTree<Int, String>

    @BeforeEach
    fun setup() {
        bst = BSTree()
    }

    @Test
    fun find_should_return_null_if_a_node_with_the_given_key_does_not_exist() {
        assertEquals(null, bst.find(5))
    }

    @Test
    fun insert_should_add_a_node_with_the_given_key_and_value() {
        bst.insert(5, "5")
        assertEquals("5", bst.find(5))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun delete_should_remove_a_node_with_the_given_key_if_it_exists() {
        bst.insert(5, "five")
        assertEquals("five", bst.find(5))
        bst.delete(5)
        assertEquals(null, bst.find(5))
        assertEquals(0, bst.getSize())
    }

    @Test
    fun delete_should_not_remove_anything_if_the_key_does_not_exist() {
        bst.insert(5, "5")
        bst.delete(10)
        assertEquals("5", bst.find(5))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun delete_with_the_left_child() {
        bst.insert(5, "5")
        bst.insert(3, "3")
        bst.delete(5)
        assertEquals(null, bst.find(5))
        assertEquals("3", bst.find(3))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun delete_with_the_right_child() {
        bst.insert(5, "5")
        bst.insert(7, "7")
        bst.delete(5)
        assertEquals(null, bst.find(5))
        assertEquals("7", bst.find(7))
        assertEquals(1, bst.getSize())
    }

    @Test
    fun delete_with_two_children() {
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
    fun find_should_return_5_if_a_node_with_the_given_key_exists() {
        bst.insert(5, "5")
        assertEquals("5", bst.find(5))
    }

    @Test
    fun min_should_return_the_minimum_value_in_the_tree() {
        bst.insert(5, "5")
        bst.insert(3, "3")
        bst.insert(7, "7")
        assertEquals("3", bst.min())
    }

    @Test
    fun min_should_return_null_if_the_tree_is_empty() {
        assertNull(bst.min())
    }

    @Test
    fun max_should_return_the_maximum_value_in_the_tree() {
        bst.insert(5, "5")
        bst.insert(3, "3")
        bst.insert(7, "7")
        assertEquals("7", bst.max())
    }

    @Test
    fun max_should_return_null_if_the_tree_is_empty() {
        assertNull(bst.max())
    }

    @Test
    fun size_should_return_the_number_of_nodes_in_the_tree() {
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
    fun size_should_return_0_for_an_empty_tree() {
        assertEquals(0, bst.getSize())
    }

    @Test
    fun checking_for_additions_and_deletions() {
        var mas = listOf(5, 2, 7, 3, -5, 6, 8)
        for (i in 0..<mas.size) {
            bst.insert(mas[i], "${mas[i]}")
        }
        assertEquals(7, bst.getSize())
        for (i in bst) {
            bst.delete(i.first)
        }
        assertEquals(0, bst.getSize())
    }

    @Test
    fun checking_on_a_large_amount_of_data() {
        val data = IntArray(10000) { Random.nextInt(-10000, 10000) }
        for (i in data) {
            bst.insert(i, i.toString())
        }
        assertEquals(data.toSet().size, bst.getSize())
        for (el in bst) {
            bst.delete(el.first)
        }
        assertEquals(0, bst.getSize())
    }
}