import java.util.Random;
import java.util.*;

class item {
	int id;
	int value;

	item(int v){
		Random rand = new Random();
		this.value = v;
		this.id = rand.nextInt(1000);
	}

	item(){};
}

class myList {
	ArrayList<item> itens = new ArrayList<item>();
	int size;

	public void myList(){
		size = 0;
	}

	public void addItem(int value){
		item n = new item(value);
		while(idExists(n.id)){
			n = new item(value);
		};
		itens.add(n);
		this.size = this.size + 1;
	}

	public boolean idExists(int id){
		for(item i: itens){
			if(i.id == id) return true;
		}
		return false;
	}

	public void sort(){
		quicksort(0, this.size - 1);
	}

	public void quicksort(int start, int end){
		int i = start;
		int j = end;
		int p = itens.get(end).value;
		item aux;
		while(i < j){
			while(i < j && itens.get(i).value <= p) i = i + 1;
			while(i < j && itens.get(j).value > p) j = j - 1;
			if(i < j){
				aux = itens.get(i);
				itens.set(i, itens.get(j));
				itens.set(j, aux);
			}
		}
		if(start < end){
			quicksort(start, i-1);
			quicksort(i, end);
		}
	}

	public void shuffle(){
		Random rand = new Random();
		int pos;
		item aux;
		for(int i = 0; i < size - 1; i++){
			pos = rand.nextInt(size - 1);
			aux = itens.get(i);
			itens.set(i, itens.get(pos));
			itens.set(pos, aux);
		}
	}

	public void mergesort(int start, int end){
		if(start < end){
			int mid = ((start + end) / 2);
			mergesort(start, mid);
			mergesort(mid+1, end);
			merge(start, mid, end);
		}
	}

	public void merge(int start, int mid, int end){
		System.out.format("begin: %d %d %d\n", start, mid, end);

		int i = start;
		int j = mid+1;
		int k = 0;
		ArrayList<item> aux = new ArrayList<item>();
		// initialize aux;
		for(int t = 0; t < size; t++){
			aux.add(new item());
		}

		while(i <= mid && j <= end){		
			if(itens.get(i).value < itens.get(j).value){
				aux.set(k, itens.get(i));
				i = i + 1;
			} else {
				aux.set(k, itens.get(j));
				j = j + 1;
			}
			k = k + 1;
		}
		while(i <= mid){
			aux.set(k, itens.get(i));
			i = i + 1;
			k = k + 1;
		}
		while(j <= end){
			aux.set(k, itens.get(j));
			j = j + 1;
			k = k + 1;
		}
		for(int ie = start, ke = 0; ie <= end; ie++, ke++){
			itens.set(ie, aux.get(ke));
		}
	}

	public void rmergesort(int start, int end){
		if(start < end){
			int mid = ((start + end) / 2);
			rmergesort(start, mid);
			rmergesort(mid+1, end);
			rmerge(start, mid, end);
		}
	}

	public void rmerge(int start, int mid, int end){
		int i = start;
		int j = mid+1;
		int k = 0;
		ArrayList<item> aux = new ArrayList<item>();
		for(int t = 0; t < size; t++){
			aux.add(new item());
		}

		while(i <= mid && j <= end){		
			if(itens.get(i).value > itens.get(j).value){
				aux.set(k, itens.get(i));
				i = i + 1;
			} else {
				aux.set(k, itens.get(j));
				j = j + 1;
			}
			k = k + 1;
		}
		while(i <= mid){
			aux.set(k, itens.get(i));
			i = i + 1;
			k = k + 1;
		}
		while(j <= end){
			aux.set(k, itens.get(j));
			j = j + 1;
			k = k + 1;
		}
		for(int ie = start, ke = 0; ie <= end; ie++, ke++){
			itens.set(ie, aux.get(ke));
		}
	}

	public item getBigger(){
		item bigger = new item();
		bigger.value = -1;
		for(int i = 0; i < size; i++){
			if(itens.get(i).value > bigger.value) bigger = itens.get(i);
		}
		return bigger;
	}

	public void bucketsort(){
		Stack<item> st;
		item it = getBigger();
		ArrayList<Stack<item>> aux = new ArrayList<Stack<item>>();
		int i;
		int nbuckets = it.value+1;
		for(i = 0; i < nbuckets; i++){
			aux.add(new Stack<item>());
		}
		for(i = 0; i < size; i++){
			it = itens.get(i);
			st = aux.get(it.value);
			st.push(it);
			aux.set(it.value, st);
		}
		int c = 0;
		for(i = 0; i < nbuckets; i++){
			st = aux.get(i);
			while(!st.isEmpty()){
				it = st.pop();
				itens.set(c, it);
				c++;
			}
		}
	}

	public int binarysearch(int start, int end, int element){
		int pos = (start + end)/2;
		item it = itens.get(pos);
		if(it.value == element) return pos+1;
		if(start >= end){
			return -1;
		}
		if(it.value > element){
			return binarysearch(start, pos-1, element);
		} else {
			return binarysearch(pos+1, end, element);
		}
	}

}

public class mainClass {
	static myList l = new myList();
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int v;

		System.out.println("Enter value (-1 to cancel): ");
		v = scan.nextInt();
		while(v > 0){
			l.addItem(v);
			System.out.println("Enter value (-1 to cancel): ");
			v = scan.nextInt();
		}
		System.out.format("Size of your list: %d\n", l.size);
		System.out.println("You list is:");
		for(item i: l.itens){
			System.out.format("id[%d] value[%d]\n", i.id, i.value);
		}
		System.out.println("Sorting... (quicksort)");
		l.sort();
		System.out.format("Size of your list: %d\n", l.size);
		System.out.println("You list is:");
		for(item i: l.itens){
			System.out.format("id[%d] value[%d]\n", i.id, i.value);
		}
		System.out.println("Shuffling...");
		l.shuffle();
		System.out.format("Size of your list: %d\n", l.size);
		System.out.println("You list is:");
		for(item i: l.itens){
			System.out.format("id[%d] value[%d]\n", i.id, i.value);
		}
		System.out.println("Sorting... (mergesort)");
		l.mergesort(0, l.size-1);
		System.out.format("Size of your list: %d\n", l.size);
		System.out.println("You list is:");
		for(item i: l.itens){
			System.out.format("id[%d] value[%d]\n", i.id, i.value);
		}
		System.out.println("Sorting... (reverse mergesort)");
		l.rmergesort(0, l.size-1);
		System.out.format("Size of your list: %d\n", l.size);
		System.out.println("You list is:");
		for(item i: l.itens){
			System.out.format("id[%d] value[%d]\n", i.id, i.value);
		}
		System.out.println("Sorting... (bucketsort)");
		l.bucketsort();
		System.out.format("Size of your list: %d\n", l.size);
		System.out.println("You list is:");
		for(item i: l.itens){
			System.out.format("id[%d] value[%d]\n", i.id, i.value);
		}
		System.out.println("Searching... (binary search)");
		int pos = l.binarysearch(0, l.size-1, 10);
		if(pos == -1){
			System.out.println("Could not find element on list");
		} else {
			item it = l.itens.get(pos);
			System.out.format("Element found on position: %d\n", pos);
			System.out.format("Element: id[%d] value[%d]\n", it.id, it.value);
		}
	}
}
