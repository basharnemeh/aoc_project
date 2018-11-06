package aoc.strategy;

import aoc.generator.Generateur;

public interface AlgoDiffusion {
	
	public void configure (Generateur generateur);
	
	public void execute ();
}
