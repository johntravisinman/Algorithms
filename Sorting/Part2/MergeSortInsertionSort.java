import java.util.Random;

class MergeSortInsertionSort
{
    public static void main(String args[])
    {
    	//creating arrays of size 5,000, 10,000, and 20,000 as arr5, arr10, and arr20 respectively
    	//5,000
    	int arr5[] = new int [5000];
        for (int i = 0; i < 5000; i++)//randomizing the element values after initialization of the array
        {
            Random random = new Random();
            arr5[i] = random.nextInt();
        }
        //10,000
    	int arr10[] = new int [10000];
        for (int i = 0; i < 10000; i++)//randomizing the element values after initialization of the array 
        {
            Random random = new Random();
            arr10[i] = random.nextInt();
        }
        //20,000
    	int arr20[] = new int [20000];
        for (int i = 0; i < 20000; i++)//randomizing the element values after initialization of the array 
        {
            Random random = new Random();
            arr20[i] = random.nextInt();
        }
        
        //creating a MergeSortInsertionSort object
        MergeSortInsertionSort object = new MergeSortInsertionSort();
        
        //display results
        System.out.println("Array Size\tBasic Merge Sort\tModified Merge Sort");
        System.out.println("5,000\t\t" + object.mergeSort(arr5, 0, arr5.length - 1) + " ns\t\t" + object.modifiedMergeSort(arr5, 0, arr5.length - 1) + " ns");
        System.out.println("10,000\t\t" + object.mergeSort(arr10, 0, arr10.length - 1) + " ns\t\t" + object.modifiedMergeSort(arr10, 0, arr10.length - 1) + " ns");
        System.out.println("20,000\t\t" + object.mergeSort(arr20, 0, arr20.length - 1) + " ns\t\t" + object.modifiedMergeSort(arr20, 0, arr20.length - 1) + " ns");
    }
    
    //Public method mergeSort to perform MERGE-SORT algorithm (p. 34) on a[p,...,r]
    public long mergeSort(int a[], int p, int r)
    {
    	long startTime = System.nanoTime();//start time
    	
    	//Algorithm
    	if (p < r)
        {
            int q = (p + r) / 2;
            mergeSort(a, p, q);
            mergeSort(a , q+1, r);
            merge(a, p, q, r);
        }
    	
    	long endTime = System.nanoTime();//end time
    	return endTime - startTime; //return execution time = (end time) - (start time)
    }
    
    //Public method to perform MERGE algorithm (p. 31) to merge a[p,..,q] and a[q+1,...,r]
    public void merge(int a[], int p, int q, int r)
    {
        int n1 = q - p + 1;
        int n2 = r - q;
 
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        for (int i = 0; i < n1; i++)
        {
        	L[i] = a[p + i];
        }	
        for (int j = 0; j < n2; j++)
        {
        	R[j] = a[q + 1+ j];
        }
        
        int i = 0;
        int j = 0;
        int k = p;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                a[k] = L[i];
                i++;
            }
            else
            {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            a[k] = R[j];
            j++;
            k++;
        }
    }
    
    //Public method mergeSort to perform a modified MERGE-SORT algorithm (p. 34) on a[p,...,r]
    //modified algorithm calls insertionSort in place of merge
    public long modifiedMergeSort(int a[], int p, int r)
    {
    	long startTime = System.nanoTime();//start time
    	
    	//modified algorithm to call insertionSort instead of merge
    	if (p < r)
        {
            int q = (p + r) / 2;
            modifiedMergeSort(a, p, q);
            modifiedMergeSort(a , q + 1, r);
            insertionSort(a);
        }
    	
    	long endTime = System.nanoTime();//end time
    	return endTime - startTime; //return execution time = (end time) - (start time)
    }
    
    //Public method insertionSort that performs INSERTION-SORT algorithm (p. 18) on a[]
    public void insertionSort(int a[])
    {
        int n = a.length;
        for (int j = 1; j < n; j++)
        {
            int key = a[j];
            int i = j - 1;
            while ( i >= 0 && a[i] > key)
            {
                a[i + 1] = a[i];
                i = i - 1;
            }
            a[i + 1] = key;
        }
    }
    
    //Simple method to print array values
    public static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}