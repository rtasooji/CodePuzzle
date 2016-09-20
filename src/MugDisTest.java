
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class MugDisTest 
{
	private MugDistributor doubleLLtest;
	private MugDistributor doubleLLtest2;
	private MugDistributor doubleLLtest3;
	private MugDistributor doubleLLtest4;
	private MugDistributor doubleLLtest5;
	
	
	@Test
	public void insertTest()
	{
		
		int[] mugLocation = {0,0,2,3,0,0};
		doubleLLtest = new MugDistributor(6);
		doubleLLtest.distribute(mugLocation);
		assertEquals(8, doubleLLtest.CalculateSec());
		
		
		int[] mugLocation2 = {0,0,0,0,0,0};
		doubleLLtest2 = new MugDistributor(6);
		doubleLLtest2.distribute(mugLocation2);
		assertEquals(0, doubleLLtest2.CalculateSec());
		
		
		int[] mugLocation3 = {0,0,2,3,0,0,0,10};
		doubleLLtest3 = new MugDistributor(6);
		Exception exception = null;
		try
		{
			doubleLLtest3.distribute(mugLocation3);
		} 
		catch (Exception e)
		{
			exception = e;
		}
		assertNotNull(exception);
		assertTrue(exception instanceof NoSuchElementException);
		
		int[] mugLocation4 = {0,0,8,4,0,0};
		doubleLLtest4 = new MugDistributor(6);
		exception = null;
		try
		{
			doubleLLtest4.distribute(mugLocation4);
		} 
		catch (Exception e)
		{
			exception = e;
		}
		assertNotNull(exception);
		assertTrue(exception instanceof IndexOutOfBoundsException);
		
		
		int[] mugLocation5 = {0,5,0,0,0,0};
		doubleLLtest5 = new MugDistributor(6);
		doubleLLtest5.distribute(mugLocation5);
		assertEquals(5, doubleLLtest5.CalculateSec());
		
	}
	
	

}
