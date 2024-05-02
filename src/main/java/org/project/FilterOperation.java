package org.project;

import java.util.function.Predicate;

public enum FilterOperation {
    NOP("-", (input, parameter) -> input),
    OR("OR", (input, parameter) -> input || parameter),
    AND("AND", (input, parameter) -> input && parameter);

    private interface OperationConsumer {
        public boolean op(boolean input, boolean parameter);
    }

    private String label;
    private OperationConsumer operation;

    private FilterOperation(String label, OperationConsumer operation) {
        this.label = label;
        this.operation = operation;
    }

    public String getLabel() {
        return label;
    }

    public static FilterOperation getFilterOperation(String opString) {
        return switch (opString) {
            case "OR" -> OR;
            case "AND" -> AND;
            default -> NOP;
        };
    }

    public static String getNextFilterLabel(String opString) {
        return switch (opString) {
            case "-" -> "OR";
            case "OR" -> "AND";
            default -> "-";
        };
    }

    public boolean op(boolean input, boolean parameter) {
        return operation.op(input, parameter);
    }
}
