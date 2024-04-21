package org.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {
    private LectureAttributes attribute;
    private String filterString;

    public Filter(LectureAttributes attribute, String filterString) {
        this.attribute = attribute;
        this.filterString = filterString;
    }

    public int getAttributeIndex() {
        return attribute.getValue();
    }
    public String getFilterString() {
        return filterString;
    }
}
