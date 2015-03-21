package cn.dyz.arithmetic;

public class TestTree {

	public static void main(String[] args) {
		int[] preOrder={1,2,4,7,3,5,6,8};
		int[] inOrder={4,7,2,1,5,3,8,6};
		TreeNode root = buildTree(preOrder, 0, 7, inOrder, 0, 7);
		
		preIter(root);
		
		interIter(root);
	}
	
	/**
	 * 前序遍历树
	 * @param node
	 */
	public static void preIter(TreeNode node){
		if(node!=null){
			System.out.println(node.getValue());
			
			if(node.getLeft()!=null){
				preIter(node.getLeft());
			}
			
			if(node.getRgiht()!=null){
				preIter(node.getRgiht());
			}
		}
	}
	/**
	 * 中序遍历树
	 * @param node
	 */
	public static void interIter(TreeNode node){
		if(node!=null){
			
			if(node.getLeft()!=null){
				interIter(node.getLeft());
			}
			
			System.out.println(node.getValue());
			
			if(node.getRgiht()!=null){
				interIter(node.getRgiht());
			}
			
		}
			
	}
	
	/**
	 * 根据前序遍历树和中序遍历树的结果构建树
	 * @param preOrder 前序遍历结果
	 * @param inOrder	中序遍历结果
	 */
	public static TreeNode buildTree(int[] preOrder,int preStartIndex,int preEndIndex,int[] inOrder,int inStartIndex,int inEndIndex){
		TreeNode root = new TreeNode();
		root.setValue(preOrder[preStartIndex]);
		
		int rootIndex =0;
		for(int i=inStartIndex;i<=inEndIndex;i++){
			if(inOrder[i]==preOrder[preStartIndex]){
				rootIndex=i;
				break;
			}
		}
		int leftSize = rootIndex-inStartIndex;
		int rightSize = inEndIndex-rootIndex;
		if(leftSize>0){
			root.setLeft(buildTree(preOrder, preStartIndex+1, preStartIndex+leftSize, inOrder, inStartIndex, rootIndex-1));
		}
		if(rightSize>0){
			root.setRgiht(buildTree(preOrder, preStartIndex+leftSize+1, preEndIndex, inOrder, rootIndex+1, inEndIndex));
		}
		
		return root;
	}
}

