package aoc.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aoc.observer.ObserverAsync;

public class GenerateurImpl implements Generateur{
	

	/**
	 * Les observateurs du générateur Proxy pour les afficheurs
	 */
	private List<ObserverAsync<Generateur>> canaux;

	/**
	 * Valeur courante générée
	 */
	private Integer value;
	
	/**
	 * Constructor
	 */
	public GenerateurImpl() {
		this.canaux = new ArrayList<ObserverAsync<Generateur>>();
	}
	
	/**
	 * Ajoute un observateur aux observateurs de ce sujet s'il n'est pas déjà présent
	 * Ne fait rien sinon
	 * @param ObserverAsync<Generateur> o observateur à ajouter
	 */
	@Override
	public void attach(ObserverAsync<Generateur> o) {
		if (!this.canaux.contains(o)) {
			this.canaux.add(o);
		}
	}
	
	/**
	 * Retire un observateur aux observateurs de ce sujet s'il est présent
	 * Ne fait rien sinon
	 * @param ObserverAsync<Generateur> o observateur à retirer
	 */
	@Override
	public void detach(ObserverAsync<Generateur> o) {
		if (this.canaux.contains(o)) {
			this.canaux.remove(o);
		}
	}
	
	/**
	 * Retourne la valeur actuelle générée par le générateur
	 */
	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public List<ObserverAsync<Generateur>> getObservers() {
		return new ArrayList<>(this.canaux);
	}

}
