import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickSortHeapSort {

    public static void main(String[] args) {
        
    	int sizeChoice; //holds user input for array size to be sorted
    	ArrayList<Integer> sizes = new ArrayList<>();//dynamic array to hold sizeChoice values
    	
    	QuickSort quickSortObj = new QuickSort();//creating a QuickSort object
    	ArrayList<Long> quickSortTimes = new ArrayList<>();//dynamic array to hold quick sort times
    	
        HeapSort heapSortObj = new HeapSort();//creating a HeapSort object
        ArrayList<Long> heapSortTimes = new ArrayList<>();//dynamic array to hold heap sort times
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("This program takes in sizes from 10 to 50,000 and sorts a random arrays of those sizes");
        System.out.println("using the HEAP-SORT and QUICK-SORT algorithms");
        System.out.println("Enter a size of -1 to end list of sizes");
        
        //This do-while loop asks the user for an array size and calls the sort methods for QuickSort and HeapSort
		//The values returned are stored in quickSortTimes and heapSortTimes respectively
		//Loop repeats until the user enters a size of -1
        do 
        {
            System.out.printf("Enter size : ");
            sizeChoice = scanner.nextInt();
            if (sizeChoice == -1) 
            {
                break;
            }
            if (sizeChoice >= 10 && sizeChoice <= 50000) 
            {
                int a[] = new int[sizeChoice];//creating the array to be sorted
                //for loop to randomize the element values of a[]
                for (int i = 0; i < sizeChoice; i++) 
                {
                    Random random = new Random();
                    a[i] = random.nextInt();
                }
                
                //storing the sizeChoice in sizes
                sizes.add(sizeChoice);
                //storing the returned execution time of QUICKSORT algorithm in quickSortTimes
                quickSortTimes.add(quickSortObj.sort(a));
                //storing the returned execution time of HEAPSORT algorithm in heapSortTimes 
                heapSortTimes.add(heapSortObj.sort(a));
                
            } 
            else 
            {
                System.out.println("Size must be between 10 and 50,000 inclusively");
            }
        } while (sizeChoice != -1);
        
        //display the results
        System.out.println("Array Size \t Quick Sort \t\t Heap Sort ");
        for (int i = 0; i < quickSortTimes.size(); i++) //number of results = quickSortTimes.size()
        {
            System.out.println(sizes.get(i) + "\t\t " + quickSortTimes.get(i) + "ns\t\t " + heapSortTimes.get(i) + "ns");
        }
        
        scanner.close();
    }
}

class QuickSort 
{
	//Public method that takes in the array from main and passes it to 
	//the private quickSort method with the proper parameters
	//Returned value will be a long representing execution time in nanoseconds
    long sort(int a[]) 
    {
        return quickSort(a, 0, a.length - 1);
    }
    
	//Private quickSort method for sorting an array a[p,...,r] with the QUICKSORT algorithm (p.171)
	//Returned value will be a long representing execution time in nanoseconds
    private long quickSort(int a[], int p, int r) 
    {
        long startTime = System.nanoTime();//start time
        
        //QUICKSORT Algorithm
        if (p < r) 
        {
            int q = partition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
        
        long endTime = System.nanoTime();//end time
        
        return endTime - startTime; //return execution time = (end time) - (start time)
    }
    
	//Private partition method to rearrange the subarray a[p,...,r] in place
	//with the PARTITION algorithm (p. 171)
	private int partition(int a[], int p, int r)
	{
		//Algorithm
		int x = a[r]; //pivot element
		int i = p - 1;
		for (int j = p; j < r; j++)
		{
			if (a[j] <= x)
			{
				i++;
				exchange(a, j, i); //exchange a[i] with a[j]
			}
		}
		exchange(a, r, i + 1); //exchange a[i + 1] with a[r]
		return i + 1;
	}
	
	//Private exchange method to exchange a[m] with a[n] for PARTITION algorithm
	private void exchange(int a[], int m, int n)
	{
		int temp = a[n];
		a[n] = a[m];
		a[m] = temp;
	}
}

class HeapSort
{
	//Public sort method that performs the HEAPSORT algorithm (p. 160) on a[] 
	//Returned value will be a long representing execution time in nanoseconds
	public long sort(int a[])
	{
		long start = System.nanoTime(); //start time
		int length = a.length;
		
		//BUILD-MAX-HEAP algorithm (p. 157)
		for (int i = length / 2 - 1; i >= 0; i--)
		{
			maxHeapify(a, i);
		}
		
		for (int i = length - 1; i >= 0; i--)
		{
			exchange(a, i, 0);
			maxHeapify(a, 0);
		}
		
		long end = System.nanoTime(); //end time
		
		return end - start; //return execution time = (end time) - (start time)
	}
	
	//Private method that performs MAX-HEAPIFY algorithm (p. 154) on a[]
	private void maxHeapify(int a[], int i)
	{
		int largest; 
		int l = 2 * i + 1; 
		int r = 2 * i + 2;
		
		if (l < a.length && a[l] > a[i])
		{
			largest = l;
		}
		else
		{
			largest = i;
		}
		if (r < a.length && a[r] > a[largest])
		{
			largest = r;
		}
		if (largest != i)
		{
			exchange(a, i, largest);
			maxHeapify(a, largest);
		}
	}
	
	//Private exchange method to exchange a[m] with a[n] for MAX-HEAPIFY algorithm
	private void exchange(int a[], int m, int n)
	{
		int temp = a[n];
		a[n] = a[m];
		a[m] = temp;
	}
}