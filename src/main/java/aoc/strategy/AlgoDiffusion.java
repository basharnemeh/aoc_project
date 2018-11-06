package aoc.strategy;

import aoc.generator.Generateur;

/**
 * 
 * Interface de l'algorithme de diffusion
 *
 */
public interface AlgoDiffusion {
	
	public void configure (Generateur generateur);
	
	public void execute ();
}
