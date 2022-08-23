package org.main;

import org.thead.lesson1.ThreadCreate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ThreadCreate threadCreate = new ThreadCreate();
        try {
            threadCreate.createNewTread();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
