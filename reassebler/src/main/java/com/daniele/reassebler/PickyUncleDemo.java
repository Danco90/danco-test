package com.daniele.reassebler;

import java.io.IOException;

import com.daniele.reassebler.service.MarvelNiece;
import com.daniele.reassebler.service.MarvelNieceImpl;


public class PickyUncleDemo 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "What a mess your bro's made! You should've looked after him ... " );
        System.out.println( " ... Tidy it up, that's an order!" );
        
        MarvelNiece niece = new MarvelNieceImpl(args);
        
        niece.getCracking();
    }
}
