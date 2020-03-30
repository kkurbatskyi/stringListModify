import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StringList{

    //the new comparator for strings to satisfy sorting requirements
    static class StringComparator implements Comparator {
        public int compare(Object o1, Object o2){
            String str1 = (String)o1;
            String str2 = (String)o2;
            if(str1.length() < str2.length()) return 1;
            if(str1.length() > str2.length()) return -1;
            return -1 * str1.compareTo(str2);
        }
    }

    //deep copy for ArrayList
    public static ArrayList<String> copy(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>(list.stream().map(String::new).collect(Collectors.toList()));
        return newList;
    }

    public static ArrayList<String> stringListModify(ArrayList<String> original, ArrayList<String> insert, ArrayList<String> remove){
        ArrayList<String> modified = copy(original);
        //add the insert-list into the original one (but only the non-repeating ones)
        for(int i = 0; i < insert.size(); i++){
            boolean exists = false;
            for(int j = 0; j < modified.size(); j++) {
                if(insert.get(i).equals(modified.get(j))){ exists = true; break;}
            }
            if(!exists)modified.add(insert.get(i));
        }
        //removing items from modified using remove-list
        for(int i = 0; i < remove.size(); i++){
            for(int j = 0; j < modified.size(); j++) {
                if(remove.get(i).equals(modified.get(j)))modified.remove(j);
            }
        }
        //sorting the resulted list
        modified.sort(new StringComparator());
        return modified;
    }

    public static void main(String[] args) {
        ArrayList<String> original = new ArrayList<>(Arrays.asList(new String[] {"one", "two", "three"}));
        ArrayList<String> insert = new ArrayList<>(Arrays.asList(new String[] {"one", "two", "five", "six"}));
        ArrayList<String> remove = new ArrayList<>(Arrays.asList(new String[] {"two", "five"}));
        System.out.println("Original list: " + original);
        System.out.println("Insert list: " + insert);
        System.out.println("Delete list: " + remove);
        System.out.println("Result: " + stringListModify(original, insert, remove));
        System.out.print("Original list to show that the function is immutable: " + original);
    }
}
