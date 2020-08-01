package entities

interface TreeNode {

    val id : Int

    val children : List<TreeNode>

    var parent: TreeNode?

    var depth : Int

}