package googleTranslateTest.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Randomizer
{
    public static final int REPLACE_WITH_UPPER_CASE_LETTERS = 1;
    public static final int REPLACE_WITH_LOWER_CASE_LETTERS = 2;
    public static final int REPLACE_WITH_DIGITS = 3;

    private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static Logger logger = LoggerFactory.getLogger( Randomizer.class );

    public static String getRandomNumberAsString( int length )
    {
        logger.info( "Inside getRandomNumberAsString() method" );
        String randomNumber = randomize( DIGITS,
                length );

        logger.info( "Exiting getRandomNumberAsString() method" );
        return randomNumber;
    }

    public static String getRandomLowerCaseString( int length )
    {
        logger.info( "Inside getRandomLowerCaseString() method" );
        String randomLowerCaseLetters = randomize( LOWER_CASE_LETTERS,
                length );

        logger.info( "Exiting getRandomLowerCaseString() method" );
        return randomLowerCaseLetters;
    }

    public static String getRandomUpperCaseString( int length )
    {
        logger.info( "Inside getRandomUpperCaseString() method" );
        String randomUpperCaseLetters = randomize( UPPER_CASE_LETTERS,
                length );

        logger.info( "Exiting getRandomUpperCaseString() method" );
        return randomUpperCaseLetters;
    }

    public static String randomizeValue( String value )
    {
        logger.info( "Inside randomizeValue() method" );
        String tempValue = value;
        if ( tempValue != null )
        {
            logger.info( "Replacing placeholders with random upper case letters" );
            tempValue = replaceAllOcurances( tempValue,
                    REPLACE_WITH_UPPER_CASE_LETTERS );
            logger.info( "After replacing placeholders with random upper case letters: " + tempValue );

            logger.info( "Replacing placeholders with random lower case letters" );
            tempValue = replaceAllOcurances( tempValue,
                    REPLACE_WITH_LOWER_CASE_LETTERS );
            logger.info( "After replacing placeholders with random lower case letters: " + tempValue );

            logger.info( "Replacing placeholders with random digits" );
            tempValue = replaceAllOcurances( tempValue,
                    REPLACE_WITH_DIGITS );
            logger.info( "After replacing placeholders with random digits: " + tempValue );
        }

        logger.info( "Exiting randomizeValue() method" );
        return tempValue;
    }

    ////////////////////////////////////////////// Helpers //////////////////////////////////////////////////////////
    private static String randomize( String arr,
                                     int length )
    {
        logger.info( "Inside randomize() method" );
        StringBuilder strBuilder = new StringBuilder( );
        SecureRandom rand = new SecureRandom( );

        logger.info( "Generating random unit with size " + length );
        for ( int i = 0; i < length; i++ )
        {
            int digitsIndex = rand.nextInt( arr.length( ) );
            strBuilder.append( arr.charAt( digitsIndex ) );
        }

        logger.info( "Generated random unit " + rand.toString( ) );
        logger.info( "Exiting getRandomNumberAsString() method" );

        return strBuilder.toString( );
    }

    private static String replaceAllOcurances( String stringValue,
                                               int replaceSeed )
    {
        logger.info( "Inside replaceWithDigits() method" );
        String value = stringValue;
        Pattern pattern;
        String arr;

        logger.info( "Building pattern for matching" );
        switch ( replaceSeed )
        {
            case REPLACE_WITH_UPPER_CASE_LETTERS:
                pattern = Pattern.compile( "\\[\\d*A\\]" );
                arr = UPPER_CASE_LETTERS;
                break;
            case REPLACE_WITH_LOWER_CASE_LETTERS:
                pattern = Pattern.compile( "\\[\\d*a\\]" );
                arr = LOWER_CASE_LETTERS;
                break;
            case REPLACE_WITH_DIGITS:
                pattern = Pattern.compile( "\\[\\d*\\]" );
                arr = DIGITS;
                break;
            default:
                return value;
        }

        logger.info( "Pattern for matching is build: " + pattern.toString( ) );
        logger.info( "Matching the provided value with built pattern: " + value );
        Matcher matcher = pattern.matcher( value );
        if ( matcher.find( ) )
        {
            logger.info( "Found matchings: " + matcher.groupCount( ) );
            for ( int groupIndex = 0; groupIndex <= matcher.groupCount( ); groupIndex++ )
            {
                String capture = matcher.group( groupIndex );
                int length = Integer.valueOf( capture.replaceAll( "\\D+",
                        "" ) ); // removes all non digit symbols and prepare the size of the random string
                String replacement = randomize( arr,
                        length );
                logger.info( "Replacing matched item with: " + replacement );
                capture = capture.replaceAll( "\\[",
                        "\\\\[" );
                capture = capture.replaceAll( "\\]",
                        "\\\\]" );

                value = value.replaceFirst( capture,
                        replacement );
            }
        }
        else
        {
            logger.info( "No matchings found :(" );
        }

        logger.info( "Exiting replaceAllOcurances() method" );
        return value;
    }
}