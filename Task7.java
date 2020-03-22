package sundayevening;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Task7 {
	public static void main(String[] args) {
		//7.4
		Comparator<Integer> LastByteComp =
			(Integer o1, Integer o2) -> o1.byteValue() - o2.byteValue();
			
		//7.5
		List<Integer> example = new ArrayList<>();
		example.add(1); example.add(3); example.add(6); example.add(90);
		List<Integer> input = new ArrayList<>();
		input.add(89); input.add(90); input.add(91); input.add(92);
		example.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		input.stream()
				.filter(x -> x.compareTo(example.get(example.size() - 1)) > 0)
				.forEach(System.out::println);
		
		//7.6
		Set<String> values = new HashSet<>();
		values.add("Queue"); values.add("Egor"); values.add("StreamAPI"); values.add("Hmwrk");
		values.stream()
			.filter((x) -> {
				x = x.toLowerCase();
				int count = 0;
				for (int i = 0; i < x.length(); i++) {
					char ch = x.charAt(i);
					if (ch == 'e' || ch == 'y' || ch == 'u' || ch == 'i' || ch == 'o' || ch == 'a') count++;
				}
				if (count > 3) return true;
				return false;
			})
			.forEach(System.out::println);
		
		//7.7
		Map<String, String> myMap = new HashMap<>();
		myMap.put("1", "I"); myMap.put("13", "Hate"); myMap.put("42", "Programming");
		String concat = new String();
		concat = myMap.keySet()
			.stream()
			.collect(Collectors.joining(""));
		System.out.println(concat);
		
		//7.8
		Collection<String> col = new ArrayList<String>();
		col.add("Itis"); col.add("Sosis"); col.add("timetosleep"); col.add("andtimetodie");
		Optional<String> sum = col.stream()
			.filter((x) -> x.length() > 5)
			.reduce((x, y) -> x + y);
		System.out.println(sum.get().length());
	}	
}
