import java.util.*;

public class Proj_250324_Iterator {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("apple");
        items.add("orange");
        items.add("mango");
        
        Iterator<String> iter = items.iterator();
        
        while (iter.hasNext()) {
            String crntFruit = iter.next();
            
            if (crntFruit.contains("g")) {
                iter.remove();
                System.out.println(crntFruit + " removed.");    
            }
            
        }
        
        System.out.println(items); 
    }
}
