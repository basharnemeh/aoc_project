package aoc.generator;

import java.util.concurrent.Future;

public interface GenerateurAsync {
	public Future<Integer> getValue();
}
