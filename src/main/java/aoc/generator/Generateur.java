package aoc.generator;

import java.util.List;

import aoc.observer.ObserverAsync;


/**
 * L'interface de generateur
 */
public interface Generateur {
	
	public Integer getValue();
	
	public void attach (ObserverAsync<Generateur> o);

	public void detach (ObserverAsync<Generateur> o);
	
	public List<ObserverAsync<Generateur>> getObservers();

}
