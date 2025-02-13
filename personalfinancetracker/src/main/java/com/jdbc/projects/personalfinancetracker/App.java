package com.jdbc.projects.personalfinancetracker;

import java.io.IOException;

import ui.Menu;

public class App 
{
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
    	new Menu().displayMenu();
    }
}
