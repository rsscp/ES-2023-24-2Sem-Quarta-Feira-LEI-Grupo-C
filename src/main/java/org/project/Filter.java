package org.project;

public class Filter {
    private LectureAttribute attribute;
    private String filterString;
    private FilterOperation operation;

    public Filter(LectureAttribute attribute, String filterString, String opString) {
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
    public boolean op(boolean input) {
        operation.op(input)
    }
}
