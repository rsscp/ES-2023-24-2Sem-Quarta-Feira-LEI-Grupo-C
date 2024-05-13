package org.project;

public class Filter {
    private Attribute attribute;
    private String filterString;
    private FilterOperation operation;

    public Filter(Attribute attribute, String filterString, String opString) {
        this.attribute = attribute;
        this.filterString = filterString;
        this.operation = FilterOperation.getFilterOperation(opString);
    }

    public int getAttributeIndex() {
        return attribute.getValue();
    }
    public String getFilterString() {
        return filterString;
    }
    public boolean op(boolean input, boolean parameter) {
        return operation.op(input, parameter);
    }
}
