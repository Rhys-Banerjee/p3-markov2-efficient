import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	EfficientMarkov(){

		this(3);
	}

	EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	@Override
	public void setTraining(String text){
		super.setTraining(text);
		myMap.clear();
		for(int i=0;i<myText.length()-myOrder;i++){
			String markov = myText.substring(i, i+myOrder);
			if(!myMap.containsKey(markov)){
				myMap.put(markov,new ArrayList<String>());
			}
			if(i+1<=myText.length()){
				String nextRaw = myText.substring(i, i+myOrder+1);
				String next = nextRaw.substring(nextRaw.length()-1);
				myMap.get(markov).add(next);
			}else{
				myMap.get(markov).add("PSUEDO_EOS");
			}
		}

	}
	@Override
	public ArrayList<String> getFollows(String key) {
		if(! myMap.containsKey(key)){
			throw new NoSuchElementException(key+" not in map");
		}
		return myMap.get(key);
	}
}	
