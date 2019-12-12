package com.carpooling.bot.bl;

import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);
    public static boolean isValidDate(String date){
        boolean result = true;
        if(date.length()!=16){
            result = false;
            LOGGER.info("Exceed Size");
        }
        else{
            if(date.charAt(2)!= '/' || date.charAt(5)!='/' || date.charAt(10)!=' ' || date.charAt(13)!=':'){
                result = false;
                LOGGER.info("Not in Format");
            }
            else{
                if(!Character.isDigit(date.charAt(0)) || !Character.isDigit(date.charAt(1)) ||
                        !Character.isDigit(date.charAt(3)) || !Character.isDigit(date.charAt(4)) ||
                        !Character.isDigit(date.charAt(6)) || !Character.isDigit(date.charAt(7)) ||
                        !Character.isDigit(date.charAt(8)) || !Character.isDigit(date.charAt(9)) ||
                        !Character.isDigit(date.charAt(11)) || !Character.isDigit(date.charAt(12)) ||
                        !Character.isDigit(date.charAt(14)) || !Character.isDigit(date.charAt(15))  ){
                    result = false;
                    LOGGER.info("Not Numbers in the format");
                }
                else{
                    int a,b,c,d;
                    int day,month,year,hour,minute;
                    a = (int)date.charAt(0) - 48;
                    b = (int)date.charAt(1) - 48;
                    day = a*10 + b;
                    a = (int)date.charAt(3) - 48;
                    b = (int)date.charAt(4) - 48;
                    month = a*10 + b;
                    a = (int)date.charAt(6) - 48;
                    b = (int)date.charAt(7) - 48;
                    c = (int)date.charAt(8) - 48;
                    d = (int)date.charAt(9) - 48;
                    year = a*1000+b*100+c*10+d;
                    a = (int)date.charAt(11) - 48;
                    b = (int)date.charAt(12) - 48;
                    hour = a*10+b;
                    a = (int)date.charAt(14) - 48;
                    b = (int)date.charAt(15) - 48;
                    minute = a*10+b;
                    LOGGER.info("Dia "+day);
                    LOGGER.info("Mes "+month);
                    LOGGER.info("Anio "+year);
                    LOGGER.info("Hora "+hour);
                    LOGGER.info("Minuto "+minute);
                    if(hour>23 || minute>59){
                        LOGGER.info("Hour and Minute Bad");
                        return false;
                    }
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date current = new Date();
                    Date selectedDate;
                    try{
                        selectedDate = dateFormat.parse(year+"-"+month+"-"+day);
                    }catch(ParseException pe){
                        LOGGER.info("Not a Correct Date");
                        return false;
                    }
                    if(!isInCalendar(day,month,year)){
                        LOGGER.info("The date not correspond to a valid date");
                       return  false;
                    }
                    int diffDays = (int) ((selectedDate.getTime()-current.getTime())/86400000);
                    if(diffDays<0){
                        LOGGER.info("Not Future Time");
                        result = false;
                    }
                    if(diffDays==0){
                        LocalDateTime localDateTime = LocalDateTime.now();
                        int localHour = localDateTime.getHour();
                        int localMinute = localDateTime.getMinute();
                        int time1 = localHour*60+localMinute;
                        int time2 = hour*60+minute;
                        if(time1>=time2){
                            LOGGER.info("Not Future Time");
                            result = false;
                        }
                    }
                }
            }
        }
        return result;
    }
    private static boolean isInCalendar(int d,int m,int y){
        boolean result = true;
        if(m>12) result = false;
        if(isLeapYear(y) && m==2){
            LOGGER.info("Bisiesto");
            if(d>29) result = false;
        }else {
            if (m == 2) {
                if (d > 28) result = false;
            }
        }
        if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12){
            if(d>31) result = false;
        }
        if(m==4 || m==6 || m==9 || m==11){
            if(d>30) result = false;
        }

        return  result;
    }
    private static boolean isLeapYear(int y){
        if ((y % 4 == 0) && ((y % 100 != 0) || (y % 400 == 0))){
            LOGGER.info(y+" es bisiseto");
            return  true;
        }
        LOGGER.info(y+" no es bisiseto");
        return false;
    }
}
