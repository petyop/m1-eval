package net.petyo.m1eval.avx;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.security.SecureRandom;

public class SquaredVectorBenchmark {

    public static final int VECTOR_SIZE = 4096;

    @State(Scope.Thread)
    public static class SetupData {
        public final int[] vector = new int[VECTOR_SIZE];

        @Setup
        public void setup() {
            SecureRandom random = new SecureRandom();
            for (int i = 0; i < vector.length; ++i) {
                vector[i] = random.nextInt((int) Math.sqrt(Integer.MAX_VALUE));
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 3)
    @Measurement(iterations = 3)
    @Fork(value = 1, jvmArgsAppend = {"-XX:+UseSuperWord"})
    @Threads(8)
    public void squareVectorWithSuperWord(Blackhole blackhole, SetupData setupData) {
        square(blackhole, setupData);
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 3)
    @Measurement(iterations = 3)
    @Fork(value = 1, jvmArgsAppend = {"-XX:-UseSuperWord"})
    @Threads(8)
    public void squareVectorWithOutSuperWord(Blackhole blackhole, SetupData setupData) {
        square(blackhole, setupData);
    }

    private void square(Blackhole blackhole, SetupData setupData) {
        for (int i = 0; i < VECTOR_SIZE; ++i) {
            setupData.vector[i] = setupData.vector[i] * setupData.vector[i];
        }
        blackhole.consume(setupData.vector);
    }

}
