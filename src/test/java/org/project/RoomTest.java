package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    String[] sampleData = {"Ala Aut�noma (ISCTE-IUL)", "Audit�rio Afonso de Barros", "80", "39", "4", "X", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    Room room = new Room(sampleData);
    @Test
    void getBuilding() {
        assertEquals(sampleData[0], room.getBuilding());
    }

    @Test
    void getDesignation() {
        assertEquals(sampleData[1], room.getDesignation());
    }

    @Test
    void setDesignation() {
        room.setDesignation("Ala não autonoma");
        assertEquals("Ala não autonoma", room.getDesignation());
    }

    @Test
    void getNormalCapacity() {
        assertEquals(Integer.parseInt(sampleData[2]), room.getNormalCapacity());
    }

    @Test
    void setNormalCapacity() {
        room.setNormalCapacity(70);
        assertEquals(70, room.getNormalCapacity());
    }

    @Test
    void getExamCapacity() {
        assertEquals(Integer.parseInt(sampleData[3]), room.getExamCapacity());
    }

    @Test
    void setExamCapacity() {
        room.setExamCapacity(34);
        assertEquals(34, room.getExamCapacity());
    }

    @Test
    void getNumOfCharacteristics() {
        assertEquals(Integer.parseInt(sampleData[4]), room.getNumOfCharacteristics());
    }

    @Test
    void setNumOfCharacteristics() {
        room.setNumOfCharacteristics(3);
        assertEquals(3, room.getNumOfCharacteristics());
    }

    @Test
    void isAmphitheatre() {
        assertTrue(room.isAmphitheatre());
    }

    @Test
    void setAmphitheatre() {
        room.setAmphitheatre(false);
        assertFalse(room.isAmphitheatre());
    }

    @Test
    void isTechHelp() {
        assertFalse(room.isTechHelp());
    }

    @Test
    void setTechHelp() {
        room.setTechHelp(true);
        assertTrue(room.isTechHelp());

    }

    @Test
    void isArq1() {
        assertFalse(room.isArq1());
    }

    @Test
    void setArq1() {
        room.setArq1(true);
        assertTrue(room.isArq1());
    }

    @Test
    void isArq2() {
        assertFalse(room.isArq2());
    }

    @Test
    void setArq2() {
        room.setArq2(true);
        assertTrue(room.isArq2());
    }

    @Test
    void isArq3() {
        assertFalse(room.isArq3());
    }

    @Test
    void setArq3() {
        room.setArq3(true);
        assertTrue(room.isArq3());
    }

    @Test
    void isArq4() {
        assertFalse(room.isArq4());
    }

    @Test
    void setArq4() {
        room.setArq4(true);
        assertTrue(room.isArq4());
    }

    @Test
    void isArq5() {
        assertFalse(room.isArq5());
    }

    @Test
    void setArq5() {
        room.setArq5(true);
        assertTrue(room.isArq5());
    }

    @Test
    void isArq6() {
        assertFalse(room.isArq6());
    }

    @Test
    void setArq6() {
        room.setArq6(true);
        assertTrue(room.isArq6());
    }

    @Test
    void isArq9() {
        assertFalse(room.isArq9());
    }

    @Test
    void setArq9() {
        room.setArq9(true);
        assertTrue(room.isArq9());
    }

    @Test
    void isBYOD() {
        assertFalse(room.isBYOD());
    }

    @Test
    void setBYOD() {
        room.setBYOD(true);
        assertTrue(room.isBYOD());
    }

    @Test
    void isFocusGroup() {
        assertFalse(room.isFocusGroup());
    }

    @Test
    void setFocusGroup() {
        room.setFocusGroup(true);
        assertTrue(room.isFocusGroup());
    }

    @Test
    void isSchedule() {
        assertFalse(room.isSchedule());
    }

    @Test
    void setSchedule() {
        room.setSchedule(true);
        assertTrue(room.isSchedule());
    }

    @Test
    void isLabACI() {
        assertFalse(room.isLabACI());
    }

    @Test
    void setLabACI() {
        room.setLabACI(true);
        assertTrue(room.isLabACI());
    }

    @Test
    void isLabACII() {
        assertFalse(room.isLabACII());
    }

    @Test
    void setLabACII() {
        room.setLabACII(true);
        assertTrue(room.isLabACII());
    }

    @Test
    void isLabBE() {
        assertFalse(room.isLabBE());
    }

    @Test
    void setLabBE() {
        room.setLabBE(true);
        assertTrue(room.isLabBE());
    }

    @Test
    void isLabEle() {
        assertFalse(room.isLabEle());
    }

    @Test
    void setLabEle() {
        room.setLabEle(true);
        assertTrue(room.isLabEle());
    }

    @Test
    void isLabInf() {
        assertFalse(room.isLabInf());
    }

    @Test
    void setLabInf() {
        room.setLabInf(true);
        assertTrue(room.isLabInf());
    }

    @Test
    void isLabJ() {
        assertFalse(room.isLabJ());
    }

    @Test
    void setLabJ() {
        room.setLabJ(true);
        assertTrue(room.isLabJ());
    }

    @Test
    void isLabRCI() {
        assertFalse(room.isLabRCI());
    }

    @Test
    void setLabRCI() {
        room.setLabRCI(true);
        assertTrue(room.isLabRCI());
    }

    @Test
    void isLabRCII() {
        assertFalse(room.isLabRCII());
    }

    @Test
    void setLabRCII() {
        room.setLabRCII(true);
        assertTrue(room.isLabRCII());
    }

    @Test
    void isLabTel() {
        assertFalse(room.isLabTel());
    }

    @Test
    void setLabTel() {
        room.setLabTel(true);
        assertTrue(room.isLabTel());
    }

    @Test
    void isMasterClass() {
        assertFalse(room.isMasterClass());
    }

    @Test
    void setMasterClass() {
        room.setMasterClass(true);
        assertTrue(room.isMasterClass());
    }

    @Test
    void isMasterClassPlus() {
        assertFalse(room.isMasterClassPlus());
    }

    @Test
    void setMasterClassPlus() {
        room.setMasterClassPlus(true);
        assertTrue(room.isMasterClassPlus());
    }

    @Test
    void isNEERoom() {
        assertFalse(room.isNEERoom());
    }

    @Test
    void setNEERoom() {
        room.setNEERoom(true);
        assertTrue(room.isNEERoom());
    }

    @Test
    void isTestRoom() {
        assertFalse(room.isTestRoom());
    }

    @Test
    void setTestRoom() {
        room.setTestRoom(true);
        assertTrue(room.isTestRoom());
    }

    @Test
    void isReunionRoom() {
        assertFalse(room.isReunionRoom());
    }

    @Test
    void setReunionRoom() {
        room.setReunionRoom(true);
        assertTrue(room.isReunionRoom());
    }

    @Test
    void isArchitectureRoom() {
        assertFalse(room.isArchitectureRoom());
    }

    @Test
    void setArchitectureRoom() {
        room.setArchitectureRoom(true);
        assertTrue(room.isArchitectureRoom());
    }

    @Test
    void isNormalClassRoom() {
        assertFalse(room.isNormalClassRoom());
    }

    @Test
    void setNormalClassRoom() {
        room.setNormalClassRoom(true);
        assertTrue(room.isNormalClassRoom());
    }

    @Test
    void isVideoCall() {
        assertFalse(room.isVideoCall());
    }

    @Test
    void setVideoCall() {
        room.setVideoCall(true);
        assertTrue(room.isVideoCall());
    }

    @Test
    void testFilters() {

    }

    @Test
    void testToString() {
        assertEquals("Ala Aut�noma (ISCTE-IUL);Audit�rio Afonso de Barros;"+80+";"+39+";"+4+";true;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false;false", room.toString());
    }
}