package aoc.observer;

import java.util.concurrent.Future;

/**
 * Interface de l'ObserverAsync
 */
public interface ObserverAsync<T> {

	public Future<Void> update(T subject);
}