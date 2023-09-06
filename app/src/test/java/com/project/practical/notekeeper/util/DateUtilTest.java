package com.project.practical.notekeeper.util;

import static com.project.practical.notekeeper.util.DateUtil.GET_MONTH_ERROR;
import static com.project.practical.notekeeper.util.DateUtil.getMonthFromNumber;
import static com.project.practical.notekeeper.util.DateUtil.monthNumbers;
import static com.project.practical.notekeeper.util.DateUtil.months;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

public class DateUtilTest {
    private static final String today="08-2023";
    @Test
    public void testGetCurrentTimestamp_returnedTimestamp(){
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(today,DateUtil.getCurrentTimeStamp());
                System.out.println("timestamp generated currently!");
            }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    public void getMonthFromNumber_returnSuccess(int monthNumber, TestInfo testInfo, TestReporter testReporter) {
        assertEquals(months[monthNumber], DateUtil.getMonthFromNumber(monthNumbers[monthNumber]));
        System.out.println(months[monthNumber] + " : " + monthNumbers[monthNumber]);
    }
        @ParameterizedTest
        @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
        public void testGetMonthFromNumber_returnError(int monthNumber, TestInfo testInfo, TestReporter testReporter){
        int randomInt= new Random().nextInt(90)+13;
            assertEquals(getMonthFromNumber(String.valueOf(monthNumber * randomInt)),GET_MONTH_ERROR);
            System.out.println(GET_MONTH_ERROR+" : "+monthNumbers[monthNumber] );


    }

}
