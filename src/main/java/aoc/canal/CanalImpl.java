package aoc.canal;

import aoc.generator.Generateur;
import aoc.generator.GenerateurAsync;
import aoc.observer.Observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CanalImpl implements Canal {
	/**
	 * Sujet du canal
	 */
	private Generateur generator;

	/**
	 * Le scheduler
	 */
	private ExecutorService scheduler;

	private int latencyInMilliseconds = 0;

	/**
	 * Les observateurs du canal (les écrans)
	 */
	private Observer<GenerateurAsync> display;

	/**
	 * Constructeur
	 * 
	 * @param generator
	 *            générateur à observer
	 * @param scheduler
	 *            ordonnanceur pour les appels asynchrones
	 */
	public CanalImpl(Generateur generator, ExecutorService scheduler) {
		this.generator = generator;
		this.scheduler = scheduler;
	}

	/**
	 * Constructeur
	 * 
	 * @param generator
	 *            générateur à observer
	 * @param scheduler
	 *            ordonnanceur pour les appels asynchrones
	 * @param latencyInMilliseconds
	 *            délai en millisecondes
	 */
	public CanalImpl(Generateur generator, ExecutorService scheduler, int latencyInMilliseconds) {
		this.generator = generator;
		this.scheduler = scheduler;
		this.latencyInMilliseconds = latencyInMilliseconds;
	}

	/**
	 * Commande asynchrone Update à exécuter
	 * 
	 * @param subject
	 *            le générateur
	 */
	@Override
	public Future<Void> update(Generateur subject) {
		return ((ScheduledExecutorService) this.scheduler).schedule(() -> {
			this.display.update(this);
			return null;
		}, latencyInMilliseconds, TimeUnit.MILLISECONDS);
	}

	/**
	 * Commande asynchrone GetValue à exécuter
	 *
	 */
	@Override
	public Future<Integer> getValue() {
		return ((ScheduledExecutorService) this.scheduler).schedule(() -> {
			return this.generator.getValue();
		}, 0, TimeUnit.MILLISECONDS);
	}

	/**
	 * Ajoute un observateur à ce sujet
	 * 
	 * @param o<Generateur>
	 *            Observer<GenerateurAsync> o observateur à ajouter
	 */
	@Override
	public void attach(Observer<GenerateurAsync> o) {
		this.display = o;
	}

	/**
	 * Retire l'observateur de ce sujet
	 * 
	 * @param o<Generateur>
	 *          Observer<GenerateurAsync> o observateur à retirer
	 */
	@Override
	public void detach(Observer<GenerateurAsync> o) {
		this.display = null;
	}

	/**
	 * Get value of current defined latency of the display
	 * 
	 * @return value in milliseconds of the latency
	 */
	public int getLatencyInMilliseconds() {
		return latencyInMilliseconds;
	}

	/**
	 * Set value of latency for the display
	 * 
	 * @param latencyInMilliseconds
	 *            value to set
	 */
	public void setLatencyInMilliseconds(int latencyInMilliseconds) {
		this.latencyInMilliseconds = latencyInMilliseconds;
	}

}
