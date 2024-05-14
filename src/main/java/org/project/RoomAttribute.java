package org.project;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public enum RoomAttribute implements Attribute{
    building(0,"Building"),
    designation(1,"Designation"),
    normalCapacity(2,"Normal Capacity"),
    examCapacity(3,"Exam Capacity"),
    numOfCharacteristics(4,"Number of Characteristics"),
    amphitheatre(5,"Amphitheatre"),
    techHelp(6,"Tech Help"),
    arq1(7,"Arq1"),
    arq2(8,"Arq2"),
    arq3(9,"Arq3"),
    arq4(10,"Arq4"),
    arq5(11,"Arq5"),
    arq6(12,"Arq6"),
    arq9(13,"Arq9"),
    BYOD(14,"BYOD"),
    focusGroup(15,"Focus Group"),
    schedule(16,"Schedule"),
    labACI(17,"Lab ACI"),
    labACII(18,"Lab ACII"),
    labBE(19,"Lab BE"),
    labEle(20,"Lab Ele"),
    labInf(21,"Lab Inf"),
    labJ(22,"Lab J"),
    labRCI(23,"Lab RCI"),
    labRCII(24,"Lab RCII"),
    labTel(25,"Lab Tel"),
    masterClass(26,"Master Class"),
    masterClassPlus(27,"Master Class Plus"),
    NEERoom(28,"NEE Room"),
    testRoom(29,"Test Room"),
    reunionRoom(30,"Reunion Room"),
    architectureRoom(31,"Architecture Room"),
    normalClassRoom(32,"Normal ClassRoom"),
    videoCall(32,"Video Call");

    private int value;
    private String label;
    private RoomAttribute(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
