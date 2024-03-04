import java.util.HashMap;

public class assembly {

    // Hashmap to map assembly mnemonics to their corresponding opcodes
    private static final HashMap<String, String> opcodeMap = new HashMap<>();
    static {
        opcodeMap.put("mov", "e1a");
        opcodeMap.put("add", "e28");
        opcodeMap.put("sub", "e04");
        opcodeMap.put("mul", "e00");
        opcodeMap.put("lsl", "e1a");
        opcodeMap.put("asr", "e1a");
        opcodeMap.put("ldr", "e59");
        opcodeMap.put("str", "e78");
    }

    // Assembler function to convert assembly instructions to machine code
    public static String assemble(String assemblyInstruction) {
        // Split the assembly instruction into parts
        String[] parts = assemblyInstruction.split("\\s+");
        // Get the opcode for the instruction
        String opcode = opcodeMap.get(parts[1]);
        // Extract register numbers or immediate values
        String registers = parts[2].substring(1); // Remove the 'r' prefix
        // Create the machine code
        String machineCode = opcode + registers;
        return machineCode;
    }

    // Disassembler function to convert machine code to assembly instructions
    public static String disassemble(String machineCode) {
        // Get the opcode from the machine code
        String opcode = machineCode.substring(0, 4);
        // Reverse the opcode mapping to get the mnemonic
        String mnemonic = getKeyFromValue(opcodeMap, opcode);
        // Get the register numbers or immediate values
        String registers = machineCode.substring(4);
        // Construct the assembly instruction
        String assemblyInstruction = mnemonic + " " + "r" + registers;
        return assemblyInstruction;
    }

    // Helper function to get key from value in HashMap
    public static <K, V> K getKeyFromValue(HashMap<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Example assembly instructions
        String[] assemblyInstructions = {
            "mov r1, r2",
            "mov r3, #7",
            "add r7, r3, #5",
            "sub r8, r6, r3",
            "mul r3, r4, r5",
            "lsl r1, r2, r3",
            "asr r2, r3, r4",
            "ldr r1, [r2]",
            "ldr r2, [r0, #4]",
            "str r1, [r2, r3]"
        };

        // Assemble and disassemble each instruction
        System.out.println("Assembly Instructions:");
        for (String instruction : assemblyInstructions) {
            System.out.println(instruction);
            String machineCode = assemble(instruction);
            System.out.println("Machine Code: " + machineCode);
            String disassembledInstruction = disassemble(machineCode);
            System.out.println("Disassembled Instruction: " + disassembledInstruction);
            System.out.println();
        }
    }
}
