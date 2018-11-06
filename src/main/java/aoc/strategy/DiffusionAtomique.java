package aoc.strategy;

import java.util.concurrent.ExecutionException;

import aoc.generator.Generateur;
import aoc.observer.ObserverAsync;

public class DiffusionAtomique implements AlgoDiffusion{
	private Generateur generateur;

	public DiffusionAtomique(Generateur generateur) {
		this.generateur = generateur;
	}

	/**
	 * Exécution de la stratégie
	 */
	public void execute() {
		// Tant que TOUS les OBSERVEURS n'ont pas eu la valeur, on bloque
		for (ObserverAsync<Generateur> o : generateur.getObservers()) {
			try {
				o.update(generateur).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Configuration de la stratégie de diffusion
	 * @param generateur le générateur sur lequel appliquer ladite stratégie
	 */
	@Override
	public void configure(Generateur generateur) {
		this.generateur = generateur;
	}
}
