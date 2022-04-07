package Enumerations;

public enum Instructions {

	RIGHT('R', "Pivoter a droite"),
    LEFT('L', "Pivoter a gauche"),
    MOVE('M', "Avancer");

    private String libelleInstruction;
    private char codeInstruction;

    private Instructions(char pCodeInstruction, String libelleInstruction) {
        this.libelleInstruction = libelleInstruction;
        this.codeInstruction = pCodeInstruction;
    }

    public String getLibelleInstruction() {
        return libelleInstruction;
    }

    public char getCodeInstruction() {
        return codeInstruction;
    }
}
