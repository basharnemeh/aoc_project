package aoc.canal;

import aoc.generator.Generateur;
import aoc.generator.GenerateurAsync;
import aoc.observer.ObserverAsync;
import aoc.observer.Subject;

/**
 * Interface Canal
 *
 */
public interface Canal extends ObserverAsync<Generateur>, GenerateurAsync, Subject<GenerateurAsync> {

}
