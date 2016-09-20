
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Reza Tasooji
 * @version 9/18/16
 * This class will distribute people in a semi-circular double link data structure
 * each person is a Node object in the class that holds int number of mugs.
 * The purpose of the class is to calculate total number of second requires to distribute all mugs
 * for this algorithm to work.
 * 
 */
public class MugDistributor
{
	/**
	 * first person in the list
	 */
	private Node<Integer> firstNode;
	/**
	 * last person in the list
	 */
	private Node<Integer> lastNode;
	/**
	 * total number of people
	 */
	private int capacity;
	/**
	 * Constructor
	 * @param PeopleNumber Total number of People
	 */
	public MugDistributor(int PeopleNumber)
	{
		capacity = PeopleNumber;	
	}

	/**
	 * Distribute an array of mugs to each person in the list
	 * @param mugs an array of mugs that each person will hold
	 * @throws NoSuchElementException if the length of mugs array is not equal to 
	 * 		   capacity throws exception
	 */
	public void distribute(int[] mugs)
	{
		if ( mugs.length != capacity)
		{
			throw new NoSuchElementException("length needs to much capacity");
		}
		if (tooMuchMug(mugs))
		{
			throw new IndexOutOfBoundsException("there are more mugs than the number of people");
		}
		for (int i = 0; i < capacity; i++)
		{
			insertMug(mugs[i], i+1);
		}

	}

	/**
	 * allocate the number of mug to each person
	 * @param mug number of mug to allocate 
	 * @param currCapacity total number of people
	 */
	private void insertMug(int mug, int currCapacity)
	{	
		if ( firstNode == null)
		{
			firstNode = new Node<Integer>(mug);
			lastNode = new Node<Integer>(null);
			firstNode.next(lastNode);
			lastNode.next(firstNode);
			lastNode.prev(firstNode);	
		}
		else if ( currCapacity < capacity)
		{
			Node<Integer> node = new Node<Integer>();
			lastNode.setValue(mug);
			node.next(firstNode);
			node.prev(lastNode);
			lastNode.next(node);
			lastNode = node;
		}
		else if ( currCapacity == capacity)
		{
			lastNode.setValue(mug);
			lastNode.next(firstNode);
			firstNode.prev(lastNode);
		}		
	}

	/**
	 * private class to check the array if it is bigger or smaller than the number
	 * @param array array list of mugs allocated to people
	 * @return true if there is too much mug otherwise false
	 */
	private boolean tooMuchMug(int[] array)
	{
		int totalMug = 0;
		for ( int i = 0; i < array.length; i++)
		{
			totalMug += array[i];
		}
		return (totalMug >= capacity);
	}

	/**
	 * calculate total number of second to distribute the mug between people
	 * @return number of seconds
	 */
	public int CalculateSec()
	{
		Node<Integer> currNode = firstNode;
		PeopleIterator<Integer> currIterator = getIterator(currNode);
		int totalSecond = 0;
		while (currIterator.hasNext()) 
		{
			if (currIterator.currValue() >= 2)
			{
				int currValue = currIterator.currValue();
				Node<Integer> nextNode = currIterator.currNode().getNext();
				int nextNodeValue = nextNode.getValue() + 1;
				nextNode.setValue(nextNodeValue);
				Node<Integer> prevNode = currIterator.currNode().getPreview();
				int prevNodeValue = prevNode.getValue() + 1;
				prevNode.setValue(prevNodeValue);
				currIterator.currNode().setValue(currValue - 2);
				currIterator.next();
				currIterator.Update();		
				totalSecond++;
				//debug purpose
				int[] test = toArray();
				test.toString();
			}
			else
			{
				currIterator.next();
			}
		}
		return totalSecond;

	}

	/**
	 * Debugging purposes can be removed
	 * personal class to check the location of mug in each iteration, 
	 * @return
	 */
	private int[] toArray()
	{
		Node<Integer> currNode = firstNode;
		int[] result = new int[capacity];
		for ( int i = 0 ; i < capacity ; i++)
		{
			result[i] = currNode.getValue();
			currNode = currNode.getNext();
		}
		return result;
	}

	/**
	 * Iterator for the class
	 * @param currNode iterator based on the currentNode 
	 * @return
	 */
	public <T>PeopleIterator<T> getIterator(Node<T> currNode)
	{
		return new PeopleIterator<T>(currNode);
	}

	/**
	 * custom iterator for this class
	 * @author Reza Tasooji
	 *
	 * @param <T> Generic
	 */
	private class PeopleIterator<T> implements Iterator<T>
	{
		int nextIndex;
		private Node<T> currNode;

		private PeopleIterator(Node<T> currentNode) 
		{
			nextIndex = 0;
			currNode =  currentNode;
		}
		@Override
		public boolean hasNext() 
		{
			if ( nextIndex < capacity)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		@Override
		public T next() 
		{
			if ( hasNext())
			{
				currNode = currNode.getNext();
				nextIndex++;
				return currNode.getValue();
			}
			else
			{
				throw new NoSuchElementException("Illegal call to next()");
			}

		}
		public T currValue()
		{
			return currNode.type_;
		}
		public Node<T> currNode()
		{
			return currNode;
		}

		public void Update()
		{
			nextIndex = 0;
		}
	}

	/**
	 * Node class 
	 * @author Reza Tasooji
	 *
	 * @param <T> Generic
	 */
	private class Node<T>
	{
		private T type_;
		private Node<T> next;
		private Node<T> prev;

		public Node()
		{
			type_ = null;
			next = null;
			prev = null;
		}

		public Node(T type)
		{
			type_ = type;
			next = null;
			prev = null;
		}

		public void next(Node<T> nextNode)
		{
			next = nextNode;
		}

		public void prev(Node<T> previewNode)
		{
			prev = previewNode;
		}

		public Node<T> getNext()
		{
			return next;
		}

		public Node<T> getPreview()
		{
			return prev;
		}

		public void setValue(T type)
		{
			type_ = type;
		}

		public T getValue()
		{
			return type_;
		}

	}
}

