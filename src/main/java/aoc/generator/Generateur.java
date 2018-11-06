package aoc.generator;

import java.util.List;


/**
 * Represents Generator Interface
 */
public interface Generateur {
	
	public Integer getValue();
	
	public void attach (ObserverAsync<Generateur> o);

	public void detach (ObserverAsync<Generateur> o);
	
	public List<ObserverAsync<Generateur>> getObservers();

}
