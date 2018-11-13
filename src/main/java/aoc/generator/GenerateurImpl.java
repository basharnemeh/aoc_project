package aoc.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aoc.observer.ObserverAsync;
import aoc.strategy.AlgoDiffusion;

public class GenerateurImpl implements Generateur {

	/**
	 * Les observateurs du générateur Proxy pour les afficheurs
	 */
	private List<ObserverAsync<Generateur>> canaux;

	/**
	 * Valeur courante générée
	 */
	private Integer value;

	/**
	 * Algorithme de diffusion courant
	 */
	private AlgoDiffusion diffusion;


	// Générateur de valeurs
	private Random rand;

	/**
	 * Variable de contrôle pour allumer/éteindre le générateur
	 */
	private boolean turnMeOn;

	/**
	 * Constructor
	 */
	public GenerateurImpl() {
		this.canaux = new ArrayList<ObserverAsync<Generateur>>();
		this.rand = new Random();
		this.turnMeOn = true;
	}

	/**
	 * Ajoute un observateur aux observateurs de ce sujet s'il n'est pas déjà
	 * présent Ne fait rien sinon
	 * 
	 * @param ObserverAsync<Generateur>
	 *            o observateur à ajouter
	 */
	@Override
	public void attach(ObserverAsync<Generateur> o) {
		if (!this.canaux.contains(o)) {
			this.canaux.add(o);
		}
	}

	/**
	 * Retire un observateur aux observateurs de ce sujet s'il est présent Ne fait
	 * rien sinon
	 * 
	 * @param ObserverAsync<Generateur>
	 *            o observateur à retirer
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

	/**
	 * Définit l'algorithme de diffusion comme étant celui passé en paramètres
	 * 
	 * @param AlgoDiffusion
	 *            algo algorithme de diffusion
	 */
	public void addStrategy(AlgoDiffusion algo) {
		diffusion = algo;
		diffusion.configure(this);
	}

	/**
	 * Désactive l'algorithme de diffusion
	 * 
	 * @param AlgoDiffusion
	 *            algo algorithme de diffusion
	 */
	public void removeStrategy(AlgoDiffusion algo) {
		diffusion.configure(null);
		diffusion = null;
	}

	/**
	 * Notifie les observateurs suivant la cohérence/stratégie choisie
	 */
	public void notifyObservers() {
		diffusion.execute();
	}

	/**
	 * Génère aléatoirement une valeur
	 */
	public void generate() {
		if (turnMeOn) {
			this.value = rand.nextInt(100);
			notifyObservers();
		}
	}

	/**
	 * Active le générateur
	 */
	public void start() {
		turnMeOn = true;
	}

	/**
	 * Arrête le générateur
	 */
	public void stop() {
		turnMeOn = false;
	}

}
