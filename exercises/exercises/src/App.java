import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        App instance = new App();

        String[] paramOne = {"ana", "banana", "castle", "ant", "cat", "dog", "arr"};
        String[] resultOne = instance.exerciseOne(paramOne);
        for(String word : resultOne) System.out.println(word);

        String resultTwo = instance.exerciseTwo("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et");
        System.out.println(resultTwo);

        instance.exerciseThree(3, 4);
    }

    public String[] exerciseOne(String[] strings){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < strings.length; i++){
            if(strings[i].charAt(0) == 'a' &&
            strings[i].length() == 3)
                result.add(strings[i]);
        }

        String[] resultArray = new String[result.size()];
        resultArray = result.toArray(resultArray);
        return resultArray;
    }

    public String exerciseTwo(String string){
        String result = "";
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == ' ') continue;
            result += string.charAt(i);
        }

        return result;
    }

    public void exerciseThree(int x, int y){
        x += y;
        y = x - y;
        x -= y;

        System.out.println("Number 1: " + x);
        System.out.println("Number 2: " + y);

    }
}
