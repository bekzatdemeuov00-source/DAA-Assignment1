import csv
import math
import matplotlib.pyplot as plt
data = []
with open("results.csv", "r") as file:
    reader = csv.DictReader(file)
    for row in reader:
        row["n"] = int(row["n"])
        row["time_ms"] = float(row["time_ms"])
        row["comparisons"] = int(row["comparisons"])
        row["max_depth"] = int(row["max_depth"])
        data.append(row)
algorithms = ["MergeSort", "QuickSort", "QuickSelect"]
input_types = ["random", "sorted", "duplicates"]

plt.figure(figsize=(10, 6))
for algorithm in algorithms:
    for input_type in input_types:
        rows = [
            row for row in data
            if row["algorithm"] == algorithm
            and row["input"] == input_type
        ]
        rows.sort(key=lambda x: x["n"])
        x = [row["n"] for row in rows]
        y = [row["time_ms"] for row in rows]
        plt.plot(
            x,
            y,
            marker="o",
            label=algorithm + " - " + input_type
        )
plt.xscale("log")
plt.xlabel("Input size (n)")
plt.ylabel("Time (ms)")
plt.title("Execution Time vs Input Size")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.savefig("time_vs_n.png")
plt.close()
plt.figure(figsize=(10, 6))
for algorithm in algorithms:
    for input_type in input_types:
        rows = [
            row for row in data
            if row["algorithm"] == algorithm
            and row["input"] == input_type
        ]
        rows.sort(key=lambda x: x["n"])
        x = [row["n"] for row in rows]
        y = [row["max_depth"] for row in rows]
        plt.plot(
            x,
            y,
            marker="o",
            label=algorithm + " - " + input_type
        )

plt.xscale("log")
plt.xlabel("Input size (n)")
plt.ylabel("Maximum depth")
plt.title("Maximum Depth vs Input Size")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.savefig("depth_vs_n.png")
plt.close()

plt.figure(figsize=(10, 6))
for algorithm in algorithms:
    for input_type in input_types:
        rows = [
            row for row in data
            if row["algorithm"] == algorithm
            and row["input"] == input_type
        ]
        rows.sort(key=lambda x: x["n"])
        x = []
        y = []
        for row in rows:
            n = row["n"]
            comparisons = row["comparisons"]

            if algorithm == "QuickSelect":
                ratio = comparisons / n
            else:
                ratio = comparisons / (n * math.log2(n))

            x.append(n)
            y.append(ratio)
        plt.plot(
            x,
            y,
            marker="o",
            label=algorithm + " - " + input_type
        )
plt.xscale("log")
plt.xlabel("Input size (n)")
plt.ylabel("Comparison ratio")
plt.title("Experimental Complexity Ratio")
plt.grid(True)
plt.legend()
plt.tight_layout()

plt.savefig("ratio_vs_n.png")
plt.close()

print("Graphs created successfully!")