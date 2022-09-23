import java.util.*;

public class ScreenLockPatternFinder {
    private LinkedList<String> m_adjNearList;

    private static HashMap<String, ArrayList<String>> m_graph = new HashMap<String, ArrayList<String>>(){
        {
            put("A", new ArrayList<String>(Arrays.asList("B","D","E","F", "H")));
            put("B", new ArrayList<String>(Arrays.asList("A","C","D","E","F","G","I")));
            put("C", new ArrayList<String>(Arrays.asList("B","D","E","F","H")));
            put("D", new ArrayList<String>(Arrays.asList("A","B","C","E","G","H","I")));
            put("E", new ArrayList<String>(Arrays.asList("A","B","C","D","F","G","H","I")));
            put("F", new ArrayList<String>(Arrays.asList("A","B","C","E","G","H","I")));
            put("G", new ArrayList<String>(Arrays.asList("B","D","E","F","H")));
            put("H", new ArrayList<String>(Arrays.asList("A","C","D","E","F","G","I")));
            put("I", new ArrayList<String>(Arrays.asList("B","D","E","F","H")));
        }
    };

    private static HashMap<String, HashMap<String, String>> m_crossReplacement = new HashMap<String, HashMap<String, String>>(){
        {
            put("A", new HashMap<String, String>(){
                {
                    put("B", "C");
                    put("E", "I");
                    put("D", "G");
                }
            });
            put("B", new HashMap<String, String>(){
                {
                    put("E", "H");
                }
            });
            put("C", new HashMap<String, String>(){
                {
                    put("B", "A");
                    put("E", "G");
                    put("F", "I");
                }
            });
            put("D", new HashMap<String, String>(){
                {
                    put("E", "F");
                }
            });
            put("F", new HashMap<String, String>(){
                {
                    put("E", "D");
                }
            });
            put("G", new HashMap<String, String>(){
                {
                    put("D", "A");
                    put("E", "C");
                    put("H", "I");
                }
            });
            put("H", new HashMap<String, String>(){
                {
                    put("E", "B");
                }
            });
            put("I", new HashMap<String, String>(){
                {
                    put("F", "C");
                    put("E", "A");
                    put("H", "G");
                }
            });
            put("E", new HashMap<String, String>());
        }
    };

    private int m_patternCount;

    public int calculateCombinations(char entry, int length){
        if (length > 9 || length < 1){
            return 0;
        }

        m_patternCount = 0;

        String firstPoint = String.valueOf(entry);

        ArrayList<String> visited = new ArrayList<>();
        visited.add(firstPoint);

        dfs(firstPoint, visited, --length, "");

        return m_patternCount;
    }

    private int dfs(String v, ArrayList<String> visited, int counter, String vPrev)
    {
        if (counter == 0){
            m_patternCount++;
            return ++counter;
        }

        ArrayList<String> adjList = (ArrayList<String>)m_graph.get(v).clone();

        for (String s :
                visited) {
            if (adjList.contains(s)){
                adjList.remove(s);
            }
            String replacement = m_crossReplacement.get(v).get(s);
            if (replacement != null && !visited.contains(replacement)){
                adjList.add(replacement);
            }
        }

        for (String a :adjList) {
            visited.add(a);
            counter--;

            counter = dfs(a, visited, counter, v);
            visited.remove(a);
        }

        return ++counter;
    }
}
