# DAA Assignment 1

Assignment 1 for Design and Analysis of Algorithms.

## Algorithms

- MergeSort
- QuickSort
- QuickSelect

The project also contains JUnit 5 tests, benchmark results and performance plots.

## Run Tests

```bash
mvn test
```

## Run Benchmark

Run the `Benchmark` class.

It creates:

```text
results.csv
```

The benchmark uses array sizes from 1,000 to 1,000,000 and tests random, sorted and duplicate arrays.

## Plots

Run:

```bash
python plot_results.py
```

It creates:

- time_vs_n.png
- depth_vs_n.png
- ratio_vs_n.png