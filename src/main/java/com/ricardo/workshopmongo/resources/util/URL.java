package com.ricardo.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class URL {

    public static String decodeParam(String text) {
        try {

            return URLDecoder.decode(text, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static LocalDate convertDate(String textDate, LocalDate defaultDate)  {  
             
        DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("yyyy-MM-dd");        
        try{
            return LocalDate.parse(textDate, fmt);
        } catch (DateTimeException e) {
            return defaultDate;
        }
    }

}