// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
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

	// Create a new MyStringBuilder2 initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
	  if (s != null && s.length() > 0)
        makeBuilder(s, 0);
      else  // no String so initialize empty MyStringBuilder2
      {
        length = 0;
        firstC = null;
        lastC = null;
      }
	  //CNode test = firstC;
	  
	}
	// Recursive method to set up a new MyStringBuilder2 from a String

	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
	  if (s != null && s.length > 0)
        makeBuilderChar(s, 0);
      else  // no String so initialize empty MyStringBuilder2
      {
        length = 0;
        firstC = null;
        lastC = null;
      }
	}
	
	private void makeBuilder(String s, int pos)
	{
      // Recursive case – we have not finished going through the String
		if (pos < s.length()-1)
		{
            // Note how this is done – we make the recursive call FIRST, then
            // add the node before it.  In this way the LAST node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node.  However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilder(s, pos+1);
            firstC = new CNode(s.charAt(pos), firstC);
            length++;
		}
		else if (pos == s.length()-1) // Special case for last char in String
		{                             // This is needed since lastC must be
                                    // set to point to this node
            firstC = new CNode(s.charAt(pos));
            lastC = firstC;
            length = 0;
		}
		else  // This case should never be reached, due to the way the
            // constructor is set up.  However, I included it as a
		{     // safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
            lastC = null;
		}
	}
	
	private void makeBuilderChar(char[] s, int pos)
	{
      // Recursive case – we have not finished going through the String
		if (pos < s.length-1)
		{
            makeBuilderChar(s, pos+1);
            firstC = new CNode( s[ pos ], firstC );
            length++;
		}
		else if (pos == s.length-1) // Special case for last char in String
		{                             // This is needed since lastC must be
                                    // set to point to this node
            firstC = new CNode( s[ pos ] );
            lastC = firstC;
            length = 0;
		}
		else  // This case should never be reached, due to the way the
            // constructor is set up.  However, I included it as a
		{     // safeguard (in case some other method calls this one)

            length = 0;
            firstC = null;
            lastC = null;
		}
	}
	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}

	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		if(this.length > 0){		//cases that mystringbuilder is not null currently
			if( b.length > 0 ){			//cases when appending stringbuilder is not null
				CNode cur = b.firstC;
				appendBuilder(b, 0, cur);		//start the recursion
			}
			//else nothing happen		
		}
		else{
			if( b.length > 0 ){			//cases when appending stringbuilder is not null, we just make b our stringbuilder
				return b;
			}
			//else nothing happen
		}
		//CNode test = firstC;
		/*while(test!=null){
			System.out.println("curr is "+test.data+" "+ length);
			test = test.next;
		}*/
		return this;
	}


	private void appendBuilder(MyStringBuilder2 s, int pos, CNode cur)//pos is the index of the current element in s
	{
      // base case
		if( pos == s.length ){		//reached the length of both stringbuilders, finish appending
			//return this;
			CNode temp = new CNode ( cur.data );
			//System.out.println("adding the last node "+cur.data);
			lastC.next = temp;
			lastC = temp;
			length++;
		}
		else{ //recursive case
			CNode temp = new CNode ( cur.data );
			cur = cur.next;		//update the node in s
			lastC.next = temp;
			lastC = temp;
			appendBuilder( s,  pos+1, cur);
			length ++;
			
		}
		//return this;
	}
	
	/** the method to traverse  to the target node from the current node, adding every elements of the stringbuilder to the original string builder 
	*/	
	/*private CNode traverseToNode(int curIndx, int goal, CNode curNode){
		CNode targetNode = null;
		if(curIndx < goal){		//iterative cases
			curNode = curNode.next;				//go to the next node
			curNode = traverseToNode(curIndx+1, sLength, curNode);
		}
		else{						//base case
			return curNode;				//return the curNode
		}
		return curNode;
	}*/
	
	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		if(this.length > 0){		//cases that mystringbuilder is not null currently
			if( s.length() > 0 ){			//cases when appending stringbuilder is not null
				CNode cur = new CNode ( s.charAt(0) );
				appendString(s, 0, cur);		//start the recursion
			}
			//else nothing happen		
		}
		else{
			if( s.length() > 0 ){			//cases when appending stringbuilder is not null, we just make b our stringbuilder
				makeBuilder(s, 0);
      
				//return new MyStringBuilder2( s);
			}
			//else nothing happen
		}
		return this;
	}

	
	private void appendString(String s, int pos, CNode cur)//pos is the index of the current element in s
	{
      // base case
		if( pos == s.length()-1 ){		//reached the length of both stringbuilders, finish appending
			CNode temp = new CNode ( s.charAt( pos ) );
			lastC.next = temp;
			lastC = temp;
			length++;
		}
		else{ //recursive case
			//CNode temp = new CNode ( cur.data );
			//cur = cur.next;		//update the node in s
			lastC.next = cur;
			lastC = cur;
			CNode temp = new CNode( s.charAt( pos+1 ) );
			appendString( s,  pos+1, temp);
			length++;
		}
		//return this;
	}
	
	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		if(this.length > 0){		//cases that mystringbuilder is not null currently
			if( c.length > 0 ){			//cases when appending stringbuilder is not null
				CNode cur = new CNode ( c[0] );
				appendCharArray(c, 0, cur);		//start the recursion
			}
			//else nothing happen		
		}
		else{
			if( c.length > 0 ){			//cases when appending stringbuilder is not null, we just make b our stringbuilder
				//return new MyStringBuilder2(c);
				makeBuilderChar(c, 0);
				//need to convert the string into stringbuilder here
			}
			//else nothing happen
		}
		return this;
	}
	
	private void appendCharArray(char[] c, int pos, CNode cur)//pos is the index of the current element in s
	{
      // base case
		if( pos == c.length-1 ){		//reached the length of both stringbuilders, finish appending
			CNode temp = new CNode ( c[ pos ] );
			lastC.next = temp;
			lastC = temp;
			length++;
		}
		else{ //recursive case
			lastC.next = cur;
			lastC = cur;
			CNode temp = new CNode( c[ pos+1 ] );
			appendCharArray( c,  pos+1, temp);
			length++;
		}
		//return this;
	}

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		if(this.length > 0){		//cases that mystringbuilder is not null currently
			
			CNode cur = new CNode ( c );
			lastC.next = cur;
			lastC = cur;
			length++;
			
			//else nothing happen		
		}
		else{
			CNode cur = new CNode ( c );
			lastC = cur;
			firstC = cur;
			length++;
		}
		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		CNode curNode = firstC;
		CNode target = iterateTo(0, index+1, curNode );
		//System.out.println("the data is "+target.data);
		return target.data;
	}
	
	
	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{
		CNode curStartNode = firstC;
		CNode curEndNode = null;
		if (firstC != null) {
			if( start > 0 && end >start && end<= length ){   // the normal cases
				curStartNode = iterateTo(0, start-1, curStartNode );
				curEndNode = iterateTo(0, end-start+1, curStartNode );
				curStartNode.next = curEndNode;		// to link the before target and after targetNode together
				if(curEndNode.next == null){		//the endNode is the last node to delete case, set the curStartNode as the last node 
					curStartNode.next = null;
					lastC = curStartNode;
				}
				length = length - (end-start);//update the length
			}
			else if (start ==0 && end >start && end<= length &&length != 1){  //to delete including the first node
				int j =0;				
				curEndNode = iterateTo(0, end-start, curStartNode );				
				firstC = curEndNode;     //set the next node to the curEndNode, normal case is deleting before the end
				if(curEndNode.next == null){		//the endNode is the last node to delete case, set the curStartNode as the last node 
					curStartNode.next = null;
					lastC = curStartNode;
				}
				length = length - (end-start);
			}
			else if( start > 0 && end >start && end > length ){//to delete until the end of the string
				curStartNode = iterateTo(0, start-1, curStartNode ); //go to the start node, including the starting node
				curStartNode.next = null;
				lastC = curStartNode;
				length = start;
			}
			else if( start ==0 && end ==1 &&length == 1){		//corner case, delete the last item   length ==1 
				length = 0;
				firstC = null;
				lastC = null;
			}
		}
		return this;		
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if(index >= 0 && index <length){
			CNode curNode = firstC;
			CNode nextNode = null;
			CNode prevNode = firstC;
			if(index < length -1 && index !=0){    //delete in the middle 
				
				prevNode = iterateTo(0, index-1, curNode );
				curNode = prevNode.next;
				nextNode = curNode.next;
				prevNode.next = nextNode;
				
				length--;
			}
			else if(index == 0){//delete the front
				length --;
				firstC = curNode.next;
			}
			else{ //delete mode in the end			
				curNode=iterateTo(0, index-1, curNode );
				curNode.next = null;
				lastC = curNode;//reset the lastNode
				length--;
			}		
		}
		return this;
	}
	
	private int[] recursiveWhileLoop(int[] hasMatch, int strIndx, CNode curNode, int count, char curC, int returnedIndx, String str){
		if( hasMatch[0]==0 && strIndx < length && curNode.next!=null ){			//recursive case
			if(curC == curNode.data ){
				if( strIndx == 0 ){ //the first match, prevIndx is used to check if consecutive
					returnedIndx = count;
					hasMatch[1]= returnedIndx;
				}
				
				strIndx ++;//update all the points
				curC = str.charAt(strIndx);
				//System.out.println("updating c "+curC);
				
			}
			else{			//if not match, need to reset strIndx
				strIndx = 0;
				curC = str.charAt(0);
				
			}
			if(strIndx == str.length()-1 ){
				hasMatch[0] = 1;
			}
			hasMatch = recursiveWhileLoop( hasMatch,  strIndx,  curNode.next, count+1, curC, returnedIndx,str  );
		}//other situations are the normal cases
		return hasMatch;
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		CNode curNode = firstC;
		int strIndx = 0;
		char curC = str.charAt(strIndx);
		int[] hasMatch = new int[2];
		int returnedIndx = 0;
		int count = 0;
		
		if(firstC != null){					//normal cases where  the firstC is not null, try to find indexes, if it is null, directly return -1
			hasMatch = recursiveWhileLoop( hasMatch,  strIndx,  curNode,  count,  curC,  returnedIndx,  str);
			//need to change the hasMatch into an array to store the value of count, and hasMatch 
			if( hasMatch[0] ==1 ){
				//System.out.println("returned indx is "+hasMatch[1]);
				return hasMatch[1];			
			}
		}
		return -1;   //return the index of the nodes
	}
	
	
	private CNode iterateTo(int cur, int index, CNode curNode){
		if( cur == index){			//base case
			//System.out.println("cur data is "+curNode.data);
			return curNode;
		}
		else{						//go to the next node
			//System.out.println("the index is "+index+" cur is "+cur+" cur node is "+curNode.data);
			curNode = iterateTo(cur+1, index, curNode.next);
			//System.out.println("the ending node is "+curNode.data);
		}
		return curNode;
	}
	
	
	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	
	private CNode iterativeAddingStr(int cur, int index, CNode curNode, String str){
		
		if (curNode != firstC){
			if( cur == index){			//base case
				return curNode;
			}
			else{		
				char curChar = str.charAt( cur );	//go to the next node
				//System.out.println("cur char is "+curChar);
				CNode newNode = new CNode( curChar );										
				curNode.next = newNode;
				curNode = newNode;
				length++;
				curNode = iterativeAddingStr(cur+1, index, curNode, str);						
			}
		}
		else{			//situations when curNode is the first node
			char curChar = str.charAt( cur );	//go to the next node
			CNode newNode = new CNode( curChar );
			CNode anotherNode = null;
			if(cur == 0){
				firstC = newNode;
				anotherNode = newNode;	//use the anotherNode to traverse
				cur++;
				length++;
				//System.out.println("shit"+length);
			}
			if( cur == index){			//base case
				return curNode;
			}
			else{		
				char curChar1 = str.charAt( cur );	//go to the next node
				CNode newNode1 = new CNode( curChar1 );				
				newNode.next = newNode1;
				newNode = newNode1;
				length++;
				newNode = iterativeAddingStr(cur+1, index, newNode, str);						
			}
			newNode.next = curNode;
			curNode = newNode;
		}
		
		return curNode;
	}
	
	
	public MyStringBuilder2 insert(int offset, String str)
	{	//System.out.println("beginning the length is "+length);
		CNode curNode = firstC;
		CNode curNextNode = null;
		int strLen = str.length();
		//
		if (firstC != null){		//if the firstC is not null case, we count the starting index
			if(offset==length){
				CNode curNode1 = new CNode(str.charAt(0));
				firstC = curNode1;
				length++;
				/*for(int i =1;i<strLen; i++){
					char curChar = str.charAt(i);
					CNode newNode = new CNode(curChar);
					//System.out.println("adding "+curChar);
					curNode1.next = newNode;
					curNode1 = newNode;
					length++;
				}*/
				
				curNode = iterativeAddingStr(0, strLen, curNode1, str);
				//length = length + strLen;
				lastC = curNode1;
			
			}
			else if (offset>0 && offset <length){//adding in the middle
			
				curNode = iterateTo(0, offset-1, curNode);
				/*for(int i =0; i<offset-1; i++){
					curNode = curNode.next;
				}*/
				curNextNode = curNode.next;  //plz think about the ending case
				curNode = iterativeAddingStr(0, strLen, curNode, str);
				//System.out.println("wtf, the length is "+length);
				//length = length + strLen;
				//System.out.println("the length is "+length);
				/*for(int j = 0;j<strLen;j++){
					char curChar = str.charAt( j );
					CNode temp = new CNode( curChar );
					curNode.next = temp;
					curNode = temp;
					length++;
				}*/
				curNode.next = curNextNode;
			}
			else if( offset == 0 ){
				CNode temp1 = firstC;
				/*firstC = new CNode( str.charAt( 0 ) );
				curNode = firstC;
				length++;*/
				curNode = iterativeAddingStr(0, strLen, curNode, str);
				//length = length + strLen;
				/*for(int j = 1; j<strLen ;j++){
					char curChar = str.charAt( j );
					CNode temp = new CNode( curChar );
					curNode.next = temp;
					curNode = temp;
					length++;
				}*/			
				curNode.next = temp1;
			}
		}
		else{							//adding into a null mystringbuilder, directly add the string
			CNode curNode1 = new CNode(str.charAt(0));
			firstC = curNode1;
			length++;
			
			curNode = iterativeAddingStr(0, strLen, curNode, str);
			length = length + strLen;
			/*for(int i =1;i<strLen; i++){
				char curChar = str.charAt(i);
				CNode newNode1 = new CNode(curChar);
				//System.out.println("adding "+curChar);
				curNode.next = newNode1;
				curNode = newNode1;
				length++;
			}*/
			//System.out.println("final length is "+length);
			lastC = curNode;
		}		
		return this;	
	}

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
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
			curNode = iterateTo(0, offset, curNode);
			/*for(int i =0; i<offset; i++){
				curNode = curNode.next;
			}*/
			curNextNode = curNode.next;  //plz think about the ending case			
			CNode temp = new CNode( c );
			curNode.next = temp;
			curNode = temp;
			length++;
			curNode.next = curNextNode;
			
		}
		
		
		return this;
	}
	
	
	private void iterativeAddingCharArray(int cur, int index, CNode curNode, char []c){
		if( cur == index){			//base case
			//return curNode;
			lastC = curNode;
		}
		else{						//go to the next node
			char curChar = c[ cur ];
			CNode newNode = new CNode( curChar );
			curNode.next = newNode;
			curNode = newNode;
			length++;
			iterativeAddingCharArray(cur+1, index, curNode.next, c);			
		}
	}

	// Insert char array c into the current MyStringBuilder2 starting at index
	// index "offset" and return the current MyStringBuilder2.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		if(offset==length || firstC ==null){
			CNode curNode = new CNode( c[ 0 ] );
			firstC = curNode;
			iterativeAddingCharArray(0, c.length, curNode, c);
			/*for(int i = 1; i<c.length ; i++){
				CNode newNode = new CNode( c[ i ] );
				curNode.next = newNode;
				curNode = newNode;
				length ++;
			}*/
			lastC = curNode;
		}
		else if (offset>0 &&offset <length){//adding in the middle
			CNode curNode = firstC;
			CNode curNextNode = null;
			int strLen = c.length;
			curNode = iterateTo(0, offset, curNode);
			/*for(int i =0; i<offset; i++){
				curNode = curNode.next;
			}*/
			curNextNode = curNode.next;  //plz think about the ending case
			
			iterativeAddingCharArray(0, strLen, curNode, c);			
			/*for(int j = 0;j<strLen;j++){
				char curChar = c[ j ]  ;
				CNode temp = new CNode( curChar );
				curNode.next = temp;
				curNode = temp;
				length++;
			}*/
			curNode.next = curNextNode;
			
		}
		
		return this;
	}

	// Return the length of the current MyStringBuilder2
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		CNode curNode = firstC;
		CNode nextNode = null;		
		
		if(start >= 0 && end >=start && end >= length ){	//delete until the end of the mystring and then insert 
			/*for (int i =0;i<start-1; i++){//iterate to the starting node
				curNode = curNode.next;
			}*/
			//System.out.println("start is "+start);
			curNode = iterateTo(0, start-1, curNode);				
			//System.out.println("curNode value is "+curNode.data);	
			nextNode = curNode;				//starting from the curNode, set all the next nodes as the new string
			int strLen = str.length();
			curNode = iterativeAddingStr(0, strLen, nextNode, str);			
			
			/*for(int j = 0;j<strLen;j++){
				char curChar = str.charAt( j );
				CNode temp = new CNode( curChar );
				nextNode.next = temp;
				nextNode = temp;
				length++;
			}*/
			lastC = nextNode;
		}
		else if(start >= 0 && end < length && end >=start){		//the normal case, add in the middle
			//if( end < length ){//replace until the end of the stringbuilder
			/*for (int i =0;i<start-1; i++){//iterate to the starting node
				curNode = curNode.next;
			}*/
				//System.out.println("damn it ");
				curNode = iterateTo(0, start-1, curNode);
				//System.out.println("curNode is "+curNode.data);
				nextNode = curNode;
				nextNode = iterateTo(0, end-start+1, nextNode);	
			/*for(int j = 0; j <end-start+1;j++){	//iterate to the end point
				nextNode = nextNode.next;
			}*/
				
			//System.out.println("start char is "+curNode.data+" the end char is "+nextNode.data);
				int strLen = str.length();
				curNode = iterativeAddingStr(0, strLen, curNode, str);			
			/*for(int j = 0;j<strLen;j++){
				char curChar = str.charAt( j );
				CNode temp = new CNode( curChar );
				curNode.next = temp;
				curNode = temp;
				length++;
			}*/
				curNode.next = nextNode;	
			
			
		}
		
		return this;	
	}

	private CNode reverseRecursion(CNode curNode, CNode nextNode, CNode prevNode){
		if(curNode.next == null){//base case, do nothing
			//System.out.println("fk u "+curNode.data);
			curNode.next = prevNode;
			return curNode;
		}
		else{
			//System.out.println("cur IS "+curNode.data);
			nextNode = curNode.next;
			curNode.next = prevNode;
			prevNode = curNode;
			curNode = nextNode;
			curNode = reverseRecursion( curNode,  nextNode, prevNode);
		}
		return curNode;
	}
	
	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse()
	{//don't forget to do the lastC shit
		CNode curNode = firstC;
		lastC = curNode;// bcause reversing the order
		CNode nextNode = null;
		CNode prevNode = null;
		curNode = reverseRecursion( curNode,  nextNode,  prevNode);
		/*while(curNode != null ){
			nextNode = curNode.next;
			curNode.next = prevNode;
			prevNode = curNode;
			curNode = nextNode;
		}*/
		firstC = curNode;
		return this;
	}
	
	private void iterativeSettingCharArray(int cur, int index, CNode curNode, char []c){
		if( cur == index){			//base case
			
		}
		else{						//go to the next node
			c[ cur ]=curNode.data;
			iterativeSettingCharArray(cur+1, index, curNode.next, c);			
		}
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
		char[] c = new char[length];
		CNode curNode = firstC;
		int difference = end - start ;
		curNode = iterateTo(0, start, curNode);				
		
		/*for(int j = 0;j<start;j++){
			curNode = curNode.next; //iterate to the starting node, assuming start is less than the length
		}*/
		iterativeSettingCharArray(0 , difference, curNode, c);		
		/*for(int i = 0; i < difference ; i++){
			c[i]=curNode.data;
			curNode = curNode.next;
		}*/
		return new String(c);
	}

	// Return the entire contents of the current MyStringBuilder2 as a String
	public String toString()
	{
		char [] c = new char[length+1];
		getString(c, 0, firstC);
		return (new String(c));
	}
	
	// Here we need the char array to store the characters, the pos value to
	// indicate the current index in the array and the curr node to access
	// the data in the actual MyStringBuilder2.  Note that these rec methods
	// are private – the user of the class should not be able to call them.

	private void getString(char [] c, int pos, CNode curr)
	{
      if (curr != null)
      {
        c[pos] = curr.data;
        getString(c, pos+1, curr.next);
      }
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str)			//seems like the opposite as indexof, don't u need to go all the way to the back and kind of backtrack? maybe try to get a char[] first, then go from the back of the char and find the first match?
	{//backtracking 
	 // the current character is the row, and the matching character is column basically
	 // go to the end if no match 
	 // go backward if match, match count ++
	 // if not match, match count ==0	
	 //if the match count == str.length(), then have the match, and return the cur index as the last index of  
		CNode curNode = firstC;
		int [] currentStatus = { 0,( str.length()-1 ),0,0 };//0 means not done yet, the last index is the indx of the curNode, the second one is have match and the first one is goesBack
		currentStatus = traverseNode( curNode, currentStatus, str);
		int returnValue = -1;
		if( /*currentStatus[2] !=0&&*/ currentStatus[1]==0 ){
			returnValue = currentStatus[3];//remember to minus one since the algorithm doesn't minus one
		}
		return returnValue;
	}
	
	private int [] traverseNode(CNode curNode, int [] currentStatus /*int haveMatch, int goesBack, int indx*/, String str){			//there is a difference between no match and half of the match, think about it
		int goesBack = currentStatus[ 0 ];
		int haveMatch = currentStatus[ 1 ];
		int indx = currentStatus [ 2 ];
		if(indx < length && haveMatch==( str.length()-1 ) && goesBack==0 && indx >= 0){		//cases to go to the last node
			currentStatus[ 2 ]++;
			currentStatus = traverseNode(curNode.next,currentStatus, /*haveMatch, goesBack, indx+1,*/ str);
		}
		else if(indx == length && haveMatch== ( str.length()-1 ) && goesBack == 0 ){	//case to start traverseback
			currentStatus[ 0 ]++;
			return currentStatus;
		}
		char curStrChar=' ';
		goesBack = currentStatus[ 0 ];			//need to update the booleans
		haveMatch = currentStatus[ 1 ];
		indx = currentStatus [ 2 ];
		int foundAns = currentStatus [ 3 ];
		//System.out.println("fk u the index is "+indx+" goes back is "+currentStatus[ 0 ]+" goes back is "+ goesBack);
		if ( goesBack ==1 ){	//declare going backwards, start to compare
				//System.out.println("fk u the index is "+indx+" goes back is "+currentStatus[ 0 ]+" have match is "+haveMatch+" corresponding data is "+currentStatus[ 1 ]);
		
			if( haveMatch== str.length()-1){	//if there is no match, char from the end of the list
				curStrChar = str.charAt( haveMatch );		//start charting from the last char of the str

				if( curNode.data==( curStrChar ) ){//if a match exist, reduce the index, if no match exist, do nothing
					currentStatus[ 1 ] --;
				}
			}
			//need to deal with the upper case shit
			else if( haveMatch > 0 && haveMatch < str.length()-1 ){
				curStrChar = str.charAt( haveMatch );
				if( curNode.data==( curStrChar ) ){//if a match exist, reduce the index, if no match exist, do nothing
					currentStatus[ 1 ] --;
				}
				else{
					currentStatus[ 1 ] = str.length()-1;   //mismatch, reset the match number back
				}
			}
			else if( haveMatch == 0 && indx >= 0){	//base case find the match, just return the have match, just use the 
				curStrChar = str.charAt( haveMatch );
				//System.out.println("data is "+curNode.data+" curstr is "+curStrChar+" the match index is "+indx );
				if( curNode.data==( curStrChar ) && foundAns == 0){//if a match exist, return
					currentStatus [ 3 ] = currentStatus[ 2 ]-1;
					//currentStatus [ 2 ] = currentStatus[ 2 ]-1;
					//System.out.println("jajjajaja "+ (indx-1));
					return currentStatus;
				}
				else if( curNode.data != ( curStrChar ) && foundAns == 0 ){
					currentStatus[ 1 ] = str.length()-1;   //mismatch, reset the match number back
				}
				
			}
			currentStatus [ 2 ] --;			//remember to decrease the index number
			
		}
		
		return currentStatus;
	}
	
	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
	// in the return array corresponds to a part of the match of the array of
	// patterns in the argument.  If the overall match does not succeed, return
	// null.  For much more detail on the requirements of this method, see the
	// Assignment 3 specifications.
	public MyStringBuilder2 [] regMatch(String [] pats)
	{
		//will it be possible to have null cell in the string array? let's assume it is one cell first?
		int matchCount = 0;				//to count how many matches
		//MyStringBuilder2 [] MSBA = new MyStringBuilder2[1]; as
		MyStringBuilder2 [] MSBA = new MyStringBuilder2[ pats.length ]; 
		if ( length != 0 ){
				
			int startIndx = 0;	// originally started at 0 index
			boolean changeStartIndex = true;
			MSBA = buildingRegMatch( 0,  pats.length,  pats,  MSBA, startIndx,changeStartIndex, -1, -1 );
			
			
			System.out.println("startIndx  is "+startIndx );
			if( MSBA== null ){			//no match for every pat, so no matches
				return null;
			}
				
			return MSBA;
			///////////////////////////////////
		}
		else{			//if mystringbuilder is null, then impossible to match, return null
			return null;
		}			
	}
	
	//,MyStringBuilder2 PrevMSB
	
	//infoArray, [0] == cur ;[1]==index ; [2]==hasmatch; [3]==noMatch, [4]=jumpedAlready [5]=indexInMyStringBuilder  0 is false and 1 is true			cur is the index of the pad
	private int[] backTrack( int[] infoArray, CNode curNode,  MyStringBuilder2 [] MSBA, int previousEnd){		//the previous string builder need to backtrack, not the current stringbuilder
	
		curNode = iterateTo(0, infoArray[ 5 ]-1, firstC);	//iterate to the previous location
		System.out.println("iterate to node "+curNode.data+" inforArray 5 is "+infoArray[ 5 ]+" previousEnd is "+previousEnd+" existing stringbuilderLen is "+length+" infoArray[ 0 ] "+infoArray[ 0 ]);
		MyStringBuilder2 PrevMSB = MSBA[ infoArray[ 0 ]-1 ];
		System.out.println("checking  prev length is "+(PrevMSB));
		
		if( infoArray[ 5 ] == PrevMSB.length-1 ){			//the starting case need to delete twice
			PrevMSB = PrevMSB.deleteCharAt( PrevMSB.length-1 );			//deleting the last character
		}
		int storedLength = PrevMSB.length;
		PrevMSB = PrevMSB.deleteCharAt( PrevMSB.length-1 );			//deleting the last character
		/*if the previousone length is 0 then we go back to the previous and previous one, infoArray[1]--, infoArray[0] = previousOne[0]--*/
		//keep backtracking until 2 base cases, one is previouslength=0 and infoArray[1]=0, the other one is to find the result
		
		if(PrevMSB.length==0 ){				//need to think about the situation when prevMSB is in the middle and can still return back
			if( infoArray[ 0 ] == 1 ){			//base case, current index is 1 and the previous is null, then no solution
				infoArray[ 2 ] = -1;				
			}
			else{							//in this situation, cur also have to update	
				//infoArray[ 1 ] = infoArray[ 1 ]-1;				//the index 1 here should be the previous padlength, which u need to think of how to express
				infoArray[ 0 ] = infoArray[ 0 ]-1;//infoArray[ 0 ]-2;				//basically reset the infoArray[0]
				System.out.println("index now become "+infoArray[ 0 ]);
				
				PrevMSB = MSBA[ infoArray[ 0 ] ];
				PrevMSB = PrevMSB.deleteCharAt( PrevMSB.length-1 );
				infoArray[ 5 ] = previousEnd-storedLength-1;			//because later infoArray[5] is gonna +1, so we minus 2
				infoArray[ 2 ] = -2;
			}
		}
		infoArray[ 5 ] = infoArray[ 5 ]-1;
		
		return infoArray;
	}
	
	
	//the first iteration should choose a stringbuilder that can satisfy all the pats requirements
	
	//this method need to be able to return the stratIndex value
	private MyStringBuilder2 [] buildingRegMatch(int cur, int patsLen, String[] pats, MyStringBuilder2 [] MSBA, int startIndx, boolean changeStartIndex, int previousEnd, int jumpingEndIndx ){		//cur is the current index in the pad 
		boolean goNextPat = false;
		boolean jumpedInMiddle =false;
		if( cur < patsLen ){			//iterative case, this is also the greedy search case for the stringbuilder, need to think about the backtracking case, involving the startIndx change 
			String curStr = pats[ cur ];  	//the original code
			int[] infoArray = null;	
			if( curStr != null ){
				int[]tempArray = { 0, curStr.length(), 0, 0,0, startIndx };			//should dynamically change the index of the starting point of infoArray
				infoArray = tempArray;
				MyStringBuilder2 MSB = new MyStringBuilder2();
				CNode curNode = null;
				if( infoArray[ 5 ]!= 0 ){
					curNode = iterateTo(0, infoArray[ 5 ], firstC);
				}
				else{
					curNode = firstC;
				}
				//System.out.println("testing, current end originally is "+infoArray[ 5 ]+" strt index is " +startIndx);
				infoArray = iterativeAddingStringBuilder( infoArray, curNode, curStr, MSB, cur);			//need to debug why this is not activated when curchar is 6
				//System.out.println("after the iteration, cur is "+infoArray[0]+" node is "+curNode.data+" char index is "+infoArray[5]+" cur is "+cur);	
				
				if( changeStartIndex )
					startIndx = infoArray[ 5 ];
				
				goNextPat = false;
				
				if( MSB.length != 0 ){
					//////////////////
					if(previousEnd != -1){  //when previous ending > curending or curending== previousnumber+cursize
						System.out.println("previous ending is "+previousEnd+" current end is "+infoArray[ 5 ]+" cursize is "+MSB.length+" MSB is "+MSB);
					}	
					if( previousEnd >infoArray[ 5 ] || (infoArray[ 5 ] == previousEnd+MSB.length) || previousEnd==-1){				//situations when not skipping, including the first loop when previousEnding is -1
					///////////////////////////////
						MSBA[ cur ] = MSB;
						System.out.println("adding "+MSB+" cur is "+cur);
						goNextPat = true;					
						changeStartIndex = true;
						if( cur !=0 ){	//have some issues deleting the last duplicated char
							MyStringBuilder2 PrevMSB = MSBA[ cur-1 ];
							if( PrevMSB.lastC.data == MSB.firstC.data ){				//deleting the duplicated char
								PrevMSB = PrevMSB.deleteCharAt( PrevMSB.length-1 );
							}
						}
					/////////////	think about why after the backtrack, the index goes back to 11
					}
					else{					//situations when we need to abandon the previous stringbuilder and startbuilding from scratch, this situation can only happen on the second or later pads
						System.out.println("we are fked, cur is  "+cur+ " MSB is "+MSB+" infoArray[5] is "+infoArray[5]+" restart from "+previousEnd+" startIndex is "+startIndx); //+" the prev is "+MSBA[ cur-1 ]
						
						jumpedInMiddle = true;
						if(previousEnd != 0 && previousEnd > jumpingEndIndx){			//previousEnd==0 is the base case, don't store it
							jumpingEndIndx = previousEnd;
						}
						//if the backtrack after jump doesn't work, then use the code below					
						//need to add another situation to check if disrupted order and the next one is find or not 		
					}
				}
				/*
				seems like the curnode mismatch with the current index issue, think about it later and correct it
				*/
				if( (cur!=0 && MSB.length== 0)|| jumpedInMiddle ){
					//MyStringBuilder2 PrevMSB = MSBA[ infoArray[ 0 ]-1 ];
					//System.out.println("prevmsb is "+PrevMSB+" infoArray[ 0 ] is "+infoArray[ 0 ]/*+" length is "+PrevMSB.length*/+" infoArray 5 is "+infoArray[ 5 ]+" cur is "+cur);
					if(!jumpedInMiddle){
						System.out.println("in the backtrack, curnode is "+curNode.data+" cur is "+cur);
						infoArray[ 0 ] = cur;	//switch to the previous index
						infoArray = backTrack( infoArray, curNode,  MSBA, previousEnd );
						goNextPat = false;
						startIndx = MSBA[ infoArray[ 0 ]-1 ].length-1;
						changeStartIndex = false;
						if( infoArray[ 2 ] ==-1){	//no match cases, quit
							cur=0;
							MSB.length =0;
							System.out.println("this is a mistake");
						}
						if( infoArray[ 2 ] == -2){			//only used to indicate special cases, change infoArray[2] back to normal after catching the special case
							infoArray[ 2 ] = 0;
							cur = cur - 2;
							infoArray[ 1 ] = pats[ cur ].length();	//go back to 2 pads ahead
							MyStringBuilder2 PrevMSB = MSBA[ infoArray[ 0 ]-1 ];
							previousEnd = infoArray[ 5 ]+1-PrevMSB.length;
							startIndx = previousEnd;
							System.out.println("\n ironically, cur is "+cur+" and infoArray[1] is "+infoArray[1]+" startIndex IS "+startIndx);
						}
					}
					else{			//jumped middle case, need to set a new stringbuilder and a new cur into 1 or later numbers
						//need to think about how to store the beginning of 
						System.out.println("zzz info0 is "+infoArray[0]+" cur is "+cur+" the previousEnd is "+previousEnd+" previous startIndx is "+startIndx);
						infoArray[ 0 ] = cur;
						infoArray = backTrack( infoArray, curNode,  MSBA, previousEnd );
						
						if(MSBA[ cur-1 ].length==0 && (cur-1) >= 0){	//situation to backtrack again, set cur=cur-1 
							System.out.println("previous length is 0 situation, backtrack again cur is "+cur+" jumpingEndIndx is "+jumpingEndIndx);
							//System.out.println("");
							infoArray[ 0 ] = cur-1;	
							cur = cur-1;
							startIndx =	previousEnd - 2;		//have to start from 2 here, previous is 4, then previous-2
							previousEnd = startIndx;
						}
						else{
							startIndx = previousEnd-1; //since backtrack 1, need to go back one					//so it is the msba's value that fuked up, the first one works because it is naturally started from 0 index, but the second one isn't
							infoArray[ 0 ] = cur-1;				//recover cur's value
							changeStartIndex = false;
							System.out.println("new startindex is "+startIndx+" the new infoarray 0 is "+infoArray[0] +" the previous stringbuilder length is "+(MSBA[ cur-1 ].length) );
							previousEnd = startIndx; //the ending for the previous array	
						}
						//so the previous length is 3? why
							
						if( MSBA[ cur ]!= null ){	//to avoid some special cases
						///////////////////////////             things fked up in this function, for somereason the backtracking here always backone more index, resulting the incorrect index and screws everything up
							if( startIndx < 0 || ( MSBA[ cur ].length==0 && cur==0 ) ){					//can't go back anymore, need to jump to the previous jumpingEndIndx
								System.out.println("oh st patric, jumpingEndIndx is "+jumpingEndIndx);						
								int prevLen = 0;
								MSBA = new MyStringBuilder2[ pats.length ]; //reinitialize anothe array to store the MSB
								startIndx = jumpingEndIndx+1;//startIndx -MSB.length-prevLen;
								previousEnd = jumpingEndIndx+1;
								int[] tempArray1 = { 0, curStr.length(), 0, 0, previousEnd, startIndx };			//should dynamically change the index of the starting point of infoArray
								infoArray = tempArray1;
								goNextPat = false;
								cur = 0;	
								changeStartIndex = true;	
							}
						}
						//////////////////////////
						
					}
					
					jumpedInMiddle = false;//reset
					System.out.println("infoArray[5] is "+infoArray[5]+" cur is "+cur+" the total length is "+length+" infoArray[0 ] is "+infoArray[0]+" MSB is "+MSB);
				}
				//System.out.println("fk ramirez");
				///////////////////////////
				if( cur== 0 && MSB.length== 0){
					//startIndx = -1;
					//return -1;
					return null;
				}
			}
			if( goNextPat ){
				System.out.println("fk u HW");
				previousEnd = infoArray[ 5 ];
				MSBA =  buildingRegMatch( cur+1, patsLen, pats, MSBA, startIndx,changeStartIndex, previousEnd, jumpingEndIndx );			//cur can't go to the next unless the previous one is sure to be filled
			}
			else{
				MSBA = buildingRegMatch( cur, patsLen, pats, MSBA, startIndx,changeStartIndex, previousEnd,jumpingEndIndx );			//cur can't go to the next unless the previous one is sure to be filled	
			}
		}
		return MSBA;
		
	}
	
	
	//this is the method to iterate through mystringbuilder, if there is nomatch, break, change the boolean into int[] and then access the cur location there
	///////////////////////////////////////////////////
	/* infoArray, [0] == cur ;[1]==index ; [2]==hasmatch; [3]==noMatch, [4]=jumpedAlready [5]=indexInMyStringBuilder  0 is false and 1 is true
	*/
	//backTrack should be happening around the iterativestringbuilder and iterativeaddingpad
	//cur is used to check the current index in padding, because only the first pad can skip pads
	private int[] iterativeAddingStringBuilder(int[] infoArray, CNode curNode, String str, MyStringBuilder2 MSB, int cur){		//cur and index are used to check the current position in the length of the string
		boolean noMatch = false;
		if( curNode.next == null || infoArray[ 3 ]== 1 ){			//base case, do nothing
			if( curNode.next == null ){					//the last element, do one more round of the iterate through the pad
				infoArray = iteratePad(infoArray, curNode, str, MSB,cur);
			}
			else{
				System.out.println("noMatch on "+curNode.data+" and "+str.charAt(infoArray[ 0 ] ) );
			}
		}
		else{						//go to the next node
			//System.out.println("wtf");
			infoArray = iteratePad(infoArray, curNode, str, MSB,cur);	//iterate through each elements in the pad string			
			if( infoArray[ 2 ] == 0 ){//no match becomes true once hasMatch is false
				infoArray[ 3 ] = 1;
			}
			
			else{			//if have match, iterate through the next node 
				//this need to be fixed, can only jump nodes for the first pad, and if backtrack doesn't work here, we need to update the pads to the current nodes
				
				//if(cur == 0){			//only the first pad can jump element
				infoArray[ 5 ] = infoArray[ 5 ]+1;
				infoArray = iterativeAddingStringBuilder( infoArray,  curNode.next,  str,  MSB, cur); //switch to the next node 	
				//}
				
				/////////////////////////////////
			}
		}
		return infoArray;
	}
	
	/* infoArray, [0] == cur ;[1]==index ; [2]==hasMatch; [3]==noMatch, [4]==wordjumped  0 is false and 1 is true
	*/
	//private boolean iteratePad(int cur, int index, CNode curNode, String str, MyStringBuilder2 MSB, boolean hasMatch){
		
	// need to pass the pat and curIndex in the pat to test	
	private int[] iteratePad(int[] infoArray, CNode curNode, String str, MyStringBuilder2 MSB, int cur){	
		if( infoArray[ 0 ] == infoArray[ 1 ] ){	//base case, do nothing
			if( infoArray[ 2 ]== 1 ){
				return infoArray;
			}
			else{			//shouldn't u switch to the next char in the 
				if( curNode!=lastC ){//&& infoArray[ 4 ] == 0){					//should check here if the word jump is used or not
					if( infoArray[ 4 ]== 0 ){
						//System.out.println("cur is "+infoArray[0]+" and the index is "+infoArray[1]+" node is "+curNode.data+" char is "+str+" cur is "+cur+" curnode index is "+infoArray[5]);
						infoArray[ 5 ] = infoArray[ 5 ]+1;
						infoArray[ 0 ] = 0;	//recounting the cur from the next index
						infoArray = iterativeAddingStringBuilder( infoArray,  curNode.next,  str,  MSB, cur);		//duplicate because the outside value hasn't been updated
						//System.out.println("change into cur is "+infoArray[5]);
					}
				}				
			}
		}
		else{
			char curChar =str.charAt( infoArray[ 0 ] );				
			//System.out.println("wtf curChar "+curChar+" curNode.data "+curNode.data);
			//System.out.println("the first index is  is "+infoArray[ 0 ]+" str length is "+str.length() );
			if( curNode.data == curChar){ //&& infoArray[ 4 ] < 2 ){
				MSB.append( curChar );
				infoArray[ 2 ] = 1;
				infoArray[ 4 ] = 1;			//this means previously have match already
				infoArray[ 0 ] = 0;//infoArray[ 0 ] + 1;		//find match, go to the next element
			}
			else{										//not finding matches, go to the next char in the pad				
				infoArray[ 2 ] = 0;
				infoArray[ 0 ] = infoArray[ 0 ] +1;
				infoArray = iteratePad(infoArray, curNode, str, MSB,cur);
			}
		}
		return infoArray;
	}

	/////////////////////////////////////
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
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
				