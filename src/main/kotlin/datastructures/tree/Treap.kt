package datastructures.tree

class Treap {

    var root : TreapNode? = null

    fun insert(node: TreapNode) {
        insert(root, node)
    }

    private fun insert(curr : TreapNode?, newNode : TreapNode){

    }

    /**
     * Splits the tree rooted at node, into left and right tree
     */
    private fun split(treap: TreapNode?, limit: Int): SplitWrapper {

        return if (treap == null) {
            SplitWrapper(null, null)
        } else {
            if (treap.value < limit) {
                val res = split(treap.rightChild, limit)
                treap.rightChild = res.left
                SplitWrapper(treap, res.right)
            } else {
                val res = split(treap.leftChild, limit)
                treap.leftChild = res.right
                SplitWrapper(res.left, treap)
            }
        }
    }


    /**
     *
     */
    private fun merge(left: TreapNode?, right: TreapNode?): TreapNode? {
        return when {
            left == null -> right
            right == null -> left
            else -> {
                if (left.priority > right.priority) {
                    left.rightChild = merge(left.rightChild, right)
                    left
                } else {
                    right.leftChild = merge(left, right.leftChild)
                    right
                }
            }
        }
    }


    private class SplitWrapper(var left: TreapNode?, var right: TreapNode?)
}