import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class BinarySearchTree 
{
	Node root;
	
	static class Node
	{
		int data;
		Node left, right;
		Node(int data)
		{
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	public Node buildTree(int[] arr, Node root, int i)
	{
        // Base case for recursion
        if (i < arr.length) {
            Node temp = new Node(arr[i]);
            root = temp;
 
            // insert left child
            root.left = buildTree(arr, root.left, 2 * i + 1);
            // insert right child
            root.right = buildTree(arr, root.right, 2 * i + 2);
        }
        return root;
	}
	
	public void printLevelOrder()
	{
		int treeHeight = getHeight(root);
		for(int i = 1; i <= treeHeight; i++)
			printLevel(root, i);
	}

	public int getHeight(Node root)
	{
		if(root == null)
			return 0;
		else
		{
			int leftHeight = getHeight(root.left);
			int rightHeight = getHeight(root.right);

			if(leftHeight > rightHeight)
				return(leftHeight + 1);
			else
				return(rightHeight + 1);
		}
	}

	public void printLevel(Node root, int level)
	{
		if(root == null)
			return;
		if(level == 1)
			System.out.print(root.data + " ");
		else if(level > 1)
		{
			printLevel(root.left, level - 1);
			printLevel(root.right, level - 1);
		}
	}

	public void printInOrder(Node root)
	{
		if(root != null)
		{
			 printInOrder(root.left);
			 System.out.print(root.data + " ");
			 printInOrder(root.right);
		}
	}
	public void storeInOrder(Node root, ArrayList inOrderArr)
	{
		if(root != null)
		{
			storeInOrder(root.left, inOrderArr);
			inOrderArr.add(root.data);
			storeInOrder(root.right, inOrderArr);
		}
	}
	public static void main(String args[]) 
	{
		int treeSize = 0;
		boolean validSize = false;
		BinarySearchTree binaryTree = new BinarySearchTree();
		Scanner scanner = new Scanner(System.in);
		
		//while loop to ask the user for input and validate if it is between 5 and 30 inclusively
		//repeat loop while size input is invalid
		while(!validSize)
		{
			System.out.println("Enter a tree size between 5 and 30: ");
			treeSize = scanner.nextInt();
			
			if(treeSize <= 30 && treeSize >= 5)
			{
				validSize = true; //exit loop
			}
			else
				System.out.println("Invalid Input"); //repeat loop
		}
		
		int[] randomArr = new int[treeSize];
		ArrayList inOrderArr = new ArrayList();
		
        for (int i = 0; i < treeSize; i++) 
        {
            Random random = new Random();
            randomArr[i] = random.nextInt(30);
        }
		
        System.out.print("Random array values: ");
        for (int i = 0; i < treeSize; i++)
        {
        	System.out.print(randomArr[i] + " ");
        }
        
        binaryTree.root = binaryTree.buildTree(randomArr, binaryTree.root, 0);
        System.out.print("\n\nInorder Traversal: ");
		binaryTree.printInOrder(binaryTree.root);
		System.out.print("\n\nLevel Order Traversal: ");
		binaryTree.printLevelOrder();
		binaryTree.storeInOrder(binaryTree.root, inOrderArr);
		
		
        
        scanner.close();
	} 
}
