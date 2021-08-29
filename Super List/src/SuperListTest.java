
public class SuperListTest {
	public static void main (String[] args) {
		SuperList<Integer> list = new SuperList<Integer>();
		for (int i = 0; i < 30; i++) {
			list.add((int)(Math.random()*1000+1));
		}
		System.out.println("Super List: " + list);
		System.out.println("List size: " + list.size());
		SuperList<Integer> stackList = new SuperList<Integer>();
		while (!list.isEmpty()) {
			stackList.push(list.remove(0));
		}
		System.out.println("Stack List: " + stackList);
		SuperList<Integer> queueList = new SuperList<Integer>();
		while (!stackList.isEmpty()) {
			queueList.add(stackList.pop());
		}
		System.out.println("Queue List: " + queueList);
		while (!queueList.isEmpty()) {
			int index = (int)(Math.random()*list.size());
			list.add(index, queueList.poll());
		}
		System.out.println("Super List Reloaded: " + list);
		
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		System.out.println("Sum = " + sum);
		
		int sumEven = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)%2 == 0) {
				sumEven += list.get(i);
			}
		}
		System.out.println("Sum of Evens = " + sumEven);
		
		int sumOdd = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)%2 == 1) {
				sumOdd += list.get(i);
			}
		}
		System.out.println("Sum of Evens = " + sumOdd);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i)%2 == 0) {
				list.add(list.get(i));
			}
		}
		System.out.println("Super List with Duplicate Evens: " + list);
		
		int size2 = list.size();
		for (int i = 0; i < size2; i++) {
			if (i >= list.size()) {
				break;
			}
			if (list.get(i)%3 == 0) {
				list.remove(i);
				i--;
			}
		}
		System.out.println("Super List without Multiples of 3: " + list);
		
		list.add(3, 55555);
		System.out.println("Super List: " + list);
		
		for (int i = 1; i < list.size(); i++) {
			int key = list.get(i);
			int j = i-1;
			while (j >= 0 && list.get(j) > key) {
				list.getNode(j+1).setValue(list.get(j));
				j--;
			}
			list.getNode(j+1).setValue(key);
		}
		System.out.println("List in Ascending Order: " + list);
		
		int size3 = list.size();
		System.out.println("List Size: " + size3);
		if (list.size()%2 == 0) {
			double medianEven = (list.get(size3/2-1)+list.get(size3/2))/2;
			System.out.println("Median: " + medianEven);
			System.out.println("Value before Median: " + list.get(size3/2-1));
			System.out.println("Value after Median: " + list.get(size3/2));
		} else {
			System.out.println("Median: " + list.get(size3/2));
			System.out.println("Value before Median: " + list.get(size3/2-1));
			System.out.println("Value after Median: " + list.get(size3/2+1));
		}
		
		System.out.println("\n\n\n\n");
		
		
		SuperList<String> stringList = new SuperList<String>();
		String txt = "He found rain fascinating yet unpleasant because while it fell majestically from the sky it also made him wet and cold";
		String[] arr = txt.split(" ");
		for (int i = 0; i < arr.length; i++) {
			stringList.add(arr[i]);
		}
		System.out.println("Sentence: " + stringList);
		
		int size4 = stringList.size();
		for (int i = 0; i < size4; i++) {
			if (i >= stringList.size()) {
				break;
			}
			if (stringList.get(i).length() <= 3) {
				stringList.remove(i);
				i--;
			}
		}
		System.out.println("Sentence without Short Words: " + stringList);
		System.out.println("Alphabetically Sorted Sentence: " + sort(stringList));
		
		double sumLength = 0;
		for (int i = 0; i < stringList.size(); i++) {
			sumLength += stringList.get(i).length();
		}
		System.out.println("Average word length: " + sumLength/stringList.size() + " letters");
	}
	
	public static SuperList<String> sort (SuperList<String> list) {
		SuperList<String> sortedList = list;
		
		for (int i = 1; i < sortedList.size(); i++) {
			String key = sortedList.get(i);
			int j = i-1;
			while (j >= 0 && sortedList.get(j).compareToIgnoreCase(key) > 0) {
				sortedList.getNode(j+1).setValue(sortedList.get(j));
				j--;
			}
			sortedList.getNode(j+1).setValue(key);
		}
		
		return sortedList;
	}
}
