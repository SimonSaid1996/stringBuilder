// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		
	}

	public int returnLength(){
		return length;
	}
	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{		
		if(s.length == 0 || s == null ){ //empty char array, special case
			firstC = null;
			lastC = null;
			length = 0;		
		}
		else{
			firstC = new CNode(s[0]);
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode( s[ i ] );
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;		
	}
	
	//adding an int into the stringbuilder
	public MyStringBuilder append(int addedInt){
		char[] chars = ("" + addedInt).toCharArray();
		if(firstC !=null){		//first node is not null case
			
			//CNode newNode = new CNode( Integer.toString(addedInt) );
			CNode currNode = firstC;
			while(currNode.next != null){
				currNode= currNode.next;
			}
			CNode newNode = null;
			for (int i = 1; i < chars.length; i++)
			{
				newNode = new CNode( chars[ i ] );
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			currNode.next = newNode;
			lastC = newNode;
			length++;
		}
		else{
			firstC = new CNode(chars[0]);
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < chars.length; i++)
			{
				CNode newNode = new CNode( chars[ i ] );
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		return this;
			
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		CNode curNode = null;
		if( b.length == 0 || b == null){	//if nothing in b, then do nothing
			return this;
		}
		else{
			if(firstC != null){		//current mystringbuilder is not null situation
				curNode = lastC; //set the curnode as the lastC
				CNode curBNode = b.firstC;
				for(int i = 0; i < b.length; i++){
					CNode insertedNode = new CNode (curBNode.data);
					curNode.next = insertedNode;
					curNode = insertedNode;
					curBNode = curBNode.next; //to go to the next node
					this.length ++;
				}
			}
			else{
				curNode = b.firstC;
				CNode curBNode = b.firstC.next;
				firstC = curNode;
				for(int i = 0; i < b.length; i++){
					CNode insertedNode = new CNode (curBNode.data);
					curNode.next = insertedNode;
					curNode = insertedNode;
					curBNode = curBNode.next; //to go to the next node
					this.length ++;	
				}	
			}	
		}
			lastC = curNode;
			return this;
	}
	


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)throws NullPointerException
	{
		if(firstC !=null){
			if( s!=null ){	//parameter not null situation
				int strLen = s.length();
				if(strLen == 0){	//do nothing
					return this;
				}
				else{
					CNode curNode = lastC;
					for(int i =0;i<strLen; i++){
						char curChar = s.charAt(i);
						CNode newNode = new CNode(curChar);
						curNode.next = newNode;
						curNode = newNode;
						length++;
					}
					lastC = curNode;
				}
			}		
		}
		else{		//firstC is null situation
			if( s!=null ){	//parameter not null situation
				int strLen = s.length();
				if(strLen == 0){	//do nothing
					
					return null;//will get thrown as null pointer exception, can't add because no initial letters and adding a 0 length string
				}
				else{
					//System.out.println("i am here "+length);
					CNode curNode = new CNode(s.charAt(0));
					firstC = curNode;
					length++;
					for(int i =1;i<strLen; i++){
						char curChar = s.charAt(i);
						CNode newNode = new CNode(curChar);
						//System.out.println("adding "+curChar);
						curNode.next = newNode;
						curNode = newNode;
						length++;
					}
					//System.out.println("final length is "+length);
					lastC = curNode;
				}
			}
		}				
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		if(firstC !=null ){		//normal case where the first node is not null
			if ( c!= null ){
				int cLen= c.length;
				if( cLen == 0 ){	//do nothing
					return this;
				}
				else{
					CNode curNode = lastC;
					for(int i = 0; i<cLen ; i++){
						CNode newNode = new CNode( c[ i ] );
						curNode.next = newNode;
						curNode = newNode;
						length ++;
					}
					lastC = curNode;
				}
			}
		}
		else{
			if ( c!= null ){
				int cLen= c.length;
				if( cLen == 0 ){	//do nothing
					return this;
				}
				else{					//append when original builder is null
					CNode curNode = new CNode( c[ 0 ] );
					firstC = curNode;
					for(int i = 1; i<cLen ; i++){
						CNode newNode = new CNode( c[ i ] );
						curNode.next = newNode;
						curNode = newNode;
						length ++;
					}
					lastC = curNode;
				}
			}
		}
		
		return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
											//char is not null situation	
		if (firstC != null){
			CNode addedNode = new CNode(c);			//new node to add
			length ++;								
			lastC.next = addedNode;					//not sure how to return the mystring builder
			lastC = addedNode;
		}
		else{
			CNode addedNode = new CNode(c);	
			firstC = addedNode;
			length ++;
			lastC = addedNode;
		}
		
		//if char is null, do nothing return the original stringbuilder 
				
		
		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)                                 //throws IndexOutOfBoundsException
	{
		//this function might have some issues, check it out later
		if ( index< length ){		//valid index case 
			if( firstC !=null ){		//not null situation
				CNode curNode = firstC;
				for(int i = 0; i<index; i++){
					curNode = curNode.next; //go to the next node
				}
				//retreat the curNode's value
				char retriveChar = curNode.data;
				return retriveChar;
			}
			else			//need to throw the exception 2 because length is 0, index must be negative
				throw new NullPointerException("null pointer");
		}
		else			//invalid index case
			throw new IndexOutOfBoundsException("Illegal Index");
		
		
		
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{	
		CNode curStartNode = firstC;
		CNode curEndNode = firstC;
		if ( firstC != null) {	//situations when there are characters in the stringbuilder
			if(start > 0 && end >start && end<= length ){   //the normal situationn
			
				for(int i = 0; i< start-1; i++){
					curStartNode = curStartNode.next; //go to the start node, including the starting node
				}
				curEndNode = curStartNode;
				int j =0;
			
				while( j< end-start+1 && curEndNode.next != null ){	//iterate through to the end node
					curEndNode = curEndNode.next;
					j++;
				}
			
				curStartNode.next = curEndNode;     //set the next node to the curEndNode, normal case is deleting before the end
			
				if(curEndNode.next == null){		//the endNode is the last node to delete case, set the curStartNode as the last node 
					curStartNode.next = null;
					lastC = curStartNode;
				}
				length = length - (end-start);//update the length
			}
			else if (start ==0 && end >start && end<= length &&length != 1){  //to delete including the first node
				int j =0;
				while( j< end-start && curEndNode.next != null ){	//iterate through to the end node
					curEndNode = curEndNode.next;
					j++;
				}
				firstC = curEndNode;     //set the next node to the curEndNode, normal case is deleting before the end
				if(curEndNode.next == null){		//the endNode is the last node to delete case, set the curStartNode as the last node 
					curStartNode.next = null;
					lastC = curStartNode;
				}
				length = length - (end-start);
			}
			else if( start > 0 && end >start && end > length ){//to delete until the end of the string
				for(int i = 0; i< start-1; i++){
					curStartNode = curStartNode.next; //go to the start node, including the starting node
				}
				curStartNode.next = null;
				lastC = curStartNode;
				length = start;
			}
			else if( start ==0 && end ==1 &&length == 1){		//corner case, delete the last item   length ==1 
				//System.out.println("delete special case length is "+length);
				length = 0;
				firstC = null;
				lastC = null;
			}
		}
		//basically when reistC is null, re do nothing and directly return the original stringbuilder
		
		
		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if( firstC != null ){			//siturations when deleting from a non-empty stringbuilder
			if(index >= 0 && index <length){
				CNode curNode = firstC;
				CNode nextNode = null;
				CNode prevNode = firstC;
				if(index < length -1 && index !=0){    //delete in the middle 
					for(int i = 0; i<index-1;i++){
						prevNode=prevNode.next;
					}
					curNode = prevNode.next;
					nextNode = curNode.next;
					prevNode.next = nextNode;
				
					for(int j=length-index; j<length-1;j++){
						nextNode=nextNode.next;
					}
					lastC= nextNode;
					length--;
				}
				else if(index == 0){//delete the front
					length --;
					firstC = curNode.next;
				}
				else{ //delete mode in the end
					for(int i = 0; i<index-1;i++){
						curNode=curNode.next;
					}
					curNode.next = null;
					lastC = curNode;//reset the lastNode
					length--;
				}		
			}
		}
		//if the stringbuilder is null, return null directly
		
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		CNode curNode = firstC;
		int strIndx = 0;
		char curC = str.charAt(strIndx);
		boolean hasMatch = false;
		int returnedIndx = 0;
		int count = 0;
		
		if(firstC != null){					//normal cases where  the firstC is not null, try to find indexes, if it is null, directly return -1
			while( !hasMatch && strIndx < length && curNode.next!=null){
				if(curC == curNode.data ){
					if( strIndx == 0 ){ //the first match, prevIndx is used to check if consecutive
						returnedIndx = count;	
					}
				
					strIndx ++;//update all the points
					curC = str.charAt(strIndx);
					//System.out.println("updating c "+curC);
				
				}
				else{			//if not match, need to reset strIndx
					strIndx = 0;
					curC = str.charAt(0);
					//System.out.println("fk");
					//hasMatch = false;
				}
				if(strIndx == str.length()-1 ){
					hasMatch = true;
					//System.out.println("true strIndx is "+strIndx+" str.length()-1 is "+str.length());
				}
				count ++;
				curNode = curNode.next;			
			}
		
			//System.out.println("the end");
			//need to add the last character check!!
			if( hasMatch ){
				//System.out.println("returned indx is "+returnedIndx);
				return returnedIndx;			
			}
		}
		return -1;   //return the index of the nodes
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		CNode curNode = firstC;
		CNode curNextNode = null;
		int strLen = str.length();
		//
		if (firstC != null){		//if the firstC is not null case, we count the starting index
			if(offset==length){
				CNode curNode1 = new CNode(str.charAt(0));
				firstC = curNode1;
				length++;
				for(int i =1;i<strLen; i++){
					char curChar = str.charAt(i);
					CNode newNode = new CNode(curChar);
					//System.out.println("adding "+curChar);
					curNode1.next = newNode;
					curNode1 = newNode;
					length++;
				}
				//System.out.println("final length is "+length);
				lastC = curNode1;
			
			}
			else if (offset>0 && offset <length){//adding in the middle
			
			
				for(int i =0; i<offset-1; i++){
					curNode = curNode.next;
				}
				curNextNode = curNode.next;  //plz think about the ending case
			
				for(int j = 0;j<strLen;j++){
					char curChar = str.charAt( j );
					CNode temp = new CNode( curChar );
					curNode.next = temp;
					curNode = temp;
					length++;
				}
				curNode.next = curNextNode;
			}
			else if( offset == 0 ){
				CNode temp1 = firstC;
				firstC = new CNode( str.charAt( 0 ) );
				curNode = firstC;
				length++;
				for(int j = 1; j<strLen ;j++){
					char curChar = str.charAt( j );
					CNode temp = new CNode( curChar );
					curNode.next = temp;
					curNode = temp;
					length++;
				}			
				curNode.next = temp1;
			}
		}
		else{							//adding into a null mystringbuilder, directly add the string
			CNode curNode1 = new CNode(str.charAt(0));
			firstC = curNode1;
			length++;
			for(int i =1;i<strLen; i++){
				char curChar = str.charAt(i);
				CNode newNode1 = new CNode(curChar);
				//System.out.println("adding "+curChar);
				curNode.next = newNode1;
				curNode = newNode1;
				length++;
			}
			//System.out.println("final length is "+length);
			lastC = curNode;
		}		
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		
		if( ( offset==length && length != 0 ) || firstC ==null ){	//situations when adding at the back or adding when the original stringbuilder is null		
			CNode addedNode = new CNode(c);	
			firstC = addedNode;
			length ++;
			lastC = addedNode;
		}
		else if (offset>=0 &&offset <length){//adding in the middle
			CNode curNode = firstC;
			CNode curNextNode = null;
			for(int i =0; i<offset; i++){
				curNode = curNode.next;
			}
			curNextNode = curNode.next;  //plz think about the ending case
			
			CNode temp = new CNode( c );
			curNode.next = temp;
			curNode = temp;
			length++;
			curNode.next = curNextNode;
			
		}
		
		
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		if(offset==length || firstC ==null){
			CNode curNode = new CNode( c[ 0 ] );
			firstC = curNode;
			for(int i = 1; i<c.length ; i++){
				CNode newNode = new CNode( c[ i ] );
				curNode.next = newNode;
				curNode = newNode;
				length ++;
			}
			lastC = curNode;
		}
		else if (offset>0 &&offset <length){//adding in the middle
			CNode curNode = firstC;
			CNode curNextNode = null;
			int strLen = c.length;
			for(int i =0; i<offset; i++){
				curNode = curNode.next;
			}
			curNextNode = curNode.next;  //plz think about the ending case
			for(int j = 0;j<strLen;j++){
				char curChar = c[ j ]  ;
				CNode temp = new CNode( curChar );
				curNode.next = temp;
				curNode = temp;
				length++;
			}
			curNode.next = curNextNode;
			
		}
		
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)
	{ 
		CNode curNode = firstC;
		CNode nextNode = null;
		//in this method, we already assume that the stringbuilder is not null currently
		
		if(start >= 0 && end < length && end >=start){		//the normal case, add in the middle
			//if( end < length ){//replace until the end of the stringbuilder
				
			for (int i =0;i<start-1; i++){//iterate to the starting node
				curNode = curNode.next;
			}
			nextNode = curNode;
			for(int j = 0; j <end-start+1;j++){	//iterate to the end point
				nextNode = nextNode.next;
			}
				
			//System.out.println("start char is "+curNode.data+" the end char is "+nextNode.data);
				
			int strLen = str.length();
			for(int j = 0;j<strLen;j++){
				char curChar = str.charAt( j );
				CNode temp = new CNode( curChar );
				curNode.next = temp;
				curNode = temp;
				length++;
			}
			curNode.next = nextNode;	
			
			
		}
		else if(start >= 0 && end >=start && end >= length ){	//delete until the end of the mystring and then insert 
			for (int i =0;i<start-1; i++){//iterate to the starting node
				curNode = curNode.next;
			}
			nextNode = curNode;				//starting from the curNode, set all the next nodes as the new string
			int strLen = str.length();
			for(int j = 0;j<strLen;j++){
				char curChar = str.charAt( j );
				CNode temp = new CNode( curChar );
				nextNode.next = temp;
				nextNode = temp;
				length++;
			}
			lastC = nextNode;
		}
		
		return this;

	}

	// Reverse the characters in the current MyStringBuilder and then
	// return the current MyStringBuilder.
	public MyStringBuilder reverse()
	{
		CNode curNode = firstC;
		CNode nextNode = null;
		CNode prevNode = null;
		
		
		while(curNode != null ){
			nextNode = curNode.next;
			curNode.next = prevNode;
			prevNode = curNode;
			curNode = nextNode;
		}
		firstC = prevNode;
		return this;
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	
	//also ask the professor if start has to be less than the length or there are special cases
	public String substring(int start, int end)
	{
		char[] c = new char[length];
		CNode curNode = firstC;
		int difference = end - start ;
		for(int j = 0;j<start;j++){
			curNode = curNode.next; //iterate to the starting node, assuming start is less than the length
		}
		for(int i = 0; i < difference ; i++){
			c[i]=curNode.data;
			curNode = curNode.next;
		}
		return new String(c);
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		char[] c = new char[length];
		CNode curNode = firstC;
		int i = 0;
		//System.out.println("curnode is "+curNode.data);
		
		while(curNode !=null){
			//System.out.println("i is "+i+" length is "+length+" curNode.data "+curNode.data);
			c[i]=curNode.data;
			i++;
			curNode = curNode.next;
		}
		return new String(c);
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
