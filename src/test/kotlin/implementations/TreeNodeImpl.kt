package implementations

import entities.TreeNode

class TreeNodeImpl(
        override val id : Int,
        override val children: MutableList<TreeNode> = mutableListOf(),
        override var parent: TreeNode? = null,
        override var depth: Int = 0
) : TreeNode