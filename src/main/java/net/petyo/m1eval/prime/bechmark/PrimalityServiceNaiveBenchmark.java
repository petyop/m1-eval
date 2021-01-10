package net.petyo.m1eval.prime.bechmark;


import net.petyo.m1eval.prime.PrimalityServiceNaive;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class PrimalityServiceNaiveBenchmark {


    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Measurement(iterations = 3)
    @Fork(value = 1, jvmArgsAppend = {"-XX:+UseSuperWord"})
    @Threads(8)
    public void averageTimeBenchWithSuperWord(Blackhole blackhole) {
        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            boolean isPrime = PrimalityServiceNaive.isPrime(Long.MAX_VALUE);
            blackhole.consume(isPrime);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Measurement(iterations = 3)
    @Fork(value = 1, jvmArgsAppend = {"-XX:-UseSuperWord"})
    @Threads(8)
    public void averageTimeBenchWithOutSuperWord(Blackhole blackhole) {
        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            boolean isPrime = PrimalityServiceNaive.isPrime(Long.MAX_VALUE);
            blackhole.consume(isPrime);
        }
    }

}