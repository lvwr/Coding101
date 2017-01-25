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

public class myList {
	ArrayList<item> itens = new ArrayList<item>();

	public void addItem(int value){
		item n = new item(value);
		while(idExists(n.id)){
			n = new item(value);
		};
		itens.add(n);
	}

	public boolean idExists(int id){
		for(item i: itens){
			if(i.id == id) return true;
		}
		return false;
	}
}

public class mainClass {
	myList l = new myList();
	
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
		System.out.println("You list is:");
		for(item i: l.itens){
			System.out.format("id[%d] value[%d]\n", i.id, i.value);
		}
	}
}