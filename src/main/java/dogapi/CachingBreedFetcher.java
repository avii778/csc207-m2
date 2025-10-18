package dogapi;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    // TODO Task 2: Complete this class
    private int callsMade = 0;
    private HashMap<String, List<String>> cachedBreeds = new HashMap<>();
    private BreedFetcher fetcher = null;

    public CachingBreedFetcher(BreedFetcher fetcher) {
        this.fetcher = fetcher;
    }

    @Override
    public List<String> getSubBreeds(String breed) throws BreedNotFoundException {
        // return statement included so that the starter code can compile and run.

        if (this.cachedBreeds.containsKey(breed)) {
            return this.cachedBreeds.get(breed);
        }

        try {
            callsMade++;
            List<String> subBreeds = fetcher.getSubBreeds(breed);
            this.cachedBreeds.put(breed, subBreeds);
            return subBreeds;
        } catch (BreedNotFoundException e) {
            throw e;
        }

    }


    public int getCallsMade() {
        return callsMade;
    }
}