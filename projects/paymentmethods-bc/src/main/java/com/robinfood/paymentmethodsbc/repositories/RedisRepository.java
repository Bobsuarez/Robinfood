package com.robinfood.paymentmethodsbc.repositories;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

public interface RedisRepository {

    /**
     * Assigns a value according to key
     * @param key {@linkplain String} key of value to be obtained
     * @param value {@linkplain String} value to save
     */
    void set(String key, String value);

    /**
     * Assigns a value according to key and timeout
     * @param key {@linkplain String} key of value to be obtained
     * @param value {@linkplain String} value to save
     * @param timeout {@linkplain Duration}
     */
    void set(String key, String value, Duration timeout);

    /**
     * Get a value according to key
     * @param key {@linkplain String} key of value to be obtained
     * @return String
     */
    Optional<String> get(String key);

    /**
     * Gets the keys stored in cache according to the key (or pattern) sent.
     * @param pattern {@linkplain String} pattern
     * @return {@linkplain Set<String>} list of cached keys that comply with pattern
     */
    Set<String> getKey(String pattern);

    /**
     * Service that allows to delete the cache according to key
     * @param key {@linkplain String} key to be deleted
     * @return success
     */
    boolean deleteKey(String key);
}
