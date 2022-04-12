package se.kimler.demo.bootstrap.util.ssn;

import static se.kimler.demo.bootstrap.util.random.RandomUtil.getRandomInteger;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class SwedishSocialSecurityNumberGenerator implements SocialSecurityNumberGenerator {

    @Override
    public String generate() {
        int year = generateYear();
        int month = generateMonth();
        int day = generateDay(year, month);
        int securityNumber = generateSecurityNumber();
        return String.format("%s%s%s-%s", year, getStringValueOfDateUnit(month), getStringValueOfDateUnit(day),
                securityNumber);
    }

    private static int generateYear() {
        int currentYear = ZonedDateTime.now(ZoneId.from(ZoneOffset.UTC)).getYear();
        return getRandomInteger(1900, currentYear);
    }

    private static int generateMonth() {
        return getRandomInteger(1, 12);
    }

    private static int generateDay(int year, int month) {
        if (month == 2) {
            if (isLeapYear(year)) {
                return getRandomInteger(1, 29);
            }
            return getRandomInteger(1, 28);
        }
        if (month % 2 == 0) {
            return getRandomInteger(1, 30);
        }
        return getRandomInteger(1, 31);
    }

    private static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        }
        return year % 4 == 0;
    }

    private static int generateSecurityNumber() {
        return getRandomInteger(1000, 9999);
    }

    private static String getStringValueOfDateUnit(int dateUnit) {
        return dateUnit < 10 ? String.format("0%s", dateUnit) : Integer.toString(dateUnit);
    }
}
