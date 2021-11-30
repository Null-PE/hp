package com.hr.hr_common;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IdWorkerTest {
    IdWorker it = new IdWorker();

    @Test
    void test() {
        Set<Long> result = new HashSet<>();
        final int SIZE = 1000;
        for (int i = 0; i < SIZE; i++) {
            result.add(it.nextId());
        }
        assertThat(result.size(), is(SIZE));
    }
}
