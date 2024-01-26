package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tester for the Word class.
 * 
 * @author Prof. Parker and Shawn
 * @version Oct 20, 2023
 */
public class WordTester {

	/**
	 * Test that the Word class constructor properly throws an exception when 
	 * the given string contains characters that are not a-z or A-Z.
	 */
	@Test
	public void testConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> { new Word("hel!o"); });
	}
	
	/**
	 * Test that the Word class constructor properly sets the private instance 
	 * variable, which is used to generate and return a String in the toString method.
	 */
	@Test
	public void testToStringNormal() {
		Word normal = new Word("Normal");
		assertEquals("Normal", normal.toString());
	}
	
	/** Tests constructor with an empty string. */
    @Test
    public void testConstructorEmptyString() {
        Word empty = new Word("");
        assertEquals("", empty.toString());
    }
    
    /** Tests palindrome identification. */
	@Test
	public void testIsPalindromeTrue() {
		Word palindrome = new Word("saippuakivikauppias");
		assertTrue(palindrome.isPalindrome());
	}
	
    /** Tests palindrome identification for single letter. */
	@Test
	public void testIsPalindromeOneLetter() {		
		Word oneLetter = new Word("a");
		assertTrue(oneLetter.isPalindrome());
	}
	
	
	/** Tests non-palindrome identification. */
    @Test
    public void testIsPalindromeFalse() {
        Word notPalindrome = new Word("hello");
        assertFalse(notPalindrome.isPalindrome());
    }
    
    /** Tests palindrome identification with mixed case. */
    @Test
    public void testIsPalindromeMixedCase() {
        Word mixedCase = new Word("AbBa");
        assertFalse(mixedCase.isPalindrome());
    }
    
    /** Tests that palindrome check doesn't alter Word state. */
    @Test
    public void testIsPalindromeObjectStateUnchanged() {
        Word palindrome = new Word("saippuakivikauppias");
        palindrome.isPalindrome();
        assertEquals("saippuakivikauppias", palindrome.toString());
    }

    /** Tests replaceLetter with invalid first argument. */
	@Test
	public void testReplaceLetterExceptionFirstArg() {
		Word oneLetter = new Word("a");
     	assertThrows(IllegalArgumentException.class, () -> { oneLetter.replaceLetter(' ', 'l'); });
	}
	
    /** Tests replaceLetter functionality. */
	@Test
	public void testReplaceLetterHello() {
		Word hello = new Word("hello");
		assertEquals("hesso", hello.replaceLetter('l', 's').toString());
	}
	
	
	/** Tests replaceLetter with invalid second argument. */
    @Test
    public void testReplaceLetterExceptionSecondArg() {
        Word word = new Word("hello");
        assertThrows(IllegalArgumentException.class, () -> { word.replaceLetter('h', '1'); });
    }

    /** Tests replaceLetter with non-existent letter. */
    @Test
    public void testReplaceLetterNotExisting() {
        Word word = new Word("hello");
        assertEquals("hello", word.replaceLetter('z', 'x').toString());
    }
    
    /** Tests replaceLetter doesn't alter Word state. */
    @Test
    public void testReplaceLetterObjectStateUnchanged() {
        Word word = new Word("hello");
        word.replaceLetter('h', 'x');
        assertEquals("hello", word.toString());
    }

    /** Tests word reversal functionality. */
	@Test
	public void testReverseHello() {
		Word hello = new Word("hello");
		hello.reverse();
		assertEquals("olleh", hello.toString());
	}
	
    /** Tests reversal of empty word. */
	@Test
	public void testReverseEmpty() {
		Word empty = new Word("");
		empty.reverse();
		assertEquals("", empty.toString());
	}
	
    /** Tests reversal of single-character word. */
    @Test
    public void testReverseSingleChar() {
        Word singleChar = new Word("a");
        singleChar.reverse();
        assertEquals("a", singleChar.toString());
    }
	

}