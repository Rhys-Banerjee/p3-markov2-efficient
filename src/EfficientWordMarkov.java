import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov{
    private Map<WordGram,ArrayList<String>> myMap;

    EfficientWordMarkov(){

        this(2);
    }

    EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<WordGram,ArrayList<String>>();
    }
    @Override
    public void setTraining(String text){
        myWords = text.split("\\s+");
        String next;
        for(int we=0;we<myWords.length-myOrder;we++) {
            WordGram markov = new WordGram(myWords, we, myOrder);
            if (!myMap.containsKey(markov)) {
                myMap.put(markov, new ArrayList<String>());
            }
            if (myWords.length == myOrder + we) {
                myMap.get(markov).add(PSEUDO_EOS);
            } else {
                next = myWords[myOrder + we];
                myMap.get(markov).add(next);
            }

        }
    }
    @Override
    public ArrayList<String> getFollows(WordGram key) {
        if(! myMap.containsKey(key)){
            throw new NoSuchElementException(key+" not in map");
        }
        return myMap.get(key);
    }
}

