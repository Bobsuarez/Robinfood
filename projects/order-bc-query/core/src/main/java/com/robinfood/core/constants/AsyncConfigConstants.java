package com.robinfood.core.constants;

public final class AsyncConfigConstants {

    public static final class SalesReport {
        public static final int CORE_POOL_SIZE = 2;
        public static final int MAX_POOL_SIZE = 2;
        public static final int QUEUE_CAPACITY = 50;
        public static final String THREAD_NAME = "getSalesBySegment";
        public static final String THREAD_NAME_PREFIX = "AsyncThread-";

        private SalesReport() {
            throw new IllegalStateException("Utility class");
        }
    }

    private AsyncConfigConstants() {
        throw new IllegalStateException("Utility class");
    }
}
