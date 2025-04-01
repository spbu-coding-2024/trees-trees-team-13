package test

import org.example.library.Tree.AVLTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

class AVLTreeTest {
    private lateinit var avlt: AVLTree<Int, String>

    @BeforeEach
    fun setup() {
        avlt = AVLTree()
    }

    @Test
    fun find_should_return_null_if_a_node_with_the_given_key_does_not_exist() {
        assertEquals(null, avlt.find(5))
    }

    @Test
    fun insert_should_add_a_node_with_the_given_key_and_value() {
        avlt.insert(5, "5")
        assertEquals("5", avlt.find(5))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun delete_should_remove_a_node_with_the_given_key_if_it_exists() {
        avlt.insert(5, "5")
        assertEquals("5", avlt.find(5))
        avlt.delete(5)
        assertEquals(null, avlt.find(5))
        assertEquals(0, avlt.getSize())
    }

    @Test
    fun delete_should_not_remove_anything_if_the_key_does_not_exist() {
        avlt.insert(5, "5")
        avlt.delete(10)
        assertEquals("5", avlt.find(5))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun delete_with_the_left_child() {
        avlt.insert(5, "5")
        avlt.insert(3, "3")
        avlt.delete(5)
        assertEquals(null, avlt.find(5))
        assertEquals("3", avlt.find(3))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun delete_with_the_right_child() {
        avlt.insert(5, "5")
        avlt.insert(7, "7")
        avlt.delete(5)
        assertEquals(null, avlt.find(5))
        assertEquals("7", avlt.find(7))
        assertEquals(1, avlt.getSize())
    }

    @Test
    fun delete_with_two_children() {
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
    fun find_should_return_5_if_a_node_with_the_given_key_exists() {
        avlt.insert(5, "5")
        assertEquals("5", avlt.find(5))
    }

    @Test
    fun min_should_return_the_minimum_value_in_the_tree() {
        avlt.insert(5, "5")
        avlt.insert(3, "3")
        avlt.insert(7, "7")
        assertEquals("3", avlt.min())
    }

    @Test
    fun min_should_return_null_if_the_tree_is_empty() {
        assertNull(avlt.min())
    }

    @Test
    fun max_should_return_the_maximum_value_in_the_tree() {
        avlt.insert(5, "5")
        avlt.insert(3, "3")
        avlt.insert(7, "7")
        assertEquals("7", avlt.max())
    }

    @Test
    fun max_should_return_null_if_the_tree_is_empty() {
        assertNull(avlt.max())
    }

    @Test
    fun size_should_return_the_number_of_nodes_in_the_tree() {
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
    fun size_should_return_0_for_an_empty_tree() {
        assertEquals(0, avlt.getSize())
    }

    @Test
    fun checking_for_additions_and_deletions() {
        val mas = listOf(5, 2, 7, 3, -5, 6, 8)
        for (i in 0..<mas.size) {
            avlt.insert(mas[i], "${mas[i]}")
        }
        assertEquals(7, avlt.getSize())
        for (i in avlt) {
            avlt.delete(i.first)
        }
        assertEquals(0, avlt.getSize())
    }

    @Test
    fun checking_on_a_large_amount_of_data() {
        val data = IntArray(10000) { Random.nextInt(-10000, 10000) }
        for (i in data) {
            avlt.insert(i, i.toString())
        }
        assertEquals(data.toSet().size, avlt.getSize())
        for (el in avlt) {
            avlt.delete(el.first)
        }
        assertEquals(0, avlt.getSize())
    }
}