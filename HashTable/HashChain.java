import java.util.Random;

class HashEntry {
    int value;
    HashEntry next;

    HashEntry(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HashEntry getNext() {
        return next;
    }

    public void setNext(HashEntry next) {
        this.next = next;
    }
}

public class HashChain {

    HashEntry[] table;

    HashChain(int size) {
        table = new HashEntry[size];
        for(int i = 0; i < size; i++) {
            table[i] = null;
        }
    }

    public void populate(int values[], int collisions[], int size) {

        for (int i = 0; i < values.length; i ++) {
            int key = values[i] % size;
            if(table[key] == null)
            	table[key] = new HashEntry(values[i]);
            else {
            	HashEntry entry = table[key];
            	
            	while(entry.getNext() != null) {
            		entry = entry.getNext();
            	}
            	entry.setNext(new HashEntry(values[i]));
            	collisions[key]++;
            }
        }  
    }


    public void print() {
        System.out.println("--------------------");
    	System.out.println("Key\tValue");
        for (int n = 0; n < table.length; n++) {
            if(table[n] == null)
            	System.out.print(n + "\tnull");
            else {
            	System.out.print(n + "\t" + table[n].getValue());
            	HashEntry entry = table[n].getNext();
            	while (entry != null) {
            		System.out.print(" -> " + entry.getValue());
            		entry = entry.getNext();
            	}
            }
        System.out.print("\n");
        }
        System.out.println("--------------------");
    }
    
    public static void fillWithZero(int arr[]) {
    	for( int i =0; i < arr.length; i++) {
    		arr[i] = 0;
    	}
    }

    public static void main(String[] args) {
        
        Random random = new Random();
        
        int size = random.nextInt(76) + 25;
        int range = 20000;

//        int size = 10;
//        int range = 25;

        HashChain hashTable = new HashChain(size);
        
        int keyValues[] = new int[25];
        int collisions[] = new int[size];
        fillWithZero(collisions);
        System.out.println("--------------------");
        System.out.println("Size: " + size);
        System.out.print("Values: ");
        for(int i = 0; i < keyValues.length; i++) {
             keyValues[i] = random.nextInt(range);
             System.out.print(keyValues[i] + ", ");
        }
        System.out.print("\n");

        hashTable.populate(keyValues, collisions, size);
        hashTable.print();

    }
}
