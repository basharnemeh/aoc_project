package aoc.generator;


import aoc.observer.SubjectAsync;


/**
 * L'interface de generateur
 */
public interface Generateur extends SubjectAsync<Generateur>{
	
	public Integer getValue();

}
