import os
import sys

if len(sys.argv) != 5:
    print("Usage: python run_my_project.py <int/float> <add/sub/mul/div> <operand1> <operand2>")
    sys.exit(1)

# Compile Java files with package
print("Compiling Java files...")
compile_status = os.system("javac  MyInfArith.java arbitraryarithmetic/*.java ")

if compile_status != 0:
    print("Compilation failed.")
    sys.exit(1)

# Run Java main class using fully qualified name
args = " ".join(sys.argv[1:])
run_status = os.system(f"java MyInfArith {args}")

if run_status != 0:
    print("Execution failed.")
